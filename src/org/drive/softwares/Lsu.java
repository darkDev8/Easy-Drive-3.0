package org.drive.softwares;

import com.sdk.tools.ExternalTools;
import org.drive.database.repository.UserRepository;
import org.drive.root.Shell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lsu {

    public void listUsers() {
        if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
            List<Object[]> data = new UserRepository().getUsers();
            int space = 4;

            if (!data.isEmpty()) {
                List<String> ids = new ArrayList<>(), users = new ArrayList<>();

                for (Object[] record : data) {
                    String[] item = ExternalTools.toStringArray(record);

                    for (int j = 0; j < item.length; j++) {
                        switch (j) {
                            case 0:
                                ids.add(item[j]);
                                break;

                            case 1:
                                users.add(item[j]);
                                break;
                        }
                    }
                }

                String biggestId = Collections.max(ids, Comparator.comparing(String::length)),
                        biggestUser = Collections.max(users, Comparator.comparing(String::length));

                int x = ((biggestId.length() - "Id".length()) / 2);
                Shell.getConsole().print("\t", false);

                Shell.getConsole().printCharacters(' ', x, false);
                Shell.getConsole().print("Id", false);
                Shell.getConsole().printCharacters(' ',
                        biggestId.length() - "Id".length() + space - x, false);

                x = ((biggestUser.length() - "User".length()) / 2);
                Shell.getConsole().printCharacters(' ', x, false);

                Shell.getConsole().print("User", false);
                Shell.getConsole().printCharacters(' ',
                        biggestUser.length() - "User".length() + space - x, true);

                Shell.getConsole().print("\t", false);
                Shell.getConsole().printCharacters('-', biggestId.length(), false);

                Shell.getConsole().printCharacters(' ', space, false);
                Shell.getConsole().printCharacters('-', biggestUser.length(), true);

                for (int i = 0; i < ids.size(); i++) {
                    Shell.getConsole().print("\t" + ids.get(i), false);

                    if (ids.get(i).equals(biggestId)) {
                        Shell.getConsole().printCharacters(' ', space, false);
                    } else {
                        Shell.getConsole().printCharacters(' ',
                                biggestId.length() - ids.get(i).length() + space, false);
                    }

                    if (Shell.getOs().isWindows()) {
                        Shell.getConsole().print(users.get(i), true);
                    } else {
                        Shell.getConsole().printInformation(users.get(i), true);
                    }
                }
            }

        } else {
            Shell.getConsole().print("\tlsu: too many arguments.", true);
        }
    }
}
