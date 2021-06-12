package org.drive.softwares;

import com.sdk.data.structures.StringList;
import com.sdk.storage.base.DirectoryOperation;
import com.sdk.storage.base.FileOperation;
import com.sdk.tools.ExternalTools;
import org.drive.root.Shell;

import java.io.File;

public class Lsc {

    public void listFiles() {
        try {
            boolean hidden = false, small = false;
            StringBuilder listType = new StringBuilder();

            if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
                startList(hidden, small, null);
            } else if (Shell.getParams().length == 1) {
                if (!Shell.getParams()[0].startsWith("-")) {
                    Shell.getConsole().print("\tlsc: invalid argument.", true);
                } else {
                    if (Shell.getParams()[0].equals("-")) {
                        Shell.getConsole().print("\tlsc: invalid option argument.", true);
                    } else {
                        String params = Shell.getParams()[0].substring(1);

                        for (char c : params.toCharArray()) {
                            switch (c) {
                                case 'h':
                                    hidden = true;
                                    break;

                                case 'f':
                                    listType.append("f");
                                    break;

                                case 'd':
                                    listType.append("d");
                                    break;

                                case 's':
                                    small = true;
                                    break;

                                default:
                                    Shell.getConsole().print("\tlsc: invalid option argument.", true);
                                    return;
                            }
                        }

                        startList(hidden, small, listType.toString());
                    }
                }
            } else {
                Shell.getConsole().print("\tlsc: too many arguments.", true);
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }

    private void startList(boolean hidden, boolean small, String type) {
        try {
            StringList users = new StringList(true), createDates = new StringList(true),
                    sizes = new StringList(true), names = new StringList(true);

            int space = 4;

            DirectoryOperation ds = new DirectoryOperation(Shell.getVariables().get("path"));
            File[] files = ds.getDirectoryContent().toArray(new File[0]);

            if (!Shell.getStrings().isNullOrEmpty(type)) {
                if (type.contains("f") && type.contains("d")) {
                    Shell.getConsole().print("\tlsc: invalid list type selection(f/d).", true);
                    return;
                }

                if (type.equals("f")) {
                    files = ds.getFiles().toArray(new File[0]);
                } else if (type.equals("d")) {
                    files = ds.getDirectories().toArray(new File[0]);
                }
            }

            for (File file : files) {
                FileOperation fp = new FileOperation(file.getAbsolutePath());

                if (hidden) {
                    users.add(fp.getOwner());
                    createDates.add(fp.getCreateDate());
                    sizes.add(fp.getReadableSize());
                    names.add(fp.getName());
                } else {
                    if (!new File(fp.getPath()).isHidden()) {
                        users.add(fp.getOwner());
                        createDates.add(fp.getCreateDate());
                        sizes.add(fp.getReadableSize());
                        names.add(fp.getName());
                    }
                }
            }

            if (!names.isEmpty()) {
                String biggestUser = users.getBiggest(), biggestCreateDate = createDates.getBiggest(),
                        biggestSize = sizes.getBiggest(), biggestName = names.getBiggest();

                Shell.getConsole().setAnimation(false);

                Shell.getConsole().print("\t", false);
                int x = ((biggestUser.length() - "User".length()) / 2);

                Shell.getConsole().printCharacters(' ', x, false);
                Shell.getConsole().print("User", false);
                Shell.getConsole().printCharacters(' ',
                        biggestUser.length() - "User".length() + space - x, false);

                Shell.getConsole().print("Create date", false);
                Shell.getConsole().printCharacters(' ',
                        biggestCreateDate.length() - "Create date".length() + space, false);

                x = ((biggestSize.length() - "Size".length()) / 2);
                Shell.getConsole().printCharacters(' ', x, false);

                Shell.getConsole().print("Size", false);
                Shell.getConsole().printCharacters(' ',
                        biggestSize.length() - "Size".length() + space - x, false);

                x = ((biggestName.length() - "Name".length()) / 2);
                Shell.getConsole().printCharacters(' ', x, false);

                Shell.getConsole().print("Name", false);
                Shell.getConsole().printCharacters(' ',
                        biggestName.length() - "Name".length() + space - x, true);

                Shell.getConsole().print("\t", false);
                Shell.getConsole().printCharacters('-', biggestUser.length(), false);

                Shell.getConsole().printCharacters(' ', space, false);
                Shell.getConsole().printCharacters('-', biggestCreateDate.length(), false);

                Shell.getConsole().printCharacters(' ', space, false);
                Shell.getConsole().printCharacters('-', biggestSize.length(), false);

                Shell.getConsole().printCharacters(' ', space, false);
                Shell.getConsole().printCharacters('-', biggestName.length(), true);

                for (int i = 0; i < users.size(); i++) {
                    Shell.getConsole().print("\t", false);

                    Shell.getConsole().print(users.get(i), false);
                    if (users.get(i).equals(biggestUser)) {
                        Shell.getConsole().print("    ", false);
                    } else {
                        Shell.getConsole().printCharacters(' ', biggestUser.length() - users.get(i).length() + space, false);
                    }

                    Shell.getConsole().print(createDates.get(i), false);
                    if (createDates.get(i).equals(biggestCreateDate)) {
                        Shell.getConsole().print("    ", false);
                    } else {
                        Shell.getConsole().printCharacters(' ', biggestCreateDate.length() - createDates.get(i).length() + space, false);
                    }

                    Shell.getConsole().print(sizes.get(i), false);
                    if (sizes.get(i).equals(biggestSize)) {
                        Shell.getConsole().print("    ", false);
                    } else {
                        Shell.getConsole().printCharacters(' ', biggestSize.length() - sizes.get(i).length() + space, false);
                    }

                    if (small) {
                        FileOperation fp = null;

                        if (Shell.getOs().isWindows()) {
                            fp = new FileOperation(ds.getPath().concat("\\").concat(names.get(i)));
                        } else {
                            fp = new FileOperation(ds.getPath().concat("/").concat(names.get(i)));
                        }

                        if (fp.getBaseName().length() > 30) {
                            if (Shell.getOs().isWindows()) {
                                Shell.getConsole().print(fp.getBaseName().substring(27).concat("..."), true);
                            } else {
                                Shell.getConsole().printInformation(fp.getBaseName().substring(27).concat("..."), true);
                            }
                        } else {
                           if (Shell.getOs().isWindows()) {
                               Shell.getConsole().print(names.get(i), true);
                           } else {
                               Shell.getConsole().printInformation(names.get(i), true);
                           }
                        }
                    } else {
                        if (Shell.getOs().isWindows()) {
                            Shell.getConsole().print(names.get(i), true);
                        } else {
                            Shell.getConsole().printInformation(names.get(i), true);
                        }
                    }
                }

                Shell.getConsole().setAnimation(true);
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }
}
