import java.util.ArrayList;
import java.util.Iterator;

public class UserDatabase {
    static ArrayList<User> users = new ArrayList<>();

    public void addUserToDatabase(User newUser) {
        // TODO username should be unique and a user can only be defined with a single auth access permission
        users.add(newUser);
    }

    public void printDb() {
        System.out.println("Registered user list is printing...");
        Iterator iterator = users.iterator();
        while (iterator.hasNext()) {
            User currentUser = (User) iterator.next();
            System.out.println(currentUser.getUserName());
        }
    }

    public int getUid(String username) {
        Iterator iterator = users.iterator();
        int uid = -1;
        while (iterator.hasNext()) {
            User currentUser = (User) iterator.next();
            if (currentUser.getUserName().toLowerCase().equals(username.toLowerCase())) {
                uid = currentUser.getUid();
            }
        }
        return uid;
    }


    public User findUser(String username) {
        Iterator iterator = users.iterator();
        User foundUser = null;
        while (iterator.hasNext()) {
            User currentUser = (User) iterator.next();
            if (currentUser.getUserName().toLowerCase().equals(username.toLowerCase())) {
                foundUser = currentUser;
            }
        }
        return foundUser;
    }


}
