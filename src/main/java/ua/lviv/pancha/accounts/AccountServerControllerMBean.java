package ua.lviv.pancha.accounts;

/**
 * Created by Vasyl on 22.05.2017.
 */
public interface AccountServerControllerMBean {
    public int getUsers();

    public int getUsersLimit();

    public void setUsersLimit(int usersLimit);
}
