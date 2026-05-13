#!/usr/bin/env python3
import argparse
import json
import os
import sys

class SharedContext:
    def __init__(self):
        self.state = {}

    def set(self, key, value):
        self.state[key] = value

    def get(self, key):
        return self.state.get(key)

shared_context = SharedContext()

def live_discovery():
    """Simulates live discovery of environmental state."""
    print("Performing live discovery of environment...")
    state = {
        "cluster_name": "gke-cluster-demo",
        "sql_connection_string": "gcp:us-central1:demo-sql",
        "load_balancer_ip": "192.168.1.100",
        "artifact_registry_url": "us-central1-docker.pkg.dev/my-project/my-repo"
    }
    return state

def get_shared_constraints():
    file_path = os.path.join(os.path.dirname(__file__), 'core', 'shared_constraints.md')
    if os.path.exists(file_path):
        with open(file_path, 'r') as f:
            return f.read()
    return ""

def run_agent(agent_name, prompt_text, extra_context=""):
    print(f"--- Running {agent_name} ---")
    agent_file = os.path.join(os.path.dirname(__file__), '.gemini', 'agents', f'{agent_name}.md')
    agent_prompt = ""
    if os.path.exists(agent_file):
        with open(agent_file, 'r') as f:
            agent_prompt = f.read()
            
    system_prompt = agent_prompt + "\n\n" + get_shared_constraints()
    
    print(f"Executing: {prompt_text}")
    if extra_context:
        print(f"Extra Context:\n{extra_context}")
        
    print(f"[{agent_name} output simulated]\n")
    return "Simulation completed successfully."

def plan(args):
    print("Executing 'plan' phase...")
    prompt = f"Plan feature: {args.idea}"
    run_agent("builder_agent", prompt)
    shared_context.set("plan", args.idea)

def develop(args):
    print("Executing 'develop' phase...")
    test_failures = shared_context.get("test_failures")
    prompt = "Develop code based on the plan."
    extra = ""
    if test_failures:
        extra = f"Test Failures to Remediate:\n{test_failures}"
        print("Injecting test failures directly to Builder persona for remediation.")
    
    run_agent("builder_agent", prompt, extra_context=extra)
    shared_context.set("build_status", "developed")

def verify(args):
    print("Executing 'verify' phase...")
    prompt = "Generate tests and verify health."
    run_agent("tester_agent", prompt)
    
    if not shared_context.get("test_failures"):
        print("Simulation: Found test failures!")
        failures = "FAIL: testCustomerCreation (CustomerServiceTest)"
        shared_context.set("test_failures", failures)
        return False
    else:
        print("Simulation: All tests passed!")
        shared_context.set("test_failures", None)
        return True

def deploy(args):
    print("Executing 'deploy' phase...")
    env_state = live_discovery()
    shared_context.set("env_state", env_state)
    
    prompt = "Deploy application to GKE."
    extra = f"Live Environment State:\n{json.dumps(env_state, indent=2)}"
    run_agent("devops_engineer_agent", prompt, extra_context=extra)
    print("Deployment successful.")

def auto(args):
    print("Running full SDLC pipeline...")
    plan(args)
    develop(args)
    success = verify(args)
    if not success:
        print("Verification failed. Rerunning development with test failures...")
        develop(args)
        verify(args)
    deploy(args)
    print("SDLC pipeline completed.")

def main():
    parser = argparse.ArgumentParser(description="CLI-Driven SDLC Automation")
    subparsers = parser.add_subparsers(dest="command")

    plan_parser = subparsers.add_parser("plan")
    plan_parser.add_argument("--idea", type=str, required=True, help="Product concept to plan")

    develop_parser = subparsers.add_parser("develop")

    verify_parser = subparsers.add_parser("verify")

    deploy_parser = subparsers.add_parser("deploy")

    auto_parser = subparsers.add_parser("sdlc")
    auto_parser.add_argument("--idea", type=str, required=True, help="Product concept to plan and build")
    auto_parser.add_argument("--config", type=str, help="Optional configuration")

    args = parser.parse_args()

    if args.command == "plan":
        plan(args)
    elif args.command == "develop":
        develop(args)
    elif args.command == "verify":
        verify(args)
    elif args.command == "deploy":
        deploy(args)
    elif args.command == "sdlc":
        auto(args)
    else:
        parser.print_help()

if __name__ == "__main__":
    main()
