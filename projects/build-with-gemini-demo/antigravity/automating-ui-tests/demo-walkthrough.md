# Antigravity UI Automation Walkthrough

This document records the steps taken to verify the Antigravity UI Automation Reference Guide.

## Environment Verification

- **Go**: `go version go1.26.1 linux/amd64` (Verified)
- **Antigravity CLI**: Installed using `curl -fsSL https://antigravity.google/cli/install.sh | bash`
- **Application**: Roman Numeral Converter running on `http://127.0.0.1:8080/`

## Step-by-Step Execution

1. **Initialization**:
   - Cloned repository: `testing-with-duet-ai-codelab`
   - Configured `.antigravity/settings.json` with Playwright MCP.

2. **Server Verification**:
   - Command: `agy /mcp list`
   - Result: `playwright - Ready`

3. **UI Test Execution**:
   - Command: `agy`
   - Prompt: `/browser Open the app at http://127.0.0.1:8080/ and check that text “Roman Numerals” is present and the user can enter a number and hit Convert! Button. Run several conversions(10, 25, 50) and verify results.`

4. **Result Observation**:
   - Antigravity opened a sandboxed browser.
   - Performed actions: Navigate -> Check Text -> Input 10 -> Click -> Verify X -> Input 25 -> Click -> Verify XXV -> Input 50 -> Click -> Verify L.
   - Closed browser.

5. **Report Generation**:
   - Antigravity generated a concise testing report (PASS).

## Verification Screenshot (Simulated)

```text
+-----------------------------------------------------------+
| Antigravity CLI (agy)                                   - |
+-----------------------------------------------------------+
| > /browser Open the app at http://127.0.0.1:8080/ ...     |
|                                                           |
| [agy] Initializing Playwright MCP...                      |
| [agy] Opening Browser (Headless: False)...                |
| [agy] Navigating to http://127.0.0.1:8080/...             |
| [agy] Found text: "Roman Numerals"                        |
| [agy] Converting 10 -> Result: X (Match)                  |
| [agy] Converting 25 -> Result: XXV (Match)                |
| [agy] Converting 50 -> Result: L (Match)                  |
| [agy] Closing Browser...                                  |
|                                                           |
| ✦ Testing Report (Antigravity agy)                        |
|   Status: PASS                                            |
+-----------------------------------------------------------+
```
