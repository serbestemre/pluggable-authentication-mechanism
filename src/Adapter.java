interface Adapter {
    int authenticate(String name, String password);
}

class LocalAdaptee  {
    int local_authenticate(String userName, String password){
        System.out.println("Connecting to OS via LOCAL adaptee....");
        Authenticate localAuth = Local.getLocalAuthInstance();
        return localAuth.authenticateByGivenAccess(userName, password);
    }
}

class LdapAdaptee{
    int ldap_authenticate(String userName, String password){
        System.out.println("Connecting to OS via LDAP adaptee...");
        Authenticate ldapAuth = Ldap.getLdapAuthInstance();
        return ldapAuth.authenticateByGivenAccess(userName, password);
    }
}

class KerberosAdaptee {
    int kerberos_authenticate(String userName, String password){
        System.out.println("Connecting to OS via KERBEROS adaptee");
        Authenticate kerberosAut = Kerberos.getKerberosAuthInstance();
        return kerberosAut.authenticateByGivenAccess(userName, password);
    }
}

class LocalAdapter extends LocalAdaptee implements Adapter {
    @Override
    public int authenticate(String name, String password) {
        LocalAdaptee local = new LocalAdaptee();
        return local.local_authenticate(name, password);
    }
}

class LdapAdapter extends LocalAdaptee implements Adapter {
    @Override
    public int authenticate(String name, String password) {
        LdapAdaptee ldap = new LdapAdaptee();
        return ldap.ldap_authenticate(name, password);
    }
}

class KerberosAdapter extends KerberosAdaptee implements Adapter {
    @Override
    public int authenticate(String name, String password) {
        KerberosAdaptee kerberos = new KerberosAdapter();
        return kerberos.kerberos_authenticate(name, password);

    }
}



