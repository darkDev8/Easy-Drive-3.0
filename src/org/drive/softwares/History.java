package org.drive.softwares;

import com.sdk.data.structures.StringList;
import com.sdk.tools.ExternalTools;
import org.drive.database.repository.HistoryRepository;
import org.drive.root.Shell;

public class History {

    public void manageHistory() {
        try {
            HistoryRepository hr = new HistoryRepository();
            String [] commands = hr.getAllHistory();

            if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
                for (int i = 0; i < commands.length; i++) {
                    Shell.getConsole().print("\t" + commands[i], true, 0);
                }
            } else if (Shell.getParams().length == 1) {
                String param = Shell.getParams()[0];
                if (!param.startsWith("-")) {
                    Shell.getConsole().print("\thistory: invalid option argument.", true);
                    return;
                }

                if (param.equals("-")) {
                    Shell.getConsole().print("\thistory: invalid option argument.", true);
                } else {
                    param = param.substring(1);

                    if (param.equals("c")) {
                        if (!hr.removeTableContent("History")) {
                            Shell.getConsole().print("\thistory: failed to delete history files.", true);
                        }
                    } else if (param.equals("n")){
                        for (int i = 0; i < commands.length; i++) {
                            Shell.getConsole().print("\t[" + i + "] " + commands[i], true, 0);
                        }
                    } else {
                        Shell.getConsole().print("\thistory: invalid option argument.", true);
                    }
                }
            } else {
                Shell.getConsole().print("\thistory: too many arguments.", true);
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }
}
