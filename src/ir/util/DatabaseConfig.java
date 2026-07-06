package ir.util;

import ir.exception.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private String URL = "jdbc:postgresql://localhost:5432/library_management_system";
    private String USERNAME = "postgres";
    private String PASSWORD = "1qaz!QAZ";

    public Connection getConnection() {
        try{
            return DriverManager.getConnection(URL, USERNAME , PASSWORD);
        }catch (SQLException e){
            throw new DatabaseConnectionException(" Connection Failed " .concat(e.getMessage()));
        }
    }
}

