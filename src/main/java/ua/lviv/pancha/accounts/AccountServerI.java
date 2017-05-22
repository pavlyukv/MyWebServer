package ua.lviv.pancha.accounts;

/**
 * Created by Vasyl on 22.05.2017.
 */
public interface AccountServerI {
    void addNewUser();

    void removeUser();

    int getUsersLimit();

    void setUsersLimit(int usersLimit);

    int getUsersCount();
}
