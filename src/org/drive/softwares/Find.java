package org.drive.softwares;

import com.sdk.data.structures.StringList;
import com.sdk.tools.ExternalTools;
import org.drive.database.entity.File;
import org.drive.database.entity.History;
import org.drive.database.repository.FileRepository;
import org.drive.database.repository.HistoryRepository;
import org.drive.database.repository.UserRepository;
import org.drive.root.Shell;

import java.util.List;
import java.util.Objects;

public class Find {

    public void findItems() {
        if (Shell.getParams().length < 2) {
            Shell.getConsole().print("\tfind: few many arguments.", true);
        } else if (Shell.getParams().length > 2) {
            Shell.getConsole().print("\tfind: too many arguments.", true);
        } else {
            StringList list = new StringList(false).add("n").add("i").add("u").add("h");
            String type = null, param = null;

            for (String parameter : Shell.getParams()) {
                if (parameter.startsWith("-")) {
                    parameter = parameter.substring(1);

                    for (char c : parameter.toCharArray()) {
                        if (c == 'n' || c == 'i' || c == 'u' || c == 'h') {
                            if (Objects.isNull(type)) {
                                type = String.valueOf(c);
                            } else {
                                type += c;
                            }
                        } else {
                            Shell.getConsole().print("\tfind: invalid search type specified.", true);
                            return;
                        }
                    }
                } else {
                    param = parameter;
                }
            }

            if (Objects.isNull(type)) {
                Shell.getConsole().print("\tfind: no search type found.", true);
            } else {
                if (type.length() > 1) {
                    Shell.getConsole().print("\tfind: only one search type can be specified.", true);
                } else {
                    FileRepository fr = new FileRepository();
                    HistoryRepository hr = new HistoryRepository();
                    UserRepository ur = new UserRepository();

                    switch (type) {
                        case "n": {
                            if (fr.fileExists(param, "name")) {
                                File file = fr.getFile(String.valueOf(fr.getFileId(param)), false);

                                Shell.getConsole().print("\tname: " + file.getName(), true);
                                Shell.getConsole().print("\tsize: " + ExternalTools.toReadableSize(file.getSize()), true);
                                Shell.getConsole().print("\tupload date: " + file.getUploadDate(), true);
                                Shell.getConsole().print("\tcreate date: " + file.getCreateDate(), true);
                                Shell.getConsole().print("\tcategory: " + file.getCategory(), true);
                            }
                        }

                        break;

                        case "i": {
                            if (fr.fileExists(param, "id")) {
                                File file = fr.getFile(param, false);

                                Shell.getConsole().print("\tname: " + file.getName(), true);
                                Shell.getConsole().print("\tsize: " + ExternalTools.toReadableSize(file.getSize()), true);
                                Shell.getConsole().print("\tupload date: " + file.getUploadDate(), true);
                                Shell.getConsole().print("\tcreate date: " + file.getCreateDate(), true);
                                Shell.getConsole().print("\tcategory: " + file.getCategory(), true);
                            }
                        }

                        break;

                        case "u": {
                            if (ur.userExists(param)) {
                                Shell.getConsole().print("\tfind: user exists.", true);
                            } else {
                                Shell.getConsole().print("\tfind: no username found.", true);
                            }
                        }

                        break;

                        case "h": {
                            if (hr.historyExists(param, "command")) {
                                Shell.getConsole().print("\tfind: command history found.\n\t" + param, true);
                            } else {
                                Shell.getConsole().print("\tfind: no history command found.", true);
                            }
                        }

                        break;
                    }
                }
            }
        }
    }
}
