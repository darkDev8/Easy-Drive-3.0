package org.drive.softwares;

import com.sdk.storage.file.TextFile;
import com.sdk.tools.ExternalTools;
import org.drive.root.Shell;

public class Sfi {

    public void showFileInformation() {
        try {
            if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
                Shell.getConsole().print("\tsfi: no arguments set.", true);
            } else {
                for (String file : Shell.getParams()) {
                    String path = Shell.getFilePath(file);

                    if (Shell.getStrings().isNullOrEmpty(path)) {
                        Shell.getConsole().print("\tsfi: \"" + file + "\" no such file or directory.", true);
                    } else {
                        TextFile tf = new TextFile(path);

                        Shell.getConsole().print("\t" + tf.getName(), true);

                        Shell.getConsole().print("\t", false);
                        Shell.getConsole().printCharacters('-', tf.getName().length(), true);

                        Shell.getConsole().print("\tpath: " + path, true);
                        Shell.getConsole().print("\tname: " + tf.getName(), true);
                        Shell.getConsole().print("\tbase name: " + tf.getBaseName(), true);
                        Shell.getConsole().print("\textension: " + tf.getExtension(), true);
                        Shell.getConsole().print("\ttype: " + tf.getType(), true);
                        Shell.getConsole().print("\taccess date: " + tf.getAccessDate(), true);
                        Shell.getConsole().print("\tcreate date: " + tf.getCreateDate(), true);
                        Shell.getConsole().print("\tmodify date: " + tf.getModifyDate(), true);
                        Shell.getConsole().print("\tparent directory path: " + tf.getDirectoryPath(), true);
                        Shell.getConsole().print("\tparent directory name: " + tf.getDirectoryName(), true);
                        Shell.getConsole().print("\towner: " + tf.getOwner(), true);
                        Shell.getConsole().print("\tsize: " + tf.getReadableSize(), true);
                        Shell.getConsole().print("\tmodify time: " + tf.getModifyTime(), true);

                        Shell.getConsole().print("\n", false);
                    }
                }
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }
}
