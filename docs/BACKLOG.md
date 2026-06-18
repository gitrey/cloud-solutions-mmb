# Product Backlog

## Antigravity UI Automation Reference Guide (F-0001) [Status: In Progress]

| ID | Task | Priority | Scope | Dependencies | Spec Link | Assigned To | Status |
|----|------|----------|-------|--------------|-----------|-------------|--------|
| JIRA-UI-01 | Initialize project directory and basic README.md for 'automating-ui-tests' | High | Documentation | None | [F-0001](specs/F-0001-antigravity-ui-automation-guide.md) | SWE-1 | Done |
| JIRA-UI-02 | Adapt setup and app startup instructions from Gemini CLI guide to Antigravity | High | Documentation | JIRA-UI-01 | [F-0001](specs/F-0001-antigravity-ui-automation-guide.md) | SWE-1 | Done |
| JIRA-UI-03 | Develop and document testing instructions using `agy /browser` capabilities | High | Technical | JIRA-UI-02 | [F-0001](specs/F-0001-antigravity-ui-automation-guide.md) | SWE-1 | Done |
| JIRA-UI-04 | Verify the guide by running the instructions and capturing screenshots | Medium | QA | JIRA-UI-03 | [F-0001](specs/F-0001-antigravity-ui-automation-guide.md) | ui-test-agent | In Progress |
| JIRA-UI-05 | Embed screenshots and finalize README.md | Low | Documentation | JIRA-UI-04 | [F-0001](specs/F-0001-antigravity-ui-automation-guide.md) | swe-1 | To Do |
| JIRA-UI-06 | Record and provide the walkthrough demo | Medium | QA/Demo | JIRA-UI-05 | [F-0001](specs/F-0001-antigravity-ui-automation-guide.md) | walkthrough-recorder | In Progress |
| JIRA-UI-07 | Open Pull Request for review | Medium | Git | All above | [F-0001](specs/F-0001-antigravity-ui-automation-guide.md) | swe-1 | To Do |
