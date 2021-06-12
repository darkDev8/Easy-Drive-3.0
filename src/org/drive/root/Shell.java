package org.drive.root;

import com.sdk.data.structures.StringList;
import com.sdk.data.types.Strings;
import com.sdk.storage.base.DirectoryOperation;
import com.sdk.storage.base.FileOperation;
import com.sdk.tools.ExternalTools;
import com.sdk.tools.OperatingSystem;
import com.sdk.console.Console;
import org.apache.commons.compress.archivers.ar.ArArchiveEntry;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Shell {
    private static Strings strings;
    private static OperatingSystem os;
    private static Console console;

    private static Map<String, String> variables;
    private static Connection connection;

    private static String[] params;

    static {
        strings = new Strings();
        os = new OperatingSystem();
        console = new Console(2, 40, true);

        variables = new HashMap<>();
    }

    public static Strings getStrings() {
        return strings;
    }

    public static OperatingSystem getOs() {
        return os;
    }

    public static Console getConsole() {
        return console;
    }

    public static Map<String, String> getVariables() {
        return variables;
    }

    public static String[] getParams() {
        return params;
    }

    public static void setConnection(Connection connection) {
        Shell.connection = connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void optimizeCommand(String command) {
        params = strings.removeElement(
                ExternalTools.optimizeParametersAsList(command.split("\\s+")).toArray(String[]::new), 0);
    }

    public static String changeDirectoryPath(String path) {
        if (os.isWindows()) {
            return changeWindowsDirectory(path);
        } else {
            return changeUnixDirectory(path);
        }
    }

    private static String changeWindowsDirectory(String path) {
        try {
            String currentPath = Shell.getVariables().get("path");
            StringList drives = new StringList(true)
                    .add(Shell.getOs().getPartitionsInfo("letter"));

            if (path.contains("/")) {
                return null;
            }

            if (path.equals(".")) {
                return null;
            }

            if (path.equals("..")) {
                if (drives.contains(currentPath)) {
                    return drives.get(drives.indexOf(currentPath));
                } else {
                    return new DirectoryOperation(currentPath).getDirectoryPath();
                }
            } else {
                if (path.startsWith("..")) {
                    String[] paths = path.split("\\\\");

                    for (String s : paths) {
                        if (s.equals("..")) {
                            currentPath = new DirectoryOperation(currentPath).getDirectoryPath();
                        } else {
                            if (currentPath.endsWith("\\")) {
                                currentPath = currentPath.concat(s);
                            } else {
                                currentPath = currentPath.concat("\\").concat(s);
                            }
                        }
                    }

                    if (new DirectoryOperation(currentPath).exists()) {
                        return currentPath;
                    }

                    return null;
                }

                for (int i = 0; i < drives.size(); i++) {
                    if (drives.get(i).endsWith("\\")) {
                        drives.set(drives.get(i).substring(0, drives.get(i).length() - 1), i);
                    }
                }

                if (drives.contains(path)) {
                    return path.concat("\\");
                } else {
                    if (path.startsWith("\\") || path.endsWith("\\")) {
                        return null;
                    } else {
                        int repeat = Shell.getStrings().countMatches(path, "\\",
                                "\\", false);
                        if (repeat > 1) {
                            path = path.replace("\\".repeat(repeat), "\\");
                        }
                    }

                    if (new DirectoryOperation(path).exists()) {
                        return path;
                    } else {
                        if (currentPath.endsWith("\\")) {
                            currentPath = currentPath.concat(path);
                        } else {
                            currentPath = currentPath.concat("\\").concat(path);
                        }

                        if (new DirectoryOperation(currentPath).exists()) {
                            return currentPath;
                        } else {
                            return null;
                        }
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    private static String changeUnixDirectory(String path) {
        try {
            String currentPath = Shell.getVariables().get("path");
            StringList drives = new StringList(true).add(Shell.getOs().getPartitionsInfo("letter"));

            if (path.contains("\\")) {
                return null;
            }

            if (path.equals(".")) {
                return null;
            }

            if (path.equals("..")) {
                if (drives.contains(currentPath)) {
                    return drives.get(drives.indexOf(currentPath));
                } else {
                    return new DirectoryOperation(currentPath).getDirectoryPath();
                }
            } else {
                if (path.startsWith("..")) {
                    String[] paths = path.split("/");

                    for (String s : paths) {
                        if (s.equals("..")) {
                            currentPath = new DirectoryOperation(currentPath).getDirectoryPath();
                        } else {
                            if (currentPath.endsWith("/")) {
                                currentPath = currentPath.concat(s);
                            } else {
                                currentPath = currentPath.concat("/").concat(s);
                            }
                        }
                    }

                    if (new DirectoryOperation(currentPath).exists()) {
                        return currentPath;
                    }

                    return null;
                }

                for (int i = 0; i < drives.size(); i++) {
                    if (drives.get(i).endsWith("/")) {
                        drives.set(drives.get(i).substring(0, drives.get(i).length() - 1), i);
                    }
                }

                if (drives.contains(path)) {
                    return path.concat("/");
                } else {
                    if (path.startsWith("\\") || path.endsWith("\\")) {
                        return null;
                    } else {
                        int repeat = Shell.getStrings().countMatches(path, "/",
                                "/", false);
                        if (repeat > 1) {
                            path = path.replace("/".repeat(repeat), "/");
                        }
                    }

                    if (new DirectoryOperation(path).exists()) {
                        return path;
                    } else {
                        if (currentPath.endsWith("/")) {
                            currentPath = currentPath.concat(path);
                        } else {
                            currentPath = currentPath.concat("/").concat(path);
                        }

                        if (new DirectoryOperation(currentPath).exists()) {
                            return currentPath;
                        } else {
                            return null;
                        }
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String getFilePath(String path) {
        if (Shell.getOs().isWindows()) {
            return getWindowsFilePath(path);
        } else {
            return getUnixFilePath(path);
        }
    }

    private static String getWindowsFilePath(String path) {
        try {
            String currentPath = Shell.getVariables().get("path");

            if (path.contains("/")) {
                return null;
            }

            if (path.startsWith("\\")) {
                path = path.substring(1);
            }

            if (path.endsWith("\\")) {
                path = path.substring(0, path.length() - 1);
            }

            if (new FileOperation(path).exists()) {
                return path;
            } else {
                if (new FileOperation(currentPath.concat("\\").concat(path)).exists()) {
                    return currentPath.concat("\\").concat(path);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    private static String getUnixFilePath(String path) {
        try {
            String currentPath = Shell.getVariables().get("path");

            if (path.contains("\\")) {
                return null;
            }

            if (path.endsWith("/")) {
                path = path.substring(0, path.length() - 1);
            }

            if (new FileOperation(path).exists()) {
                return path;
            } else {
                if (new FileOperation(currentPath.concat("/").concat(path)).exists()) {
                    return currentPath.concat("/").concat(path);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static void printException(String text, boolean nextLine) {
        if (os.isWindows()) {
            console.print(text, nextLine);
        } else {
            console.printError(text, nextLine);
        }
    }
}
