/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainPackage;

import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author danil
 */
public class Modules {
    
    private static final String DB_URL = "jdbc:sqlite:teacher.db";
    List<Integer> themeID = new ArrayList<>();
    List<String> themeName = new ArrayList<>();
    
    public void AddNewTheme (String themeName, String themeOwner, int teacherID)
    {
        String newThemeQuery = "INSERT INTO Themes (theme_name, theme_owner, teacher_id) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement stmt = conn.prepareStatement(newThemeQuery))
        {
            stmt.setString(1, themeName);
            stmt.setString(2, themeOwner);
            stmt.setInt(3, teacherID);
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void AddNewModule(int themeID, String contentText, int teacherID)
    {
        String newModuleQuery = "INSERT INTO Content (content_text, content_image, theme_id, teacher_id) VALUES (?, NULL, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement stmt = conn.prepareStatement(newModuleQuery))
        {
            stmt.setString(1, contentText);
            stmt.setInt(2, themeID);
            stmt.setInt(3, teacherID);
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void AddNewModule(int themeID, int teacherID, Blob contentImage)
    {
        String newModuleQuery = "INSERT INTO Content (content_text, content_image, theme_id, teacher_id) VALUES (NULL, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement stmt = conn.prepareStatement(newModuleQuery))
        {
            stmt.setBlob(1, contentImage);
            stmt.setInt(2, themeID);
            stmt.setInt(3, teacherID);
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void loadModules(int teacherID, JPanel mainPanel, JButton backsButton, JFrame teacherFrame)
    {
        Component[] components = mainPanel.getComponents();
        for (Component component : components) {
            if (component != backsButton) {
                mainPanel.remove(component);
            }
        }
        String getThemes = "SELECT theme_name FROM Themes WHERE teacher_id = " + teacherID + ";";
        try (Connection newConn = DriverManager.getConnection(DB_URL);
                            Statement newStmt = newConn.createStatement();
                            ResultSet newRs = newStmt.executeQuery(getThemes))
                        {
                            List <Themes> themes = new ArrayList<>();
                            while(newRs.next())
                            {
                                String theme_name = newRs.getString("theme_name");
                                themes.add(new Themes(theme_name));
                            }
                            for (Themes theme : themes)
                            {
                                JButton module = new JButton(theme.theme_name);
                                int mStartY = backsButton.getY();
                                int mBtnHeight = 40;
                                int mHorizontalGap = 10;
                                int mVerticalGap = 10;
                                int mBtnWidth = mainPanel.getWidth() - 10;
                                int mFormH = teacherFrame.getHeight();

                                int mCurY = mStartY + 50;

                                module.setBounds(10, mCurY, mBtnWidth, mBtnHeight);
                                if (mCurY + mBtnHeight > mFormH - 50)
                                {
                                   mCurY = mStartY;
                                   break;
                                }
                                module.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                                mainPanel.add(module);
                            }
                            mainPanel.revalidate();
                        }
                        catch(SQLException exc)
                        {
                            exc.printStackTrace();
                        }
    }
}
