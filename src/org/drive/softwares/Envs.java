package org.drive.softwares;

import com.sdk.data.structures.StringList;
import org.drive.root.Boot;
import org.drive.root.Shell;

public class Envs {

    public void printVariables() {
        if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
            StringList list = new StringList(true);
            new Boot().checkBoot();

            Shell.getVariables().replace("enc", "\uD83D\uDD95");

            Shell.getVariables().forEach((key, value) -> list.add(key));
            String biggest = list.getBiggest();

            Shell.getVariables().entrySet().stream().peek(entry ->
                    Shell.getConsole().print("\t" + entry.getKey(), false)).peek(entry -> {
                if (entry.getKey().equals(biggest)) {
                    Shell.getConsole().print("   ", false);
                } else {
                    Shell.getConsole().printCharacters(' ', biggest.length() - entry.getKey().length() + 3, false);
                }
            }).forEachOrdered(entry -> {
                Shell.getConsole().print(entry.getValue(), true);
            });

            Shell.getVariables().replace("enc", "\uD83D\uDD95", "23549918?*");
        } else {
            Shell.getConsole().print("\tenvs: too many arguments.", true);
        }
    }
}
