package ua.lviv.pancha.DAO;

import ua.lviv.pancha.accounts.UserProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Vasyl on 06.05.2017.
 */
public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void addNewUser(UserProfile userProfile) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS (LOGIN, PASSWORD) VALUES (?, ?)");
            statement.setString(1, userProfile.getLogin());
            statement.setString(2, userProfile.getPassword());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Can't add new user to DB!");
        }
    }

    public UserProfile getUserByLogin(String login) {
        UserProfile userProfile = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE LOGIN LIKE ?");
            statement.setString(1, login);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                userProfile = new UserProfile(set.getString("LOGIN"), set.getString("PASSWORD"));
                userProfile.setId(set.getInt("ID"));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Can't find user in DB!");
        }
        return userProfile;
    }
}
