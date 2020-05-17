//Facade
public class OperatingSystem {
    UserDatabase db = new UserDatabase();
    public void authenticate(String username, String pwd){
        Authenticate localAuth = Local.getLocalAuthInstance();
        Authenticate ldapAuth = Ldap.getLdapAuthInstance();
        Authenticate kerberosAuth = Kerberos.getKerberosAuthInstance();

        int uid = db.getUid(username);
        String uidString = String.valueOf(uid);

        String accessRoleCode= "undefined";
        // local users' id starts with 100
        // ldap users' id starts with 200
        // kerberos users' id start with 300
        // other ids do not have any access permission

        if(uidString.startsWith("100")){
            accessRoleCode = "local";
        }else if(uidString.startsWith("200")){
            accessRoleCode = "ldap";
        }else if (uidString.startsWith("300")){
            accessRoleCode = "kerberos";
        }else {
            accessRoleCode = "undefined";
        }

        int rc;

        switch (accessRoleCode){
            case "local":
                rc = localAuth.authenticateByGivenAccess(username,pwd);
                break;
            case "ldap":
               rc = ldapAuth.authenticateByGivenAccess(username,pwd);
                break;
            case "kerberos":
                rc = kerberosAuth.authenticateByGivenAccess(username,pwd);
                break;
            default:
                rc = 1;
                break;
        }

        if(rc == 0){
        System.out.println("User logged in successfully! <<Facade>>");
            uid = db.getUid(username);
            // TODO setUid ???
        }else{
            System.out.println("HATALI GİRİŞ DENEMESİ");
        }

    };
}
