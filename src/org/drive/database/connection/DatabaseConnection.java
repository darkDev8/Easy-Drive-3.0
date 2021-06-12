package org.drive.database.connection;

import com.sdk.database.connections.MysqlConnection;
import com.sdk.database.connections.SqlServerConnection;
import com.sdk.tools.ExternalTools;
import org.drive.root.Shell;

import java.sql.Connection;

public class DatabaseConnection {
    private Connection connection;

    public boolean serverConnect() {
        try {
            connection = new SqlServerConnection("161.97.64.20", "161.97.64.20",
                    "ohgbcqgm_easydrive", "ohgbcqgm_mch8",
                    "?23549918*?A", 1433, 15).getConnection();

            Shell.getVariables().put("dbType", "mssql");
            return true;
        } catch (Exception e){
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }

    public boolean localConnect() {
        try {
            connection = new MysqlConnection("127.0.0.1", 3306, "root", "",
                    "easydrive").getConnection();

            Shell.getVariables().put("dbType", "mysql");
            return true;
        } catch (Exception e){
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}