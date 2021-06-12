package org.drive.softwares;

import com.sdk.data.structures.StringList;
import org.drive.database.repository.FileRepository;
import org.drive.database.repository.HistoryRepository;
import org.drive.database.repository.UserRepository;
import org.drive.root.Shell;

public class Count {

    public void countTable() {
        if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
            Shell.getConsole().print("\tcount: no arguments set.", true);
        } else if (Shell.getParams().length == 1) {
            String table = Shell.getParams()[0].toLowerCase();

            if (table.equals("files")) {
                Shell.getConsole().print("\tcount: " + new FileRepository().countTable(table), true);
            } else if (table.equals("history")) {
                Shell.getConsole().print("\tcount: " + new HistoryRepository().countTable(table), true);
            } else if (table.equals("users")) {
                Shell.getConsole().print("\tcount: " + new UserRepository().countTable(table), true);
            } else {
                Shell.getConsole().print("\tcount: no such table.", true);
            }
        } else {
            Shell.getConsole().print("\tcount: too many arguments.", true);
        }
    }
}
