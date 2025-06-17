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
            stmt.execute("PRAGMA foreign_keys = ON");
            String createAuthorTable = "CREATE TABLE IF NOT EXISTS Authors " + 
                    "(AuthorID INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                    "FirstName TEXT NOT NULL, " + 
                    "LastName TEXT NOT NULL, " + 
                    "BirthDate TEXT, " + 
                    "Country TEXT NOT NULL)";
            String createBookTable = "CREATE TABLE IF NOT EXISTS Books " + 
                    "(BookID INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                    "Title TEXT NOT NULL, " + 
                    "AuthorID INTEGER, " +
                    "GenreID INTEGER, " + 
                    "PublisherID INTEGER, " + 
                    "YearPublished TEXT NOT NULL, " + 
                    "ISBN INTEGER NOT NULL, "
                    + "Quantity INTEGER, "
                    + "FOREIGN KEY (AuthorID) REFERENCES Authors(AuthorID) ON DELETE CASCADE, "
                    + "FOREIGN KEY (GenreID) REFERENCES Genres(GenreID) ON DELETE SET NULL, "
                    + "FOREIGN KEY (PublisherID) REFERENCES Publisher(PublisherID))";
            String createGenreTable = "CREATE TABLE IF NOT EXISTS Genres "
                    + "(GenreID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Name TEXT NOT NULL)";
            String createReaderTable = "CREATE TABLE IF NOT EXISTS Reader"
                    + "(ReaderID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "FirstName TEXT NOT NULL, "
                    + "LastName TEXT NOT NULL, "
                    + "Adress TEXT NOT NULL, "
                    + "PhoneNumber TEXT NOT NULL, "
                    + "Email TEXT NOT NULL)";
            String bookLoanTable = "CREATE TABLE IF NOT EXISTS BookLoan "
                    + "(LoanID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "BookID INTEGER, "
                    + "ReaderID INTEGER, "
                    + "LoanDate TEXT NOT NULL, "
                    + "ReturnDate TEXT, "
                    + "Status TEXT NOT NULL, "
                    + "FOREIGN KEY (BookID) REFERENCES Books(BookID)ON DELETE CASCADE,"
                    + "FOREIGN KEY (ReaderID) REFERENCES Reader(ReaderID) )";
            String createPublisherTable = "CREATE TABLE IF NOT EXISTS Publisher"
                    + "(PublisherID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Name TEXT NOT NULL, "
                    + "Adress TEXT NOT NULL, "
                    + "PhoneNumber TEXT NOT NULL)";
            stmt.executeUpdate(createAuthorTable);    // Независимая
            stmt.executeUpdate(createGenreTable);     // Независимая
            stmt.executeUpdate(createPublisherTable); // Независимая
            stmt.executeUpdate(createReaderTable);    // Независимая (Reader)
            stmt.executeUpdate(createBookTable);      // Зависит от Authors, Genres, Publisher
            stmt.executeUpdate(bookLoanTable);        // Зависит от Books, Reader
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            String error = e.getMessage();
            error = error.replace("SQLITE", "PG");
            outputLabel.setText("Ошибка, " + e.getClass().getName() + ": " + error);
            return;
        }
        outputLabel.setText("База данных создана");
    }
}
