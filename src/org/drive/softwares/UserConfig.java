package org.drive.softwares;

import com.mysql.cj.protocol.Security;
import com.sdk.security.SecurityTools;
import com.sdk.tools.ExternalTools;
import org.drive.database.repository.UserRepository;
import org.drive.root.Shell;

public class UserConfig {

    public void configUser() {
        try {
            if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
                Shell.getConsole().print("\n\t[1]. username\t", true);
                Shell.getConsole().print("\t[2]. password", true);
                Shell.getConsole().print("\t[3]. password hint", true);

                Shell.getConsole().print("\t", false);
                Shell.getConsole().printCharacters('-', 25, true);

                String selection = Shell.getConsole().getInput("\tselection: ", 3, true);
                if (Shell.getStrings().isNullOrEmpty(selection)) {
                    Shell.getConsole().print("\tuserconfig: aborted.", true);
                } else {
                    switch (selection) {
                        case "1":
                            changeUsername();
                            break;
                        case "2":
                            changePassword();
                            break;
                        case "3":
                            changePasswordHint();
                            break;
                    }
                }
            } else {
                Shell.getConsole().print("\tuserconfig: too many arguments.", true);
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }

    private void changeUsername() {
        String username = Shell.getConsole().getInput("\tnew username: ", 1, true),
                currentUsername = Shell.getVariables().get("user");

        if (Shell.getStrings().isNullOrEmpty(username)) {
            Shell.getConsole().print("\n\tuserconfig: username can't be null.", true);
        } else {
            UserRepository ur = new UserRepository();

            if (!username.equals(currentUsername)) {
                if (ur.userExists(username)) {
                    Shell.getConsole().print("\n\tuserconfig: username already exists.", true);
                } else {
                    if (ur.changeUsername(currentUsername, username)) {
                        Shell.getVariables().replace("user", currentUsername, username);
                    } else {
                        Shell.getConsole().print("\n\tuserconfig: failed to change username.", true);
                    }
                }
            }
        }
    }

    private void changePassword() throws Exception {
        UserRepository ur = new UserRepository();
        String currentPassword = null, newPassword = null, dbPassword = ur.getCurrentPassword(
                Shell.getVariables().get("user")
        );

        currentPassword = Shell.getConsole().getInput("\tcurrent password: " , 3, true);
        currentPassword = SecurityTools.hashPlainText(currentPassword);
        currentPassword = SecurityTools.encrypt(Shell.getVariables().get("enc"), currentPassword);

        if (currentPassword.equals(dbPassword)) {
            newPassword = Shell.getConsole().getInput("\tnew password: ", 3, true);

            if (!Shell.getStrings().isNullOrEmpty(newPassword)) {
                newPassword = SecurityTools.hashPlainText(newPassword);
                newPassword = SecurityTools.encrypt(Shell.getVariables().get("enc"), newPassword);

                if (!ur.changePassword(Shell.getVariables().get("user"), newPassword)) {
                    Shell.getConsole().print("\n\tuserconfig: failed to change the user password.", true);
                }
            } else {
                Shell.getConsole().print("\n\tuserconfig: password can't be null.", true);
            }
        } else {
            Shell.getConsole().print("\n\tuserconfig: current password is incorrect.", true);
        }
    }

    private void changePasswordHint() {
        String passwordHint = Shell.getConsole().getInput("\tnew password hint: ", 1, true);

        if (!new UserRepository().changePasswordHint(Shell.getVariables().get("user"), passwordHint)) {
            Shell.getConsole().print("\n\tuserconfig: failed to change password hint.", true);
        }
    }
}
