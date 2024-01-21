package db;

import db.exception.DBException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection connection = null;

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(fs);
            return properties;
        }catch (IOException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static Connection startConnection() {
        if (connection == null) {
            try {
            Properties properties = loadProperties();
            String url = properties.getProperty("dburl");
            connection = DriverManager.getConnection(url, properties);
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }
}
