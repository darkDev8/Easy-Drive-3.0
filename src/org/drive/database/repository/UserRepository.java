package org.drive.database.repository;

import com.sdk.database.repository.DatabaseRepository;
import com.sdk.tools.ExternalTools;
import org.drive.database.entity.User;
import org.drive.root.Shell;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class UserRepository implements MainRepository {

    private DatabaseRepository db;

    public UserRepository() {
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
            st.execute("DELETE FROM Users");

            return true;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }

    public boolean userExists(String username) {
        try {
            if (Shell.getVariables().get("dbType").equals("mssql")) {
                return db.searchTable("dbo.[users]", "loginUser", username);
            } else {
                return db.searchTable("users", "loginUser", username);
            }
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }

    public User getUser(String username) {
        try {
            String sql = db.getSelectSQL("users", new String[]{"id", "loginUser", "loginPass",
                            "passHint", "createDate", "space"}, "loginUser = ?", "");
            User user = null;

            PreparedStatement pst = Shell.getConnection().prepareStatement(sql);
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString("loginUser"), rs.getString("loginPass"),
                        rs.getString("passHint"), rs.getString("createDate"));

                user.setId(rs.getInt("id"));
                user.setSpace(rs.getInt("space"));
            }

            return user;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return null;
        }
    }

    public List<Object[]> getUsers() {
        List<Object[]> content = new ArrayList<>();
        String query = db.getSelectSQL("Users", new String[]{"id", "loginUser"}, "", "");

        try {
            PreparedStatement pst = Shell.getConnection().prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                content.add(new Object[]{rs.getInt(1), rs.getString(2)});
            }

            pst.close();
            return content;
        } catch (SQLException e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return null;
        }
    }

    public String getCurrentPassword(String username) {
        try {
            String sql = null, password = null;

            if (Shell.getVariables().get("dbType").equals("mssql")) {
                sql = "SELECT loginPass FROM dbo.[users] WHERE loginUser= ?";
            } else {
                sql = "SELECT loginPass FROM Users WHERE loginUser= ?";
            }
            PreparedStatement pst = Shell.getConnection().prepareStatement(sql);
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                password = rs.getString("loginPass");
            }

            return password;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return null;
        }
    }

    public boolean changeUsername(String currentUsername, String newUsername) {
        try {
            Map<String, String> colValues = new HashMap<>();
            colValues.put("loginUser", newUsername);

            String sql = db.getUpdateSQL("users", colValues, "loginUser = '" + currentUsername + "'");
            Statement statement = Shell.getConnection().createStatement();

            statement.execute(sql);
            return true;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }

    public boolean changePassword(String username, String newPassword) {
        try {
            Map<String, String> colValues = new HashMap<>();
            colValues.put("loginPass", newPassword);

            String sql = db.getUpdateSQL("users", colValues, "loginUser = '" + username + "'");
            Statement statement = Shell.getConnection().createStatement();

            statement.execute(sql);
            return true;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }

    public boolean changePasswordHint(String username, String passwordHint) {
        try {
            Map<String, String> colValues = new HashMap<>();
            colValues.put("passHint", passwordHint);

            String sql = db.getUpdateSQL("users", colValues, "loginUser = '" + username + "'");
            Statement statement = Shell.getConnection().createStatement();

            statement.execute(sql);
            return true;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }
}
