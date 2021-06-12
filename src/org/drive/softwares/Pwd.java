package org.drive.softwares;

import org.drive.root.Shell;

public class Pwd {

    public void printPath() {
        if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
            Shell.getConsole().print("\t" + Shell.getVariables().get("path"), true);
        } else {
            Shell.getConsole().print("\tpwd: too many arguments.", true);
        }
    }
}
