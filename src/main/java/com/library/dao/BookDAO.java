package com.library.dao;

import com.library.model.Book;
import com.library.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public List<Book> searchBooks(String query) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE (LOWER(title) LIKE LOWER(?) OR LOWER(author) LIKE LOWER(?)) AND available = 1";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setIsbn(rs.getString("isbn"));
                book.setAvailable(rs.getInt("available") == 1);
                books.add(book);
            }
        }
        return books;
    }

    public List<Book> getOverdueBooks() throws SQLException {
        List<Book> overdueBooks = new ArrayList<>();
        String sql = "SELECT b.* FROM books b " +
                    "JOIN transactions t ON b.id = t.book_id " +
                    "WHERE t.due_date < SYSDATE AND t.return_date IS NULL";
                    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setIsbn(rs.getString("isbn"));
                book.setAvailable(rs.getInt("available") == 1);
                overdueBooks.add(book);
            }
        }
        return overdueBooks;
    }
}
