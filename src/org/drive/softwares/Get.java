package org.drive.softwares;

import com.sdk.network.DownloadProgress;
import com.sdk.storage.base.FileOperation;
import com.sdk.tools.ExternalTools;
import org.drive.database.entity.File;
import org.drive.database.repository.FileRepository;
import org.drive.root.DatabaseDownloadProgress;
import org.drive.root.Shell;

import java.util.Objects;

public class Get {

    public void downloadFile() {
        try {
            if (Shell.getParams().length > 2) {
                Shell.getConsole().print("\tget: too many arguments.", true);
            } else if (Shell.getParams().length < 2) {
                Shell.getConsole().print("\tget: few many arguments.", true);
            } else {
                String id = Shell.getParams()[0], downloadPath = Shell.getParams()[1];
                FileRepository fr = new FileRepository();

                if (fr.fileExists(id, "id")) {
                    if (downloadPath.equals(".")) {
                        downloadPath = Shell.getVariables().get("path");
                    }

                    downloadPath = Shell.changeDirectoryPath(downloadPath);

                    if (Shell.getStrings().isNullOrEmpty(downloadPath)) {
                        Shell.getConsole().print("\tget: download path is invalid.", true);
                    } else {
                        File file = fr.getFile(id, false);

                        if (Objects.isNull(file)) {
                            Shell.getConsole().print("\tget: failed to fetch the file from database.", true);
                        } else {
                            if (Shell.getOs().isWindows()) {
                                downloadPath = downloadPath.concat("\\").concat(file.getName());
                            } else {
                                downloadPath = downloadPath.concat("/").concat(file.getName());
                            }

                            FileOperation fo = new FileOperation(downloadPath);
                            if (fo.exists()) {
                                if (Shell.getConsole().ask("\tget: file exists.delete", true) == 1) {
                                    if (!fo.delete()) {
                                        Shell.getConsole().print("\tget: unable to delete the source file.aborted!",
                                                true);
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            }

                            Shell.getConsole().print("\tdownloading file...", true);
                            file = fr.getFile(id, true);

                            if (ExternalTools.readBytesToFile(file.getContent(), downloadPath)) {
                                Shell.getConsole().print("\tget: file downloaded successfully.", true);
                            } else {
                                Shell.getConsole().print("\tget: failed to write to the location.", true);
                            }
                        }
                    }
                } else {
                    Shell.getConsole().print("\tget: there is no such file with \""
                            + Shell.getParams()[0] + "\" id.", true);
                }
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }
}
