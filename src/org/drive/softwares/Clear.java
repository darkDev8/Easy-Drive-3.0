package org.drive.softwares;

import com.sdk.storage.base.FileOperation;
import org.drive.root.Shell;

import java.io.IOException;

public class Clear {

    public void clearScreen() {
        if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
            startClear(-1);
        } else if (Shell.getParams().length == 1) {
            if (Shell.getStrings().isNumber(Shell.getParams()[0])) {
                int lines = Integer.parseInt(Shell.getParams()[0]);

                if (lines > 0) {
                    startClear(lines);
                } else {
                    Shell.getConsole().print("\tclear: number of lines is invalid.", true);
                }
            } else {
                Shell.getConsole().print("\tclear: the option must be number.", true);
            }
        } else {
            Shell.getConsole().print("\tclear: too many arguments.", true);
        }
    }

    private void startClear(int lines) {
        Shell.getConsole().setAnimation(false);

        if (lines == -1) {
            Shell.getConsole().clear();
        } else {
            Shell.getConsole().clear(lines);
        }

        Shell.getConsole().setAnimation(true);
    }
}
