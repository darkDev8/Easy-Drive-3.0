package org.drive.softwares;

import com.sdk.tools.ExternalTools;
import org.drive.database.repository.FileRepository;
import org.drive.root.Shell;

public class Rn {

    public void renameFile() {
        try {
            if (Shell.getParams().length > 2) {
                Shell.getConsole().print("\trn: too many arguments.", true);
            } else if (Shell.getParams().length < 2) {
                Shell.getConsole().print("\trn: few many arguments.", true);
            } else {
                String id = Shell.getParams()[0], newName = Shell.getParams()[1];
                FileRepository fr = new FileRepository();

                if (fr.fileExists(id, "id")) {
                    if (fr.fileExists(newName, "name")) {
                        Shell.getConsole().print("\trn: the file with this name already exists.aborted!", true);
                    } else {
                        if (!fr.renameFile(id, newName)) {
                            Shell.getConsole().print("\trn: failed to rename the file.", true);
                        }
                    }
                } else {
                    Shell.getConsole().print("\trn: there is no such file with \""
                            + Shell.getParams()[0] + "\" id.", true);
                }
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }
}
