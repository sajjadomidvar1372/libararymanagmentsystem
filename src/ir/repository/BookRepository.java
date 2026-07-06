package ir.repository;

import ir.util.DatabaseConfig;
import ir.model.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class BookRepository {
    private DatabaseConfig databaseConfig = new DatabaseConfig();

    public int create(Book book) {
        String insertQuery =
                "INSERT INTO books(title, author, price, stock) VALUES (?, ?, ?, ?)";
        try (Connection connection = this.databaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(insertQuery)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getStock());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("INSERT FALIED");
        }



    }

}
