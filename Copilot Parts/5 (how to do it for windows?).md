Creating a custom URI scheme on Windows involves modifying the Windows Registry. Here's a step-by-step guide:

1. **Open the Registry Editor**: Press `Win + R`, type `regedit`, and hit Enter.
2. **Navigate to the Protocols Section**: Go to `HKEY_CLASSES_ROOT`.
3. **Create a New Key**: Right-click on `HKEY_CLASSES_ROOT`, select New > Key, and name it after your custom protocol (e.g., `myapp`).
4. **Define the Default Value**: Double-click on `(Default)` and set a description for your protocol (e.g., `My App Protocol`).
5. **Add URL Protocol**: Create a new string value named `URL Protocol` and leave its value empty.
6. **Create a Shell Subkey**: Inside your protocol key (`myapp`), right-click and create a new key named `shell`.
7. **Create an Open Key**: Inside the `shell` key, right-click, select New > Key, and name it `open`.
8. **Create a Command Key**: Inside the `open` key, right-click, select New > Key, and name it `command`.
9. **Define the Command to Execute**: Click on the `command` key, and in the right-hand pane, double-click the `(Default)` entry. Set the value to the path of the executable you want to run, followed by `"%1"` (including the quotes). For example: `C:\Path\To\App.exe "%1"`.

After completing these steps, your custom URI scheme should be ready! You can test it by entering something like `myapp://test` in your web browser or command prompt.

Would you like more detailed instructions or help with a specific step?


### <a href="https://github.com/CodePearly/URI-schemes/blob/main/Copilot%20Parts/6%20(how%20to%20make%20a%20java%20application%20to%20do%20it%20for%20you%20on%20windows).md">next</a>
