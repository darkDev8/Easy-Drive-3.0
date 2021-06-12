package org.drive.softwares;

import com.sdk.tools.ExternalTools;
import org.drive.database.repository.FileRepository;
import org.drive.database.repository.UserRepository;
import org.drive.root.Shell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lsf {

    public void listDatabaseFiles() {
        try {
            if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
                FileRepository fr = new FileRepository();

                List<Object[]> files = fr.getFiles(
                        new UserRepository().getUser(Shell.getVariables().get("user")).getId()
                );

                if (!files.isEmpty()) {
                    List<String> names = new ArrayList<>(), sizes = new ArrayList<>(),
                            uploadDates = new ArrayList<>(), ids = new ArrayList<>();

                    for (Object[] record : files) {
                        String[] item = ExternalTools.toStringArray(record);

                        for (int j = 0; j < item.length; j++) {
                            switch (j) {
                                case 0:
                                    ids.add(item[j]);
                                    break;

                                case 1:
                                    names.add(item[j]);
                                    break;

                                case 2:
                                    sizes.add(ExternalTools.toReadableSize(Long.parseLong(item[j])));
                                    break;

                                case 3:
                                    uploadDates.add(item[j]);
                                    break;
                            }
                        }
                    }

                    String biggestId = Collections.max(ids, Comparator.comparing(String::length)),
                            biggestSize = Collections.max(sizes, Comparator.comparing(String::length)),
                            biggestName = Collections.max(names, Comparator.comparing(String::length)),
                            biggestUploadDate = Collections.max(uploadDates, Comparator.comparing(String::length));

                    Shell.getConsole().setAnimation(false);
                    for (int i = 0; i < names.size(); i++) {
                        Shell.getConsole().print("\t" + ids.get(i), false);

                        if (ids.get(i).equals(biggestId)) {
                            Shell.getConsole().print("   ", false);
                        } else {
                            Shell.getConsole().printCharacters(' ',
                                    biggestId.length() - ids.get(i).length() + 3, false);
                        }

                        if (names.get(i).length() > 40) {
                            if (Shell.getOs().isWindows()) {
                                Shell.getConsole().print(names.get(i).substring(0, 37) + "...   ", false);
                            } else {
                                Shell.getConsole().printInformation(names.get(i).substring(0, 37) + "...   ",
                                        false);
                            }
                        } else {
                            if (Shell.getOs().isWindows()) {
                                Shell.getConsole().print(names.get(i), false);
                            } else {
                                Shell.getConsole().printInformation(names.get(i), false);
                            }

                            if (biggestName.length() > 40) {
                                Shell.getConsole().printCharacters(' ',
                                        40 - names.get(i).length() + 3, false);
                            } else {
                                Shell.getConsole().printCharacters(' ',
                                        biggestName.length() - names.get(i).length() + 3, false);
                            }
                        }

                        Shell.getConsole().print(sizes.get(i), false);

                        if (sizes.get(i).equals(biggestSize)) {
                            Shell.getConsole().print("   ", false);
                        } else {
                            Shell.getConsole().printCharacters(' ',
                                    biggestSize.length() - sizes.get(i).length() + 3, false);
                        }

                        Shell.getConsole().print(uploadDates.get(i), false);

                        if (uploadDates.get(i).equals(biggestUploadDate)) {
                            Shell.getConsole().print("   ", true);
                        } else {
                            Shell.getConsole().printCharacters(' ',
                                    biggestUploadDate.length() - uploadDates.get(i).length() + 3, true);
                        }
                    }

                    Shell.getConsole().setAnimation(true);
                }
            } else {
                Shell.getConsole().print("\tlsf: too many arguments.", true);
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }
}
