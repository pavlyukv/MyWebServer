package ua.lviv.pancha.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Vasyl on 06.05.2017.
 */
public class DBService implements AutoCloseable {
    // TODO - Use properties!!!
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private final Connection connection;

    public DBService() {
        connection = getSqlConnection();
    }

    private static Connection getSqlConnection() {
        try {
            Class.forName(DRIVER).newInstance();
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println("No connection to DB!");
        }
        return null;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {}
    }
}
