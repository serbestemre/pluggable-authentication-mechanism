// Template

abstract class Authenticate {
    UserDatabase dbInstance = new UserDatabase();
    public final User findUser(String userName) {
        System.out.println("Looking for user information...");
        return dbInstance.findUser(userName);
    }

    public final boolean verifyPassword(String userPass, String enteredPass) {
        System.out.println("Verifying user information....");
        if(enteredPass == userPass){
            return true;
        }else{
            return false;
        }
    }

    abstract int isLoggedIn();

    public final int authenticateByGivenAccess(String name, String password){
        int rc = 1; //login failed
        User registeredUser =  findUser(name);
        boolean isPasswordMatch = verifyPassword(registeredUser.getPassword(), password);
        if(!isPasswordMatch){
            System.out.println("Passwords does not match");

        }else{
            rc = isLoggedIn();
        }

        return rc;
    };
}


class Local extends Authenticate {
    private static Local localAuthInstance = null;

    private Local () {
    }

    public static Local getLocalAuthInstance () {
        if(localAuthInstance == null){
            return new Local();
        }else{
            return localAuthInstance;
        }
    }

    @Override
    int isLoggedIn() {
        System.out.println("User authenticated on local");
        return 0;
    }
}

class Ldap extends Authenticate{
    private static Ldap ldapAuthInstance = null;

    private Ldap(){
    }

    public static Ldap getLdapAuthInstance (){
        if(ldapAuthInstance == null){
            return new Ldap();
        }else{
            return ldapAuthInstance;
        }
    }

    @Override
    int isLoggedIn() {
        System.out.println("User authenticated on Ldap");
        // TODO Call getUid(name);?
        return 0;
    }
}

class Kerberos extends Authenticate{
    private static Kerberos kerberosAuthInstance = null;

    private Kerberos () {
    }

    public static Kerberos getKerberosAuthInstance(){
        if(kerberosAuthInstance == null){
            return new Kerberos();
        }else{
            return kerberosAuthInstance;
        }
    }

    @Override
    int isLoggedIn() {
        System.out.println("User authenticated on Kerberos");
        return 0;
    }
}
