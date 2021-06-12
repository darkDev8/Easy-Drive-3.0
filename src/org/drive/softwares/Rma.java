package org.drive.softwares;

import com.sdk.security.SecurityTools;
import com.sdk.tools.ExternalTools;
import org.drive.database.entity.User;
import org.drive.database.repository.FileRepository;
import org.drive.database.repository.UserRepository;
import org.drive.root.Shell;

public class Rma {

    public void removeAll() {
        try {
            if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
                if (Shell.getConsole().ask("\trma: sure to delete all files.changes can't be undo", true) == 1) {
                    Shell.getConsole().print("\tPassword: ", false);
                    String password = Shell.getConsole().getInput(3, true);

                    password = SecurityTools.hashPlainText(password);
                    password = SecurityTools.encrypt(Shell.getVariables().get("enc"), password);

                    User user = new UserRepository().getUser(Shell.getVariables().get("user"));
                    if (user.getLoginPass().equals(password)) {
                        if (!new FileRepository().removeTableContent("Files")) {
                            Shell.getConsole().print("\trma: failed to delete the table files.", true);
                        }
                    } else {
                        Shell.getConsole().print("\trma: the password is invalid.", true);
                    }
                }
            } else {
                Shell.getConsole().print("\trma: too many arguments.", true);
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }
}
