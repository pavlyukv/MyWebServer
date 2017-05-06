package ua.lviv.pancha.accounts;

import ua.lviv.pancha.DAO.UserDAO;
import ua.lviv.pancha.services.DBService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vasyl on 18.04.2017.
 */
public class AccountService implements AutoCloseable {
    private final DBService dbService;
    private final UserDAO userDAO;
    private Map<String, UserProfile> sessionIdToProfile;

    public AccountService() {
        dbService = new DBService();
        userDAO = new UserDAO(dbService.getConnection());
        sessionIdToProfile = new HashMap<>();
    }

    public int addNewUser(String login, String password) {
        return addNewUser(new UserProfile(login, password));
    }

    public int addNewUser(UserProfile userProfile) {
        return userDAO.addNewUser(userProfile);
    }

    public UserProfile getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }

    @Override
    public void close() {
        dbService.close();
    }
}
