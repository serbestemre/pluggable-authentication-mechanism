//Facade
class OperatingSystem {
    UserDatabase db = new UserDatabase();

    public void authenticate(String username, String pwd) {
        PluggableAuthentication pluggableAuthentication;

        int uid = db.getUid(username);
        String uidString = String.valueOf(uid);

        String accessRoleCode = "undefined";
        // local users' id starts with 100
        // ldap users' id starts with 200
        // kerberos users' id start with 300
        // other ids do not have any access permission

        if (uidString.startsWith("100")) {
            accessRoleCode = "local";
        } else if (uidString.startsWith("200")) {
            accessRoleCode = "ldap";
        } else if (uidString.startsWith("300")) {
            accessRoleCode = "kerberos";
        } else {
            accessRoleCode = "undefined";
        }

        int rc;

        switch (accessRoleCode) {
            case "local":
                pluggableAuthentication = LocalAPIPluggableAuthentication.getLocalAdapterInstance();
                rc = pluggableAuthentication.authenticate(username, pwd);
                pluggableAuthentication.getUid(username);
                break;
            case "ldap":
                pluggableAuthentication = LdapAPIPluggableAuthentication.getLdapAdapterInstance();
                rc = pluggableAuthentication.authenticate(username, pwd);
                pluggableAuthentication.getUid(username);
                break;
            case "kerberos":
                pluggableAuthentication = KerberosAPIPluggableAuthentication.getKerberosAdapterInstance();
                rc = pluggableAuthentication.authenticate(username, pwd);
                pluggableAuthentication.getUid(username);
                break;
            default:
                rc = 1;
                break;
        }

        if (rc == 0) {
            System.out.println("User logged in successfully!");
        } else {
            System.out.println("Authentication is failed!");
        }

    }

}

public class Main {

    public static void main(String[] args) {
        User user1 = new User("Emre", "123");
        user1.setUid(100100);
        User user2 = new User("Sercan", "123");
        user2.setUid(200100);
        User user3 = new User("Hatice", "abc");
        user3.setUid(300100);
        User user4 = new User("İrem", "123abc");
        user4.setUid(300101);

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
        windowsOS.authenticate("Emre", "123");
        System.out.println(" ");
        windowsOS.authenticate("Sercan", "123");
        System.out.println(" ");
        windowsOS.authenticate("Hatice", "abc");
        System.out.println(" ");
        windowsOS.authenticate("İrem", "123abc");


    }
}



