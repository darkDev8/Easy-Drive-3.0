package org.drive.softwares;

import com.sdk.tools.ExternalTools;
import org.drive.database.entity.File;
import org.drive.database.repository.FileRepository;
import org.drive.root.Shell;

public class Info {

    public void showFileInformation() {
        try {
            if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
                Shell.getConsole().print("\tinfo: no arguments set.", true);
            } else {
                for (String param : Shell.getParams()) {
                    FileRepository fr = new FileRepository();

                    if (fr.fileExists(param, "id")) {
                        File file = fr.getFile(param, false);

                        Shell.getConsole().print("\n\t" + file.getName(), true);
                        Shell.getConsole().print("\t", false);

                        Shell.getConsole().printCharacters('-', file.getName().length() + 5, true);

                        Shell.getConsole().print("\tid: " + file.getId(), true);
                        Shell.getConsole().print("\tfid: " + file.getFid(), true);
                        Shell.getConsole().print("\tsize: " + ExternalTools.toReadableSize(file.getSize()), true);
                        Shell.getConsole().print("\tcreate date: " + file.getCreateDate(), true);
                        Shell.getConsole().print("\tupload date: " + file.getUploadDate(), true);
                        Shell.getConsole().print("\tcategory: " + file.getCategory(), true);
                        Shell.getConsole().print("\tuser: " + Shell.getVariables().get("user"), true);
                    } else {
                        Shell.getConsole().print("\tinfo: there is no such file with \"" + param + "\" id.",
                                true);
                    }
                }
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }
}
