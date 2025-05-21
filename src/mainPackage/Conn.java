/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainPackage;

import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JLabel;

/**
 *
 * @author danil
 */
public class Conn {
    public static void Connect(JLabel outputLabel)
    {
        java.sql.Connection conn = null;
        Statement stmt = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:dbmsteach.db");
            outputLabel.setText("Создание базы данных...");
            stmt = conn.createStatement();
            String createAuthorTable = "CREATE TABLE IF NOT EXISTS Authors " + 
                    "(AuthorID INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                    "FirstName TEXT NOT NULL, " + 
                    "LastName TEXT NOT NULL, " + 
                    "BirthDate TEXT, " + 
                    "Country TEXT NOT NULL)";
            stmt.executeUpdate(createAuthorTable);
            String createBookTable = "CREATE TABLE IF NOT EXISTS Books " + 
                    "(BookID INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                    "Title TEXT NOT NULL, " + 
                    "AuthorID INTEGER, " +
                    "GenreID INTEGER, " + 
                    "PublisherID INTEGER, " + 
                    "YearPublished TEXT NOT NULL, " + 
                    "ISBN INTEGER NOT NULL, "
                    + "Quantity INTEGER, "
                    + "FOREIGN KEY (AuthorID) REFERENCES Authors(AuthorID), "
                    + "FOREIGN KEY (GenreID) REFERENCES Genres(GenreID), "
                    + "FOREIGN KEY (PublisherID) REFERENCES Publisher(PublisherID))";
            stmt.executeUpdate(createBookTable);
            String createGenreTable = "CREATE TABLE IF NOT EXISTS Genres "
                    + "(GenreID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Name TEXT NOT NULL)";
            stmt.executeUpdate(createGenreTable);
            String createReaderTable = "CREATE TABLE IF NOT EXISTS Reader"
                    + "(ReaderID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "FirstName TEXT NOT NULL, "
                    + "LastName TEXT NOT NULL, "
                    + "Adress TEXT NOT NULL, "
                    + "PhoneNumber TEXT NOT NULL, "
                    + "Email TEXT NOT NULL)";
            stmt.executeUpdate(createReaderTable);
            String bookLoanTable = "CREATE TABLE IF NOT EXISTS BookLoan "
                    + "(LoanID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "BookID INTEGER, "
                    + "ReaderID INTEGER, "
                    + "LoanDate TEXT NOT NULL, "
                    + "ReturnDate TEXT, "
                    + "Status TEXT NOT NULL, "
                    + "FOREIGN KEY (BookID) REFERENCES Books(BookID),"
                    + "FOREIGN KEY (ReaderID) REFERENCES Readers(ReaderID) )";
            stmt.executeUpdate(bookLoanTable);
            String createPublisherTable = "CREATE TABLE IF NOT EXISTS Publisher"
                    + "(PublisherID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Name TEXT NOT NULL, "
                    + "Adress TEXT NOT NULL, "
                    + "PhoneNumber TEXT NOT NULL)";
            stmt.executeUpdate(createPublisherTable);
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            outputLabel.setText("Ошибка, " + e.getClass().getName() + ": " + e.getMessage());
            return;
        }
        outputLabel.setText("База данных создана");
    }
}
