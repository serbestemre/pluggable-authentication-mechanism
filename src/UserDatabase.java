public class UserDatabase {
    static AbstractAggregate users = new Collection();

    public void addUserToDatabase(User newUser) {
        // TODO username should be unique and a user can only be defined with a single auth access permission
        users.add(newUser);
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
            User currentUser = (User) iterator.CurrentUser();
            if (currentUser.getUserName().toLowerCase().equals(username.toLowerCase())) {
                foundUser = currentUser;
            }
            iterator.Next();
        }
        return foundUser;
    }


}
