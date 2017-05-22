package ua.lviv.pancha.accounts;

/**
 * Created by Vasyl on 22.05.2017.
 */
public class AccountServer implements AccountServerI {
    private int usersCount;
    private int usersLimit;

    public AccountServer(int usersLimit) {
        usersCount = 0;
        this.usersLimit = usersLimit;
    }

    @Override
    public void addNewUser() {
        usersCount += 1;
    }

    @Override
    public void removeUser() {
        usersCount -= 1;
    }

    @Override
    public int getUsersLimit() {
        return usersLimit;
    }

    @Override
    public void setUsersLimit(int usersLimit) {
        this.usersLimit = usersLimit;
    }

    @Override
    public int getUsersCount() {
        return usersCount;
    }
}
