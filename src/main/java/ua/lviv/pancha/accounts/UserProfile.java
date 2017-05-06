package ua.lviv.pancha.accounts;

/**
 * Created by Vasyl on 18.04.2017.
 */
public class UserProfile {
    private int id;
    private String login;
    private String password;

    public UserProfile(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserProfile(String login) {
        this.login = login;
        this.password = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
