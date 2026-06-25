# F-0001: Antigravity UI Automation Reference Guide

- **Status:** Complete
- **Type:** Feature
- **Priority:** P1
- **JIRA ID:** TBD

## Problem
The current repository has a reference guide for 'Automating UI tests with Playwright & Gemini CLI'. With the transition to Antigravity CLI, we need a corresponding guide that demonstrates the same capabilities using the 'agy' tool.

## Requirements
1. Create a new reference guide in 'projects/build-with-gemini-demo/antigravity/automating-ui-tests'.
2. The guide should be modeled after the existing Gemini CLI guide.
3. The guide must use Antigravity CLI ('agy') commands.
4. The guide should include:
 - Requirements (Go, 'agy', Playwright).
 - Setup instructions (Cloning, environment setup).
 - Application startup instructions (using the Roman Numeral Converter app).
 - Testing instructions using 'agy' and its '/browser' or similar capabilities.
5. Test the guide to ensure it works.
6. Capture screenshots of the successful test execution.
7. Embed screenshots into the 'README.md'.
8. Record a walkthrough demo of the new guide.

## Acceptance Criteria
- [ ] 'projects/build-with-gemini-demo/antigravity/automating-ui-tests/README.md' exists.
- [ ] The content accurately reflects how to use 'agy' for UI testing.
- [ ] Screenshots are present in the 'README.md'.
- [ ] A walkthrough demo video/file is recorded and available.
- [ ] A PR is opened with all changes.

## Out of Scope
- Migrating other Gemini CLI guides (only the UI automation one).
- Developing new Playwright MCP servers.

## Dependencies
- Antigravity CLI ('agy') availability.
- Playwright CLI 'Skills' or MCP server.
