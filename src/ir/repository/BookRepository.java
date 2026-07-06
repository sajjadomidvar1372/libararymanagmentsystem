package ir.repository;

import ir.exception.BookNotFoundException;
import ir.util.DatabaseConfig;
import ir.model.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class BookRepository {
    private DatabaseConfig databaseConfig = new DatabaseConfig();

    public int createBook(Book book) {
        String insertQuery = "INSERT INTO books(title, author, price, stock) VALUES (?, ?, ?, ?)";
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

    public Book findById(int id) {
        String findQuery = "SELECT * FROM books WHERE id = ?";
        try (Connection connection = this.databaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(findQuery)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getInt(1),
                        rs.getString("title"),
                        rs.getString(3),
                        rs.getDouble("price"),
                        rs.getInt(5)
                );
            }
            throw new BookNotFoundException(" THIS ID IS NOT VALID AND BOOK NOT FOUND");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updatePrice(int id, double price) {
        String updateQuery = "UPDATE books SET price = ? WHERE id = ?";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(updateQuery)) {
            ps.setDouble(1, price);
            ps.setInt(2, id);
            int record = ps.executeUpdate();
            if (record == 0) {
                throw new BookNotFoundException("THE BOOK NOT FOUND FOR UPDATE");
            }
            return record;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
