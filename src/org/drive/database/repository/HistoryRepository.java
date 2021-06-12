package org.drive.database.repository;

import com.sdk.database.repository.DatabaseRepository;
import com.sdk.tools.ExternalTools;
import org.drive.database.entity.File;
import org.drive.root.Shell;
import org.drive.database.entity.History;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoryRepository implements MainRepository {

    private DatabaseRepository db;

    public HistoryRepository() {
        db = new DatabaseRepository(Shell.getConnection());
    }

    @Override
    public long countTable(String table) {
        try {
            return db.countRecords(table);
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return -1;
        }
    }

    @Override
    public boolean removeTableContent(String table) {
        try {
            Statement st = Shell.getConnection().createStatement();
            st.execute("DELETE FROM History");

            return true;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }

    public String[] getAllHistory() {
        try {
            String sql = db.getSelectSQL("History", new String[]{"command"}, "", "");
            List<String> commands = new ArrayList<>();

            PreparedStatement pst = Shell.getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                commands.add(rs.getString("command"));
            }

            return commands.toArray(String[]::new);
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return null;
        }
    }

    public void addHistory(History history) {
        try {
            if (!Objects.isNull(history)) {
                String sql = "INSERT INTO History (fid,command,date) VALUES(?,?,?)";

                PreparedStatement pst = Shell.getConnection().prepareStatement(sql);
                pst.setInt(1, history.getFid());
                pst.setString(2, history.getCommand());
                pst.setString(3, history.getDate());

                pst.execute();
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
        }
    }

    public boolean historyExists(String data, String column) {
        try {
            return db.searchTable("History", column, data);
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }

    public History getHistory(String command) {
        try {
            String sql = db.getSelectSQL("History", new String[]{"id", "fid", "command", "date"}, "command = '" + command + "'",
                    null);

            History history = null;

            PreparedStatement pst = Shell.getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                history = new History(rs.getString("command"), rs.getString("date"));
                history.setId(rs.getInt("id"));
                history.setId(rs.getInt("fid"));
            }

            return history;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return null;
        }
    }
}
