// Template

abstract class AuthenticatorMechanism {
    UserDatabase dbInstance = new UserDatabase();

    public final User findUser(String userName) {
        System.out.println("Looking for user information...");
        return dbInstance.findUser(userName);
    }

    public final boolean verifyPassword(String userPass, String enteredPass) {
        System.out.println("Verifying user information....");
        return enteredPass.equals(userPass);
    }

    abstract int isLoggedIn();

    public final int authenticateByGivenAccess(String name, String password) {
        int rc = 1; //login failed
        User registeredUser = findUser(name);
        boolean isPasswordMatch = verifyPassword(registeredUser.getPassword(), password);
        if (!isPasswordMatch) {
            System.out.println("Passwords does not match");

        } else {
            rc = isLoggedIn();
        }

        return rc;
    }

}


class LocalAuthenticator extends AuthenticatorMechanism {
    @Override
    int isLoggedIn() {
        System.out.println("User authenticated on local");
        return 0;
    }
}

class LdapAuthenticator extends AuthenticatorMechanism {
    @Override
    int isLoggedIn() {
        System.out.println("User authenticated on Ldap");
        // TODO Call getUid(name);?
        return 0;
    }
}

class KerberosAuthenticator extends AuthenticatorMechanism {
    @Override
    int isLoggedIn() {
        System.out.println("User authenticated on Kerberos");
        return 0;
    }
}
