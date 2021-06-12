package org.drive.softwares;

import org.drive.root.Boot;
import org.drive.root.Shell;

public class Echo {

    public void printText() {
        if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
            Shell.getConsole().print("\techo: no arguments set.", true);
        } else {
            for (String parameter : Shell.getParams()) {
                if (parameter.startsWith("$")) {
                    parameter = parameter.substring(1);
                    new Boot().checkBoot();

                    if (Shell.getVariables().containsKey(parameter)) {
                        if (parameter.equals("enc")) {
                            Shell.getConsole().print("\t\uD83D\uDD95", true);
                        } else {
                            Shell.getConsole().print("\t" + Shell.getVariables().get(parameter), true);
                        }
                    }
                } else {
                    Shell.getConsole().print("\t" + parameter, true);
                }
            }
        }
    }
}
