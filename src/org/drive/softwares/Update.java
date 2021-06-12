package org.drive.softwares;

import com.sdk.storage.base.FileOperation;
import com.sdk.tools.ExternalTools;
import org.drive.database.entity.File;
import org.drive.database.repository.FileRepository;
import org.drive.root.Shell;

public class Update {

    public void updateContent() {
        try {
            if (Shell.getParams().length < 2) {
                Shell.getConsole().print("\tupdate: few many arguments.", true);
            } else if (Shell.getParams().length > 2) {
                Shell.getConsole().print("\tupdate: too many arguments.", true);
            } else {
                String id = Shell.getParams()[0], path = Shell.getFilePath(Shell.getParams()[1]);
                FileRepository fr = new FileRepository();

                if (fr.fileExists(id, "id")) {
                    if (Shell.getStrings().isNullOrEmpty(path)) {
                        Shell.getConsole().print("\tupdate: \"" +
                                Shell.getParams()[1] + "\" no such file or directory.", true);
                    } else {
                        FileOperation fo = new FileOperation(path);

                        File file = new File();
                        file.setName(fo.getName());
                        file.setSize(fo.getSize());
                        file.setContent(ExternalTools.readFileToBytes(path));

                        if (fr.updateFile(id, file)) {
                            Shell.getConsole().print("\tupdate: file content updated successfully.", true);
                        } else {
                            Shell.getConsole().print("\tupdate: failed to update the file content.", true);
                        }
                    }
                } else {
                    Shell.getConsole().print("\tupdate: there is no such file with \""
                            + Shell.getParams()[0] + "\" id.", true);
                }
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }
}
