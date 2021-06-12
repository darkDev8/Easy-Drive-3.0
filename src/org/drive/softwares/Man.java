package org.drive.softwares;

import com.sdk.data.structures.StringList;
import org.drive.root.Shell;

public class Man {

    public void showManPage() {
        if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
            Shell.getConsole().print("\tman: no arguments set.", true);
        } else {
            for (String command : new StringList(false).add(Shell.getParams())) {
                switch (command.toLowerCase()) {
                    case "add":
                        showHelp("add", "Upload new file,",
                                "Uploads new file to the database, if file already exists asks use to " +
                                        " make sure the valid file has uploaded.", new String[]{"[File path]"},
                                new String[]{"add tmp\\\"new video.mp4\""});

                        break;

                    case "cat":
                        showHelp("cat", "Print file content.", "Open and print file " +
                                        "content to the screen.", new String[]{"[File path]"},
                                new String[]{"cat names.txt"});

                        break;

                    case "cd":
                        showHelp("cd", "Change directory.", "Changes current directory path " +
                                "to go backward or forward or specific directory.",
                                new String[]{"[Directory path]"}, new String[]{"cd /bin/systemctl"});
                        break;

                    case "clear":
                        showHelp("clear", "Clears screen.", "Clears terminal screen or say " +
                                "how many lines to clear.", new String[]{"x -> number of lines."},
                                new String[]{"clear 100"});
                        break;

                    case "count":
                        showHelp("count", "Count table records.", "How many records saved in " +
                                "each table.", new String[]{"Table name."}, new String[]{"count files.", "count users."});
                        break;

                    case "echo":
                        showHelp("echo", "print text to screen.", "Print a system variable " +
                                "value or user text to the screen.\n\tThe variable must start with \"$\" and if you use " +
                                "space in you're text user \"text\".", new String[]{"[Variable name or text]"},
                                new String[]{"echo $path.", "echo \"print text is here.\""});
                        break;

                    case "envs":
                        showHelp("envs", "Show system variables", "Show all system variables.",
                                null, new String[]{"envs."});
                        break;

                    case "exit":
                        showHelp("exit", "Exit software.", "First ask for exit and exits " +
                                "software.", new String[]{"-y/y -> Yes"}, new String[]{"exit -y", "exit y", "exit"});
                        break;

                    case "get":
                        showHelp("get", "Downloads file from database.", "Downloads file from " +
                                "database by it's id.", new String[]{"x -> id of file.", "[Download path]."},
                                new String[]{"get 23 .(here)", "get 64 Documents/prgm."});
                        break;

                    case "help":
                        showHelp("help", "Show commands help.", "Show a command help in one line.",
                                new String[]{"Command name."}, new String[]{"help", "help sfi"});
                        break;

                    case "history":

                        break;

                    case "id":

                        break;

                    case "info":

                        break;

                    case "lsc":

                        break;

                    case "lsf":

                        break;

                    case "lsu":

                        break;

                    case "man":

                        break;

                    case "mkdir":

                        break;

                    case "pwd":

                        break;

                    case "rm":

                        break;

                    case "rma":

                        break;

                    case "rn":

                        break;

                    case "sfi":

                        break;

                    case "update":

                        break;

                    case "wc":

                        break;

                    case "userconfig":
                        break;

                    case "changes":
                        break;

                    default:
                        Shell.getConsole().print("\tman: \"" + command + "\" no man page found.", true);
                        break;
                }
            }
        }
    }

    private void showHelp(String title, String synopsis, String description, String[] options,
                          String[] examples) {

        Shell.getConsole().print("\t(" + title + ")", true);

        Shell.getConsole().print("\t", false);
        Shell.getConsole().printCharacters('-', 45, true);

        Shell.getConsole().print("# Synopsis", true);
        Shell.getConsole().print("\t" + synopsis + "\n", true);

        Shell.getConsole().print("# Description", true);
        Shell.getConsole().print("\t" + description + "\n", true);

        if (!Shell.getStrings().isNullOrEmpty(options)) {
            Shell.getConsole().print("# Options", true);
            for (String option : options) {
                Shell.getConsole().print("\t" + option, true);
            }

            Shell.getConsole().print("\n", false);
        }

        Shell.getConsole().print("# Examples", true);
        for (String example : examples) {
            Shell.getConsole().print("\t" + example, true);
        }

        Shell.getConsole().pressEnter(true, false);
    }
}
