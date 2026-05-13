## Shared Policies

### 1. Port Conflict Management
- **Port Conflicts**: Before starting services or tests, proactively identify any running containers, background processes, or services bound to the required ports (e.g., 8080, 8081, 8082). If a conflict is found, **do not automatically terminate or stop it**. Report the conflict to the user and ask for explicit instructions or confirmation to stop the process or configure alternative ports.

### 2. Health Verification
- **Actuator Endpoints**: Ensure services expose health endpoints at `/actuator/health`.
- **Health Checks**: Always verify that the application has successfully started and initialized by checking its `/actuator/health` HTTP endpoint before proceeding to next steps.

### 3. Generic Tech-Stack Constraints
- **Java**: Version 21 or greater.
- **Spring Boot**: Version 3.2.3 or greater.
- **Database**: Default to using in-memory databases (like H2) without mapping local volumes for isolated local workflows.
