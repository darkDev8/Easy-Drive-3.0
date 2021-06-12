package org.drive.softwares;

import com.sdk.storage.file.TextFile;
import com.sdk.tools.ExternalTools;
import org.drive.root.Shell;

public class Cat {

    public void openFile() {
        try {
            if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
                Shell.getConsole().print("\tcat: no arguments set.", true);
            } else {
                for (String file : Shell.getParams()) {
                    String path = Shell.getFilePath(file);

                    if (Shell.getStrings().isNullOrEmpty(path)) {
                        Shell.getConsole().print("\tcat: \"" + file + "\" no such file or directory.", true);
                    } else {
                        TextFile tf = new TextFile(path);

                        Shell.getConsole().print("\t" + tf.getName(), true);
                        Shell.getConsole().print("\t", false);
                        Shell.getConsole().printCharacters('-', tf.getName().length(), true);

                        Shell.getConsole().print(tf.read(), true, 0);
                    }
                }
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }
}
