package org.drive.softwares;

import com.sdk.data.structures.StringList;
import org.drive.root.Shell;

import javax.print.attribute.standard.SheetCollate;

public class Help {

    public void showHelp() {
        String[] help = {
                "\tadd        -> upload new file.\n",
                "\tcat        -> print file content to screen.\n",
                "\tcd         -> enter or exit directory(change directory).\n",
                "\tchanges    -> show software changes from previous version.\n",
                "\tclear      -> clears screen.\n",
                "\tcount      -> count table records.\n",
                "\techo       -> print a variable value or text to screen.\n",
                "\tenvs       -> show system variables.\n",
                "\texit       -> exit software.\n",
                "\tfind       -> find (users,files and histories) easily.\n",
                "\tget        -> downloads file to a location.\n",
                "\thelp       -> show commands help.\n",
                "\thistory    -> show all entered commands.\n",
                "\tid         -> get file id by it's name.\n",
                "\tinfo       -> fetch file information from database.\n",
                "\tlsc        -> show directory content.\n",
                "\tlsf        -> list files in the database.\n",
                "\tlsu        -> list registered users.\n",
                "\tman        -> show command man page.\n",
                "\tmkdir      -> create new directory(s).\n",
                "\tpwd        -> print current working directory.\n",
                "\trm         -> remove a file from database.\n",
                "\trma        -> remove all files in database.\n",
                "\trn         -> rename a file to new name.\n",
                "\tsfi        -> show file information from os filesystem.\n",
                "\tupdate     -> update file content in database.\n",
                "\tuserconfig -> user configuration like username and password.\n",
                "\twc         -> count words,characters and lines of a text file.\n"
        };

        if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
            Shell.getConsole().printLines(help, false);
        } else {
            for (String command : new StringList(false).add(Shell.getParams()).toArray()) {
                switch (command.toLowerCase()) {
                    case "add":
                        Shell.getConsole().print(help[0], false);
                        break;

                    case "cat":
                        Shell.getConsole().print(help[1], false);
                        break;

                    case "cd":
                        Shell.getConsole().print(help[2], false);
                        break;

                    case "changes":
                        Shell.getConsole().print(help[3], false);
                        break;

                    case "clear":
                        Shell.getConsole().print(help[4], false);
                        break;

                    case "count":
                        Shell.getConsole().print(help[5], false);
                        break;

                    case "echo":
                        Shell.getConsole().print(help[6], false);
                        break;

                    case "envs":
                        Shell.getConsole().print(help[7], false);
                        break;

                    case "exit":
                        Shell.getConsole().print(help[8], false);
                        break;

                    case "find":
                        Shell.getConsole().print(help[9], false);
                        break;

                    case "get":
                        Shell.getConsole().print(help[10], false);
                        break;

                    case "help":
                        Shell.getConsole().print(help[11], false);
                        break;

                    case "history":
                        Shell.getConsole().print(help[12], false);
                        break;

                    case "id":
                        Shell.getConsole().print(help[13], false);
                        break;

                    case "info":
                        Shell.getConsole().print(help[14], false);
                        break;

                    case "lsc":
                        Shell.getConsole().print(help[15], false);
                        break;

                    case "lsf":
                        Shell.getConsole().print(help[16], false);
                        break;

                    case "lsu":
                        Shell.getConsole().print(help[17], false);
                        break;

                    case "man":
                        Shell.getConsole().print(help[18], false);
                        break;

                    case "mkdir":
                        Shell.getConsole().print(help[19], false);
                        break;

                    case "pwd":
                        Shell.getConsole().print(help[20], false);
                        break;

                    case "rm":
                        Shell.getConsole().print(help[21], false);
                        break;

                    case "rma":
                        Shell.getConsole().print(help[22], false);
                        break;

                    case "rn":
                        Shell.getConsole().print(help[23], false);
                        break;

                    case "sfi":
                        Shell.getConsole().print(help[24], false);
                        break;

                    case "update":
                        Shell.getConsole().print(help[25], false);
                        break;

                    case "userconfig":
                        Shell.getConsole().print(help[26], false);
                        break;

                    case "wc":
                        Shell.getConsole().print(help[27], false);
                        break;

                    default:
                        Shell.getConsole().print("\thelp: \"" +
                                command.replace("\n",
                                        Shell.getStrings().getEmptyString()) + "\" command not found.", true);
                        break;
                }
            }
        }
    }
}
