package org.drive.root;

import com.sdk.network.NetworkTools;
import com.sdk.storage.file.TextFile;
import com.sdk.tools.ExternalTools;
import org.drive.database.connection.DatabaseConnection;

import java.io.IOException;

public class Boot {

    public Boot loadBuildNumber() {

        try {
            TextFile file = new TextFile("build.txt");

            if (file.exists()) {
                String firstLine = file.readFirstLine(true);

                if (Shell.getStrings().isNumber(firstLine)) {
                    int number = Integer.parseInt(firstLine) + 1;
                    Shell.getVariables().put("build", String.valueOf(number));

                    file.write(String.valueOf(number));
                } else {
                    Shell.getVariables().put("build", "1");
                    file.write("1");
                }
            } else {
                file.create();
                file.write("1");
                Shell.getVariables().put("build", "1");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    public Boot loadSystemTime() {
        Shell.getVariables().put("time", Shell.getOs().getTime());
        return this;
    }

    public Boot loadSystemDate() {
        Shell.getVariables().put("date", Shell.getOs().getDate());
        return this;
    }

    public Boot loadOS() {
        Shell.getVariables().put("os", Shell.getOs().getOSName());
        return this;
    }

    public Boot loadHome() {
        Shell.getVariables().put("home", Shell.getOs().getHomeUser());
        return this;
    }

    public Boot loadDatabaseConnection() {
        DatabaseConnection dbConnection = new DatabaseConnection();
        boolean status = dbConnection.serverConnect();

        if (!status) {
            Shell.printException(ExternalTools.getPrintStackTrace(new Exception("Database connection failed.")), true);
            System.exit(0);
        }

        Shell.getVariables().put("db", String.valueOf(status));
        Shell.setConnection(dbConnection.getConnection());
        return this;
    }

    public Boot loadInternetConnection() {
        boolean status = new NetworkTools("http://www.google.com").internetStatus();

        if (!status) {
            Shell.printException(ExternalTools.getPrintStackTrace(
                    new Exception("Internet is not connected.")), true);
            System.exit(0);
        }

        Shell.getVariables().put("eth", String.valueOf(status));
        return this;
    }

    public Boot loadEncryptionKey() {
        Shell.getVariables().put("enc", "23549918*?");
        return this;
    }

    public Boot loadPath() {
        Shell.getVariables().put("path", Shell.getOs().getHomeUser());
        return this;
    }

    public Boot loadMaxUsers() {
        Shell.getVariables().put("maxusr", "10");
        return this;
    }

    public Boot loadUserSpace() {
        Shell.getVariables().put("space", "104857600");
        return this;
    }

    public Boot checkBoot() {
        return loadSystemDate()
                .loadSystemTime()
                .loadInternetConnection()
                .loadDatabaseConnection()
                .loadOS()
                .loadHome()
                .loadEncryptionKey()
                .loadMaxUsers()
                .loadUserSpace();
    }
}
