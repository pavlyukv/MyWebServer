package ua.lviv.pancha.accounts;

import ua.lviv.pancha.DAO.UserDAO;
import ua.lviv.pancha.services.DBService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vasyl on 18.04.2017.
 */
public class AccountService {
    private Map<String, UserProfile> sessionIdToProfile;
    private final DBService dbService;

    public AccountService(DBService dbService) {
        this.dbService = dbService;
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile) {
        UserDAO userDAO = new UserDAO(dbService.getConnection());
        userDAO.addNewUser(userProfile);
    }

    public UserProfile getUserByLogin(String login) {
        UserDAO userDAO = new UserDAO(dbService.getConnection());
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
}
