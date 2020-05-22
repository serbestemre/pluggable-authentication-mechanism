import java.util.Objects;

public class UserDatabase {
    static AbstractAggregate users = new Collection();

    public void addUserToDatabase(User newUser) {
        if (!users.contains(newUser)) {
            users.add(newUser);
        }else{
            System.out.println("USER COULDNT ADD");
            // TODO Throw error User already defined with that id or username
        }
    }

    public void printDb() {
        System.out.println("Registered user list is printing...");
        AbstractIterator iterator = users.CreateIterator();
        while (!iterator.IsDone()) {
            User currentUser = iterator.CurrentUser();
            System.out.println(currentUser.getUserName());
            iterator.Next();
        }
    }

    public int getUid(String username) {
        AbstractIterator iterator = users.CreateIterator();
        int uid = -1;
        while (!iterator.IsDone()) {
            User currentUser = iterator.CurrentUser();
            if (currentUser.getUserName().toLowerCase().equals(username.toLowerCase())) {
                uid = currentUser.getUid();
            }
            iterator.Next();
        }
        return uid;
    }


    public User findUser(String username) {
        AbstractIterator iterator = users.CreateIterator();
        User foundUser = null;
        while (!iterator.IsDone()) {
            User currentUser = iterator.CurrentUser();
            if (currentUser.getUserName().toLowerCase().equals(username.toLowerCase())) {
                foundUser = currentUser;
            }
            iterator.Next();
        }
        return foundUser;
    }


}


class User {
    private String userName;
    private int uid;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUid() == user.getUid() ||
                getUserName().toLowerCase().equals(user.getUserName().toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getUid());
    }
}

