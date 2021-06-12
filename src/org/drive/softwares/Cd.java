package org.drive.softwares;

import com.sdk.storage.base.DirectoryOperation;
import org.drive.root.Shell;

public class Cd {

    public void changeDirectory() {
        try {
            if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
                Shell.getVariables().replace("path", Shell.getVariables().get("path"), Shell.getOs().getHomeUser());
            } else if (Shell.getParams().length == 1) {
                String path = Shell.changeDirectoryPath(Shell.getParams()[0]);

                if (Shell.getStrings().isNullOrEmpty(path)) {
                    Shell.getConsole().print("\tcd: \"" + Shell.getParams()[0] + "\" no such file or directory.", true);
                } else {
                    Shell.getVariables().replace("path", Shell.getVariables().get("path"), path);
                }
            } else {
                Shell.getConsole().print("\tcd: too many arguments.", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
