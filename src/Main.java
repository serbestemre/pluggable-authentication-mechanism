import java.util.Scanner;

// EMRE SERBEST
// SERCAN KAVDIR
// İREM ULUSOY
// HATİCE NUR RUŞEN
// A Pluggable Authentication Mechanism

/*
* OperatingSystem class is used to implement Facade Design pattern
* User(Client) will be able to authenticate only calling authenticate() method from this class
* All complexity of the authentication flow is hided by facade design pattern
*
Facade:
    -OperatingSystem
Subsystem Classes:
    -LocalAPIPluggableAuthentication
    -LdapAPIPluggableAuthentication
    -KerberosAPIPluggableAuthentication
* */
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

        try {
            if (rc == 0) {
                System.out.println("User logged in successfully!");
            } else {
                throw new Exception("Authentication is failed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}


/* USAGE
* User have to be created by hard coding before run the application (examples are below)
*
* UID must start with API identifier codes
* LocalAPI Identifier = 100
* LdapAPI Identifier = 200
* KerberosAPı Identifier = 300
*
* Created user has to be added to the operating system database by calling addUserToDatabase();
*
* After the program run username and password will be asked respectively
*
* authentication flow will be proceed by the operating system sequentially and the result will be printed
*
* */

public class Main {

    public static void main(String[] args) {
        // For login in local, the id of the user must start with 100
        User user1 = new User("Emre", "123");
        user1.setUid(100100);

        // For login in ldap, the id of the user must start with 200
        User user2 = new User("Sercan", "123");
        user2.setUid(200100);

        // For login in kerberos, the id of the user must start with 300
        User user3 = new User("Hatice", "abc");
        user3.setUid(300100);

        User user4 = new User("İrem", "123abc");
        user4.setUid(300101);

        UserDatabase db = new UserDatabase();

        // Added created users to the database
        db.addUserToDatabase(user1);
        db.addUserToDatabase(user2);
        db.addUserToDatabase(user3);
        db.addUserToDatabase(user4);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Provide username and password to login to the Operating System");
        System.out.println("--------------------");

        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        System.out.println("--------------------");

        OperatingSystem windowsOS = new OperatingSystem();

        windowsOS.authenticate(username, password);
        System.out.println(" ");

    }
}



