package ua.lviv.pancha.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Vasyl on 06.05.2017.
 */
public class DBService implements AutoCloseable {
    // TODO - properties
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private final Connection connection;

    public DBService() {
        connection = getSqlConnection();
    }

    // TODO - add H2 db possibilities
    private static Connection getSqlConnection() {
        try {
            Class.forName(DRIVER).newInstance();
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception empty) {
        }
        return null;
    }

    public void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException empty) {
        }
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
        } catch (SQLException empty) {
        }
    }
}
