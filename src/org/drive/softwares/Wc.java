package org.drive.softwares;

import com.sdk.storage.file.TextFile;
import com.sdk.tools.ExternalTools;
import org.drive.root.Shell;

public class Wc {

    public void wordCount() {
        try {
            boolean lines = false, words = false, characters = false;

            if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
                Shell.getConsole().print("\twc: no arguments set.", true);
            } else if (Shell.getParams().length == 1) {
                String path = Shell.getFilePath(Shell.getParams()[0]);

                if (Shell.getStrings().isNullOrEmpty(path)) {
                    Shell.getConsole().print("\twc: \"" + Shell.getParams()[0] + "\" no such file or directory.", true);
                } else {
                    processFile(path, true, true, true);
                }
            } else if (Shell.getParams().length == 2) {
                String path = Shell.getFilePath(Shell.getParams()[0]);

                if (Shell.getStrings().isNullOrEmpty(path)) {
                    Shell.getConsole().print("\twc: \"" + Shell.getParams()[0] + "\" no such file or directory.", true);
                } else {
                    if (!Shell.getParams()[1].startsWith("-")) {
                        Shell.getConsole().print("\twc: invalid argument.", true);
                    } else {
                        if (Shell.getParams()[1].equals("-")) {
                            Shell.getConsole().print("\twc: invalid option argument.", true);
                        } else {
                            String param = Shell.getParams()[1].substring(1);

                            for (char c : param.toCharArray()) {
                                switch (c) {
                                    case 'l':
                                        lines = true;
                                        break;

                                    case 'w':
                                        words = true;
                                        break;

                                    case 'c':
                                        characters = true;
                                        break;

                                    default:
                                        Shell.getConsole().print("\twc: invalid option argument.", true);
                                        return;
                                }
                            }

                            processFile(path, lines, words, characters);
                        }
                    }
                }
            } else {
                Shell.getConsole().print("\twc: too many arguments.", true);
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }

    private void processFile(String path, boolean lines, boolean words, boolean characters) {
        try {
            TextFile file = new TextFile(path);
            int line = 0, word = 0, character = 0;

            if (file.exists()) {
                if (lines) {
                    Shell.getConsole().print("\t lines: " + file.countLines(), true);
                }

                if (words) {
                    Shell.getConsole().print("\t words: " + file.countWords(), true);
                }

                if (characters) {
                    Shell.getConsole().print("\t characters: " + file.countCharacters(false), true);
                }
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }
}
