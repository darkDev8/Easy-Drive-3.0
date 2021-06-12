package org.drive.main;

import com.sdk.console.ConsoleForeground;
import com.sdk.data.structures.StringList;
import com.sdk.database.repository.DatabaseRepository;
import com.sdk.security.SecurityTools;
import com.sdk.storage.base.DirectoryOperation;
import com.sdk.storage.file.TextFile;
import com.sdk.tools.ExternalTools;
import org.drive.database.entity.History;
import org.drive.database.entity.User;
import org.drive.database.repository.HistoryRepository;
import org.drive.database.repository.UserRepository;
import org.drive.root.Boot;
import org.drive.root.Shell;
import org.drive.root.SoftwareSelection;

public class Engine {
    private static String command;

    public static void main(String[] args) {
        try {
            new Boot().checkBoot().loadBuildNumber().loadPath();
            StringList drives = new StringList(false).add(
                    Shell.getOs().getPartitionsInfo("letter")
            ), validCommands = new StringList(true)
                    .add("add").add("cat").add("cd").add("clear").add("count")
                    .add("echo").add("envs").add("exit").add("get").add("help")
                    .add("history").add("id").add("info").add("lsc").add("lsf")
                    .add("lsu").add("man").add("mkdir").add("pwd").add("rm")
                    .add("rma").add("rn").add("sfi").add("update").add("wc")
                    .add("cls").add("userconfig");

            args = new String[3];
            args[0] = "mahdi";
            args[1] = "23549918";
            args[2] = "4H/eZQjV2cn66nbOU32pKg==";

            if (args.length != 3) {
                Shell.getConsole().print("\tlogin: the arguments are invalid.", true);
                System.exit(1);
            }

            args = ExternalTools.optimizeParametersAsList(args).toArray(String[]::new);
            if (!login(args[0], args[1], args[2])) {
                System.exit(1);
            }

            titleMenu();

            while (true) {
                mainMenu();
                command = Shell.getConsole().getInput(3, true);

                if (!Shell.getStrings().isNullOrEmpty(command)) {
                    if (validCommands.contains(command.split("\\s+")[0])) {
                        History history = new org.drive.database.entity.History(command, Shell.getOs().getDate());
                        history.setFid(new UserRepository().getUser(Shell.getVariables().get("user")).getId());

                        HistoryRepository hr = new HistoryRepository();
                        if (!hr.historyExists(command, "command")) {
                            hr.addHistory(history);
                        }
                    }
                }

                switch (command.split("\\s+")[0].toLowerCase()) {
                    case "exit":
                        new SoftwareSelection(command).exit();
                        break;

                    case "pwd":
                        new SoftwareSelection(command).pwd();
                        break;

                    case "envs":
                        new SoftwareSelection(command).envs();
                        break;

                    case "echo":
                        new SoftwareSelection(command).echo();
                        break;

                    case "cd":
                        new SoftwareSelection(command).cd();
                        break;

                    case "lsc":
                    case "ls":
                        new SoftwareSelection(command).lsc();
                        break;

                    case "clear":
                    case "cls":
                        new SoftwareSelection(command).clear();
                        break;

                    case "wc":
                        new SoftwareSelection(command).wc();
                        break;

                    case "count":
                        new SoftwareSelection(command).count();
                        break;

                    case "id":
                        new SoftwareSelection(command).id();
                        break;

                    case "add":
                        new SoftwareSelection(command).add();
                        break;

                    case "cat":
                        new SoftwareSelection(command).cat();
                        break;

                    case "rma":
                        new SoftwareSelection(command).rma();
                        break;

                    case "sfi":
                        new SoftwareSelection(command).sfi();
                        break;

                    case "rm":
                        new SoftwareSelection(command).rm();
                        break;

                    case "get":
                        new SoftwareSelection(command).get();
                        break;

                    case "rn":
                        new SoftwareSelection(command).rn();
                        break;

                    case "lsf":
                        new SoftwareSelection(command).lsf();
                        break;

                    case "history":
                        new SoftwareSelection(command).history();
                        break;

                    case "info":
                        new SoftwareSelection(command).info();
                        break;

                    case "lsu":
                        new SoftwareSelection(command).lsu();
                        break;

                    case "update":
                        new SoftwareSelection(command).update();
                        break;

                    case "mkdir":
                        new SoftwareSelection(command).mkdir();
                        break;

                    case "help":
                        new SoftwareSelection(command).help();
                        break;

                    case "man":
                        new SoftwareSelection(command).man();
                        break;

                    case "userconfig":
                        new SoftwareSelection(command).userconfig();
                        break;

                    case "changes":
                        new SoftwareSelection(command).changes();
                        break;

                    case "find":
                        new SoftwareSelection(command).find();
                        break;

                    default:
                        if (!Shell.getStrings().isNullOrEmpty(command)) {
                            if (drives.contains(command)) {
                                Shell.getVariables().replace("path", Shell.getVariables().get("path"), command);
                            } else {
                                String path = Shell.changeDirectoryPath(command);

                                if (Shell.getStrings().isNullOrEmpty(path)) {
                                    Shell.getConsole().print("\t\"" + command + "\" command not found.", true);
                                } else {
                                    if (new DirectoryOperation(path).exists()) {
                                        Shell.getVariables().replace("path", Shell.getVariables().get("path"), path);
                                    } else {
                                        Shell.getConsole().print("\t\"" + command + "\" command not found.", true);
                                    }
                                }
                            }
                        }

                        break;
                }
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }

    private static void titleMenu() {
        String[] title = {
                "/+++\n"
                        + "+    ███████╗ █████╗ ███████╗██╗   ██╗    ██████╗ ██████╗ ██╗██╗   ██╗███████╗\n"
                        + "+    ██╔════╝██╔══██╗██╔════╝╚██╗ ██╔╝    ██╔══██╗██╔══██╗██║██║   ██║██╔════╝\n"
                        + "+    █████╗  ███████║███████╗ ╚████╔╝     ██║  ██║██████╔╝██║██║   ██║█████╗\n"
                        + "+    ██╔══╝  ██╔══██║╚════██║  ╚██╔╝      ██║  ██║██╔══██╗██║╚██╗ ██╔╝██╔══╝\n"
                        + "+    ███████╗██║  ██║███████║   ██║       ██████╔╝██║  ██║██║ ╚████╔╝ ███████╗\n"
                        + "+    ╚══════╝╚═╝  ╚═╝╚══════╝   ╚═╝       ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝  ╚══════╝ "
        };

        Shell.getConsole().printLines(title, false);
        Shell.getConsole().printError("3.7.39", true);

        Shell.getConsole().print("+ ", true);

        Shell.getConsole().print("+ ", false);
        Shell.getConsole().print("   Created by \"mahdiDedsec\" (build " +
                Shell.getVariables().get("build") + ")\n", true);

        if (!Shell.getOs().isWindows()) {
            Shell.getConsole().print(ConsoleForeground.RESET, false);
        }
    }

    private static void mainMenu() {
        try {
            String path = Shell.getVariables().get("path");

            Shell.getConsole().print("[" + Shell.getVariables().get("user"), false);

            StringList list = new StringList(true)
                    .add(Shell.getOs().getPartitionsInfo("letter"));

            if (Shell.getOs().isWindows()) {
                Shell.getConsole().print("# ", false);

                if (list.contains(path)) {
                    Shell.getConsole().print(list.get(list.indexOf(path)), false);
                } else {
                    if (path.equals(Shell.getVariables().get("home"))) {
                        Shell.getConsole().print("~", false);
                    } else {
                        path = path.replace(Shell.getVariables().get("home"), "~");
                        Shell.getConsole().print(path, false);
                    }
                }
            } else {
                Shell.getConsole().print(" \uD83D\uDC80 ", false);

                if (list.contains(path)) {
                    Shell.getConsole().printError(list.get(list.indexOf(path)), false);
                } else {
                    if (path.equals(Shell.getVariables().get("home"))) {
                        Shell.getConsole().printError("~", false);
                    } else {
                        path = path.replace(Shell.getVariables().get("home"), "~");
                        Shell.getConsole().printError(path, false);
                    }
                }
            }

            Shell.getConsole().print("] ", false);
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }

    private static boolean login(String username, String password, String key) {
        try {
            if (SecurityTools.decrypt(Shell.getVariables().get("enc"), key).equals("fuck off \uD83D\uDD95")) {
                if (Shell.getStrings().isNullOrEmpty(username) || Shell.getStrings().isNullOrEmpty(password)) {
                    Shell.getConsole().print("\tlogin: the username or password is empty.", true);
                    System.exit(1);
                }

                UserRepository ur = new UserRepository();
                username = username.toLowerCase();

                if (ur.userExists(username)) {
                    User user = ur.getUser(username);
                    String dbPassword = SecurityTools.decrypt(Shell.getVariables().get("enc"), user.getLoginPass());

                    if (SecurityTools.hashPlainText(password).equals(dbPassword)) {
                        Shell.getVariables().put("user", username);
                        return true;
                    } else {
                        Shell.getConsole().print("\tlogin: password is invalid.", true);
                        System.exit(1);
                    }
                } else {
                    Shell.getConsole().print("\tlogin: username is invalid.", true);
                    System.exit(1);
                }
            } else {
                Shell.getConsole().print("\tlogin: input key is invalid.", true);
                System.exit(1);
            }

            return false;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }
}
