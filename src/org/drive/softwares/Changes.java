package org.drive.softwares;

import org.drive.root.Shell;

public class Changes {

    public void showChanges() {
        if (Shell.getStrings().isNullOrEmpty(Shell.getParams())) {
            String [] changes = {
                    "New prompt and logo color.",
                    "Add skull emoji to the prompt for unix systems.",
                    "Add new find command for search(files,users and histories).",
                    "Add new changes command to show software changes and new updates that applied to this version.",
                    "New ls alias for lsc command."
            };

            Shell.getConsole().printList(changes, "\t", true, true);
        } else {
            Shell.getConsole().print("\tchanges: too many arguments.", true);
        }
    }
}
