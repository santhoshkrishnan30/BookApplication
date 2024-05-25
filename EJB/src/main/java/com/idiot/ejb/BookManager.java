package com.idiot.ejb;

import com.idiot.model.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class BookManager {

    private static final String URL = "jdbc:mysql:///book";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "8883";

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT ID, BOOKNAME, BOOKEDITION, BOOKPRICE FROM BOOKDATA";

        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("ID"));
                book.setBookName(rs.getString("BOOKNAME"));
                book.setBookEdition(rs.getString("BOOKEDITION"));
                book.setBookPrice(rs.getFloat("BOOKPRICE"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
}