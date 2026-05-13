# Shared Technical Constraints

## Port Management
- **Port Conflicts**: Before starting services, proactively check for any running containers or background processes holding the required ports (e.g., 8080, 8081, 8082). If a conflict is found, **do not automatically stop it**. Report the conflict to the user and ask for confirmation to stop the process or to configure alternative ports.

## Health Checks
- **Health Checks**: Verify the application successfully starts by checking its `/actuator/health` endpoint.

## Memory Guardrails
- **Memory Guardrails**: Set `export MAVEN_OPTS="-Xmx1G"` before starting to ensure the background process does not consume excessive memory.

## Container/Docker Configurations
- **Container Configurations**: Set conservative requests for Java apps (e.g., `150m CPU`, `384Mi Memory`) to avoid quota issues. Default to using in-memory databases (like H2) without mapping local volumes unless explicitly requested otherwise.
