interface PluggableAuthentication {
    int authenticate(String name, String password);

    void getUid(String userName);
}

class LocalAPI {
    int local_authenticate(String userName, String password) {
        System.out.println("Connecting to OS via LOCAL adaptee....");
        AuthenticatorMechanism localAuth = new LocalAuthenticator();
        return localAuth.authenticateByGivenAccess(userName, password);
    }

    void local_getUid(String userName) {
        UserDatabase db = new UserDatabase();
        int uid = db.getUid(userName);
        System.out.println("Local User ID => " + uid);
    }
}

class LdapAPI {
    int ldap_authenticate(String userName, String password) {
        System.out.println("Connecting to OS via LDAP adaptee...");
        AuthenticatorMechanism ldapAuth = new LdapAuthenticator();
        return ldapAuth.authenticateByGivenAccess(userName, password);
    }

    void ldap_getUid(String userName) {
        UserDatabase db = new UserDatabase();
        int uid = db.getUid(userName);
        System.out.println("LDAP User ID => " + uid);
    }
}

class KerberosAPI {
    int kerberos_authenticate(String userName, String password) {
        System.out.println("Connecting to OS via KERBEROS adaptee");
        AuthenticatorMechanism kerberosAut = new KerberosAuthenticator();
        return kerberosAut.authenticateByGivenAccess(userName, password);
    }

    void kerberos_getUid(String userName) {
        UserDatabase db = new UserDatabase();
        int uid = db.getUid(userName);
        System.out.println("Kerberos User ID => " + uid);
    }
}

class LocalAPIPluggableAuthentication extends LocalAPI implements PluggableAuthentication {
    private static LocalAPIPluggableAuthentication localAdapterInstance = null;
    LocalAPI localAPI = new LocalAPI();

    private LocalAPIPluggableAuthentication() {

    }

    public static LocalAPIPluggableAuthentication getLocalAdapterInstance() {
        if (localAdapterInstance == null) {
            return new LocalAPIPluggableAuthentication();
        } else {
            return localAdapterInstance;
        }
    }

    @Override
    public int authenticate(String name, String password) {

        return localAPI.local_authenticate(name, password);
    }

    @Override
    public void getUid(String userName) {
        localAPI.local_getUid(userName);
    }

}

class LdapAPIPluggableAuthentication extends LdapAPI implements PluggableAuthentication {
    private static LdapAPIPluggableAuthentication ldapAdapterInstance = null;
    LdapAPI ldapAPI = new LdapAPI();

    private LdapAPIPluggableAuthentication() {
    }

    public static LdapAPIPluggableAuthentication getLdapAdapterInstance() {
        if (ldapAdapterInstance == null) {
            return new LdapAPIPluggableAuthentication();
        } else {
            return ldapAdapterInstance;
        }
    }

    @Override
    public int authenticate(String name, String password) {
        return ldapAPI.ldap_authenticate(name, password);
    }

    @Override
    public void getUid(String userName) {
        ldapAPI.ldap_getUid(userName);
    }
}

class KerberosAPIPluggableAuthentication extends KerberosAPI implements PluggableAuthentication {
    private static KerberosAPIPluggableAuthentication kerberosAdapterInstance = null;
    KerberosAPI kerberosAPI = new KerberosAPI();

    private KerberosAPIPluggableAuthentication() {
    }

    public static KerberosAPIPluggableAuthentication getKerberosAdapterInstance() {
        if (kerberosAdapterInstance == null) {
            return new KerberosAPIPluggableAuthentication();
        } else {
            return kerberosAdapterInstance;
        }
    }

    @Override
    public int authenticate(String name, String password) {

        return kerberosAPI.kerberos_authenticate(name, password);
    }

    @Override
    public void getUid(String userName) {
        kerberosAPI.kerberos_getUid(userName);
    }
}



