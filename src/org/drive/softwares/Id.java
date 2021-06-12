package org.drive.softwares;

import org.drive.database.repository.FileRepository;
import org.drive.root.Shell;

public class Id {

    public void showFileId() {
        if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
            Shell.getConsole().print("\tid: no name specified.", true);
        } else if (Shell.getParams().length == 1) {
            int id = new FileRepository().getFileId(Shell.getParams()[0]);

            if (id == -1) {
                Shell.getConsole().print("\tid: no file found.", true);
            } else {
                Shell.getConsole().print("\tid: " + id, true);
            }
        } else {
            Shell.getConsole().print("\tid: too many arguments.", true);
        }
    }
}
