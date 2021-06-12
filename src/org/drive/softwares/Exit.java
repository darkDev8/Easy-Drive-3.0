package org.drive.softwares;

import org.drive.root.Shell;

public class Exit {

    public void exitSoftware() {
        if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
             if (Shell.getConsole().ask("\tare you sure", true) == 1) {
                 System.exit(0);
             }
        } else if (Shell.getParams().length == 1) {
            if (Shell.getParams()[0].equals("-y") || Shell.getParams()[0].equals("y")) {
                System.exit(0);
            } else {
                Shell.getConsole().print("\texit: invalid argument.", true);
            }
        } else {
            Shell.getConsole().print("\texit: too many arguments.", true);
        }
    }
}
