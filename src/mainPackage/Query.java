/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author danil
 */
public class Query {
    public static void UseQuery(JTextArea resultQueryTextArea, JTextArea QueryTextArea, JLabel outputLabel)
    {
        String query = QueryTextArea.getText();
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:dbmsteach.db");
             Statement stmt = connection.createStatement()) 
        {
            boolean hasResult = stmt.execute(query);
            StringBuilder output = new StringBuilder();
            String toLabel;
            
            if (hasResult)
            {
                try (ResultSet resultSet = stmt.getResultSet())
                {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    
                    for (int i = 1; i <= columnCount; i++)
                    {
                        output.append(metaData.getColumnName(i)).append("\t");
                    }
                    output.append("\n");
                    
                    while (resultSet.next())
                    {
                        for (int i = 1; i <= columnCount; i++)
                        {
                            output.append(resultSet.getString(i)).append("\t");
                        }
                        output.append("\n");
                    }
                }
            }
            else
            {
                int updateCount = stmt.getUpdateCount();
                toLabel = "Строк добавлено/удалено: " + updateCount;
                outputLabel.setText(toLabel);
            }
            
            resultQueryTextArea.setText(output.toString());
        }
        catch (SQLException e)
        {
            resultQueryTextArea.setText("Ошибка: " + e.getMessage());
        }
        
    }
    public static void UseQuery(JTextArea QueryTextArea, JLabel outputLabel)
    {
        String query = QueryTextArea.getText();
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:dbmsteach.db");
             Statement stmt = connection.createStatement()) 
        {
            boolean hasResult = stmt.execute(query);
            StringBuilder output = new StringBuilder();
            String toLabel;
            
            if (hasResult)
            {
                try (ResultSet resultSet = stmt.getResultSet())
                {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    
                    for (int i = 1; i <= columnCount; i++)
                    {
                        output.append(metaData.getColumnName(i)).append("\t");
                    }
                    output.append("\n");
                    
                    while (resultSet.next())
                    {
                        for (int i = 1; i <= columnCount; i++)
                        {
                            output.append(resultSet.getString(i)).append("\t");
                        }
                        output.append("\n");
                    }
                }
            }
            else
            {
                int updateCount = stmt.getUpdateCount();
                toLabel = "Таблица создана. Код ошибки: " + updateCount;
                outputLabel.setText(toLabel);
            }
        }
        catch (SQLException e)
        {
            outputLabel.setText("Ошибка: " + e.getMessage());
        }
        
    }
}
