public class Main {

    public static void main(String[] args) {
        User user1 = new User("Emre", "123");
        user1.setUid(100100);
        User user2 = new User("Sercan", "123");
        user2.setUid(200100);
        User user3 = new User("Hatice", "abc");
        user3.setUid(300100);
        User user4 = new User("İrem", "123abc");
        user4.setUid(300100);

        UserDatabase db = new UserDatabase();
        db.addUserToDatabase(user1);
        db.addUserToDatabase(user2);
        db.addUserToDatabase(user3);
        db.addUserToDatabase(user4);

        db.printDb();

        System.out.println(" ");
        System.out.println("---------------");
        System.out.println(" ");

        OperatingSystem windowsOS = new OperatingSystem();

        // TODO get inputs from scanner
        windowsOS.authenticate("Emre","123");
        System.out.println(" ");
        windowsOS.authenticate("Sercan","1234");
        System.out.println(" ");
        windowsOS.authenticate("Hatice","abc");
        System.out.println(" ");
        windowsOS.authenticate("İrem","123abc");



    }
}
