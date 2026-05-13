# Shared Operational Library

This library contains standard operational instructions for all agents in the SDLC workflow.

## Health-Check Logic
- Always verify that applications have successfully started by checking their `/actuator/health` HTTP endpoints.
- If a health check fails, analyze the failure to determine if it is a configuration issue, a port conflict, or a memory constraint, and take appropriate action.

## Port Management Rules
- Before starting services or running tests, proactively check for any running containers or background processes holding the required ports (e.g., 8080, 8081, 8082).
- If a port conflict is found, resolve it autonomously (e.g., adjust configurations or stop conflicting orphaned processes as permitted by global guardrails) instead of waiting for user intervention.

## Shared Context Layer Guidelines
- The shared context layer is located at `../shared_context.md`.
- **Reading**: Always read infrastructure connection strings, endpoints, and current workflow state from the shared context layer. Do not rely on manually generated files or user prompts for this information.
- **Writing**: Update the shared context layer immediately when new infrastructure details (e.g., dynamically discovered cluster endpoints, Terraform outputs) are generated or when the workflow state changes.
- **Isolation**: Shared context is strictly scoped to the current session.
