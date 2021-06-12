package org.drive.database.repository;

import com.sdk.database.repository.DatabaseRepository;
import com.sdk.tools.ExternalTools;
import org.drive.database.entity.File;
import org.drive.database.entity.User;
import org.drive.root.Shell;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class FileRepository implements MainRepository {

    private DatabaseRepository db;

    public FileRepository() {
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
            st.execute("DELETE FROM Files");

            return true;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }

    public boolean fileExists(String data, String column) {
        try {
            return db.searchTable("Files", column, data);
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }

    public int getFileId(String name) {
        try {
            if (!fileExists(name, "name")) {
                return -1;
            }

            String sql = db.getSelectSQL("Files", new String[]{"id"}, "name = '" + name + "'", "");
            int id = 0;

            PreparedStatement pst = Shell.getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
            }

            return id;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return -1;
        }
    }

    public boolean addFile(File file) {
        try {
            String sql = "INSERT INTO Files (name,size,createDate,uploadDate,category,content,fid) " +
                    "VALUES (?,?,?,?,?,?,?)";

            PreparedStatement pst = Shell.getConnection().prepareStatement(sql);
            pst.setString(1, file.getName());
            pst.setLong(2, file.getSize());
            pst.setString(3, file.getCreateDate());
            pst.setString(4, file.getUploadDate());
            pst.setString(5, file.getCategory());
            pst.setBytes(6, file.getContent());
            pst.setInt(7, file.getFid());

            pst.execute();
            return true;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }

    public boolean deleteFile(String data, String column) {
        try {
            String sql = db.getDeleteSQL("Files", column + "= '" + data + "'");
            Statement st = Shell.getConnection().createStatement();

            st.execute(sql);
            return true;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }

    public File getFile(String id, boolean getContent) {
        try {
            String sql = null;

            if (getContent) {
                sql = db.getSelectSQL("Files", new String[]{"id", "fid", "name",
                        "size", "content", "uploadDate", "createDate", "category"}, "id = '"
                        + id + "'", "");
            } else {
                sql = db.getSelectSQL("Files", new String[]{"id", "fid", "name",
                        "size", "uploadDate", "createDate", "category"}, "id = '"
                        + id + "'", "");
            }
            File file = null;

            PreparedStatement pst = Shell.getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                file = new File(rs.getLong("size"), rs.getString("name"), rs.getString("createDate"),
                        rs.getString("uploadDate"), rs.getString("category"));

                file.setId(rs.getInt("id"));
                file.setFid(rs.getInt("fid"));

                if (getContent) {
                    file.setContent(rs.getBytes("content"));
                }
            }

            return file;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return null;
        }
    }

    public boolean renameFile(String id, String name) {
        try {
            PreparedStatement pst = Shell.getConnection().prepareStatement("UPDATE Files SET name=? WHERE id=?");
            pst.setString(1, name);
            pst.setString(2, id);

            pst.execute();
            return true;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }

    public boolean updateFile(String id, File file) {
        try {

            if (Objects.isNull(file)) {
                return false;
            }

            PreparedStatement pst = Shell.getConnection().prepareStatement("UPDATE Files SET name=?,size=?,content=? WHERE id=?");
            pst.setString(1, file.getName());
            pst.setLong(2, file.getSize());
            pst.setBytes(3, file.getContent());
            pst.setString(4, id);

            pst.execute();
            return true;
        } catch (Exception e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return false;
        }
    }

    public List<Object[]> getFiles(int foreignId) {
        List<Object[]> content = new ArrayList<>();
        String query = db.getSelectSQL("Files", new String[]{"id", "name", "size", "uploadDate"},
                "fid = ?", "");

        try {
            PreparedStatement pst = Shell.getConnection().prepareStatement(query);
            pst.setInt(1, foreignId);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                content.add(new Object[]{rs.getInt(1), rs.getString(2),
                        rs.getLong(3), rs.getString(4)});
            }

            pst.close();
            return content;
        } catch (SQLException e) {
            Shell.printException(e.getMessage() + "\n" + ExternalTools.getPrintStackTrace(e), true);
            return null;
        }
    }
}
