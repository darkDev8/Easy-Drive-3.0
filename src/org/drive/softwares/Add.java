package org.drive.softwares;

import com.sdk.storage.base.FileOperation;
import com.sdk.storage.file.SingleFile;
import com.sdk.storage.file.TextFile;
import com.sdk.tools.ExternalTools;
import org.drive.database.entity.File;
import org.drive.database.entity.User;
import org.drive.database.repository.FileRepository;
import org.drive.database.repository.UserRepository;
import org.drive.root.Shell;

public class Add {

    public void addNewFile() {
        try {
            if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
                Shell.getConsole().print("\tadd: no arguments set.", true);
            } else if (Shell.getParams().length == 1) {
                String path = Shell.getFilePath(Shell.getParams()[0]);

                if (Shell.getStrings().isNullOrEmpty(path)) {
                    Shell.getConsole().print("\tadd: \"" + Shell.getParams()[0] + "\" no such file or directory.", true);
                } else {
                    FileOperation fp = new FileOperation(path);

                    if (fp.exists()) {
                        User user = new UserRepository().getUser(Shell.getVariables().get("user"));

                        if ((user.getSpace() + fp.getSize()) > Integer.parseInt(Shell.getVariables().get("space"))) {
                            Shell.getConsole().print("\tadd: not enough space to upload the file.", true);
                        } else {
                            File file = new File(fp.getSize(),fp.getName(),fp.getCreateDate(), Shell.getOs().getDate(),
                                    fp.getType());

                            file.setContent(ExternalTools.readFileToBytes(fp.getFile()));
                            file.setFid(user.getId());

                            FileRepository fr = new FileRepository();

                            if (fr.fileExists(fp.getName(), "name") && fr.fileExists(String.valueOf(user.getId()), "fid")) {
                                if (Shell.getConsole().ask("\tadd: file already exists.delete", true) == 1) {
                                    if (!fr.deleteFile(fp.getName(), "name")) {
                                        Shell.getConsole().print("\tadd: failed to delete the last file.", true);
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            }

                            Shell.getConsole().print("\tuploadig file...", true);
                            if (fr.addFile(file)) {
                                Shell.getConsole().print("\tadd: new file uploaded successfully.", true);
                            } else {
                                Shell.getConsole().print("\tadd: failed to add new file.", true);
                            }
                        }
                    } else {
                        Shell.getConsole().print("\tadd: invalid file selected.", true);
                    }
                }
            } else {
                Shell.getConsole().print("\tadd: too many arguments.", true);
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }
}
