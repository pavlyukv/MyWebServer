package ua.lviv.pancha.DAO;

import ua.lviv.pancha.accounts.UserProfile;

import java.sql.*;

/**
 * Created by Vasyl on 06.05.2017.
 */
public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
        createTable();
    }

    public int addNewUser(UserProfile userProfile) {
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS (LOGIN, PASSWORD) VALUES (?, ?)")) {
            statement.setString(1, userProfile.getLogin());
            statement.setString(2, userProfile.getPassword());
            result = statement.executeUpdate();
        } catch (SQLException empty) {
        }
        return result;
    }

    public UserProfile getUserByLogin(String login) {
        UserProfile userProfile = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE LOGIN LIKE ?")) {
            statement.setString(1, login);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                userProfile = new UserProfile(set.getString("LOGIN"), set.getString("PASSWORD"));
                userProfile.setId(set.getInt("ID"));
            }
            set.close();
        } catch (SQLException empty) {
        }
        return userProfile;
    }

    private void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS USERS " +
                    "(ID INT(11) NOT NULL AUTO_INCREMENT, " +
                    "LOGIN VARCHAR(100) NOT NULL, " +
                    "PASSWORD VARCHAR(100) NOT NULL, " +
                    "PRIMARY KEY (ID), " +
                    "UNIQUE KEY ID_UNIQUE (ID), " +
                    "UNIQUE KEY LOGIN_UNIQUE (LOGIN)) " +
                    "ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8");
        } catch (SQLException empty) {
        }
    }
}
