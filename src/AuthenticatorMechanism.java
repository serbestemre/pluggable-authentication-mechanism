// This is the Template Pattern we used in the project.
// Abstract class lets us to perform different methods in an order.
// Non-changed methods implemented as final but if the method is changing
// according to the inherited class so, we override it in the sub class and give the appropriate solution.
// In this case, as the user authenticate with different types (Local, LDAP and Kerberos)
// we only printed what type the user connected to the Operating System

abstract class AuthenticatorMechanism {
    UserDatabase dbInstance = new UserDatabase();

    public final User findUser(String userName) {
        try {
            System.out.println("Looking for user information...");
            return dbInstance.findUser(userName);
        } catch (Exception e) {
            System.out.println("User could not found!");
            return null;
        }

    }

    public final boolean verifyPassword(String userPass, String enteredPass) {
        System.out.println("Verifying user information....");
        return enteredPass.equals(userPass);
    }

    abstract int isLoggedIn();

    public final int authenticateByGivenAccess(String name, String password) {
        int rc = 1; //login failed
        try {
            User registeredUser = findUser(name);
            boolean isPasswordMatch = verifyPassword(registeredUser.getPassword(), password);
            try {
                if (!isPasswordMatch) {
                    throw new Exception("Username or password is not correct!");
                } else {
                    rc = isLoggedIn();
                }
                return rc;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return rc;
            }

        } catch (Exception e) {
            System.out.println("Something went wrong during login process");
            return rc;
        }
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
