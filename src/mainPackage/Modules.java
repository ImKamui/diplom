///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package mainPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Modules {
    
    private static final String DB_URL = "jdbc:sqlite:teacher.db?busy_timeout=5000";
    
    public int AddNewTheme(Connection conn, String themeName, String themeOwner, 
                         int teacherID, String teacherLogin) throws SQLException {
        String sql = "INSERT INTO Themes (theme_name, theme_owner, teacher_id, teacher_login) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, themeName);
            stmt.setString(2, themeOwner);
            stmt.setInt(3, teacherID);
            stmt.setString(4, teacherLogin);
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }
    
    public void AddNewModule(Connection conn, int themeID, String contentText, 
                           int teacherID, int isTitle) throws SQLException {
        String sql = "INSERT INTO Content (content_text, theme_id, teacher_id, is_title) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contentText);
            stmt.setInt(2, themeID);
            stmt.setInt(3, teacherID);
            stmt.setInt(4, isTitle);
            stmt.executeUpdate();
        }
    }
    
    public void AddNewModule(Connection conn, int themeID, File imageFile, int teacherID, int isTitle) throws SQLException, IOException {
        String sql = "INSERT INTO Content (content_image, theme_id, teacher_id, is_title) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (FileInputStream fis = new FileInputStream(imageFile))
            {
                byte[] imageBytes = new byte[(int)imageFile.length()];
                fis.read(imageBytes);
                stmt.setBytes(1, imageBytes);
                stmt.setInt(2, themeID);
                stmt.setInt(3, teacherID);
                stmt.setInt(4, isTitle);
                stmt.executeUpdate();
            }
        }
    }
    
    public List<ModuleContent> getModuleContent(String themeName, String teacherLogin) throws SQLException {
        List<ModuleContent> contents = new ArrayList<>();
        String sql = "SELECT content_text, content_image, is_title FROM Content " +
                     "WHERE theme_id = (SELECT theme_id FROM Themes WHERE theme_name = ? AND teacher_login = ?) " +
                     "ORDER BY content_id";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, themeName);
            stmt.setString(2, teacherLogin);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String text = rs.getString("content_text");
                    byte[] imageBytes = rs.getBytes("content_image");
                    boolean isTitle = rs.getBoolean("is_title");
                    contents.add(new ModuleContent(text, imageBytes, isTitle));
                }
            }
        }
        return contents;
    }
}