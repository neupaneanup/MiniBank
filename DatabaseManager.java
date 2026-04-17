package MiniBank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/minibank_db";
    private static final String USER = "anup"; 
    private static final String PASSWORD = ""; // Postgres.app defaults to no password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
