package org.drive.softwares;

import com.sdk.storage.base.DirectoryOperation;
import com.sdk.storage.base.DirectorySystem;
import org.drive.root.Shell;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Mkdir {

    public void makeDirectories() {
        if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
            Shell.getConsole().print("\tmkdir: no arguments set.", true);
        } else {
            String currentPath = Shell.getVariables().get("path");

            for (String path : Shell.getParams()) {
                String tmpPath = null;

                if (Shell.getOs().isWindows()) {
                    if (path.endsWith("\\")) {
                        tmpPath = currentPath.concat(path);
                    } else {
                        tmpPath = currentPath.concat("\\").concat(path);
                    }
                } else {
                    if (path.endsWith("/")) {
                        tmpPath = currentPath.concat(path);
                    } else {
                        tmpPath = currentPath.concat("/").concat(path);
                    }
                }

                DirectoryOperation ds = new DirectoryOperation(tmpPath);

                if (ds.exists()) {
                    if (Shell.getConsole().ask("\tmkdir:directory \"" + ds.getName() + "\" exists.delete",
                            true) == 1) {
                        if (ds.delete()) {
                            if (!ds.create()) {
                                Shell.getConsole().print("\tmkdir: failed to create \"" + path + "\".", true);
                            }
                        } else {
                            Shell.getConsole().print("\tmkdir: failed to remove \"" + ds.getName() + "\".",
                                    true);
                        }
                    }
                } else {
                    if (!ds.create()) {
                        Shell.getConsole().print("\tmkdir: failed to create \"" + path + "\".", true);
                    }
                }
            }
        }
    }
}
