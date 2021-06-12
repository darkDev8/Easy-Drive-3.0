package org.drive.softwares;

import com.sdk.tools.ExternalTools;
import org.drive.database.repository.FileRepository;
import org.drive.root.Shell;

public class Rm {

    public void removeFile() {
        try {
            if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
                Shell.getConsole().print("\trm: no arguments set.", true);
            } else if (Shell.getParams().length == 1) {
                FileRepository fr = new FileRepository();

                if (fr.fileExists(Shell.getParams()[0], "id")) {
                    if (!fr.deleteFile(Shell.getParams()[0], "id")) {
                        Shell.getConsole().print("\trm: failed to delete the file.", true);
                    }
                } else {
                    Shell.getConsole().print("\trm: there is no such file with \""
                            + Shell.getParams()[0] + "\" id.", true);
                }
            } else {
                Shell.getConsole().print("\trm: too many arguments.", true);
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }
}
