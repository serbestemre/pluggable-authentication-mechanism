# Pluggable Authentication Mechanism

This repository is created for Software Architure Design course project. The goal of this project is to simulate a simple authentication mechanism to the newly written Operating System. In a pluggable authentication mechanism different authentication mechanism can be used. Every user has a username, user id and a password. The operating system provides the following API's to the user applications for performing login to the system:

# Design Documents

* [Patterns Motivation](https://github.com/serbestemre/pluggable-authentication-mechanism/wiki/Patterns-Motivation)
* [UML Diagram](https://github.com/serbestemre/pluggable-authentication-mechanism/blob/master/Project4-UML-Diagram.jpeg)
* [Detailed Document](https://github.com/serbestemre/pluggable-authentication-mechanism/blob/master/SE%20311%20PROJECT.REPORT.docx)



# Requirements

- Use at least 5 design patterns to solve this problem.
- You ill not write a real-word application. You will simulate various functions of it.
- The whole design must be a coherent one.


```
int authenticate (string userName, string pwd) //authenticates the user using the name and password

int get uid(string name); //get the userid corresponding to the user
int setuid(int uid); // sets the userid of the user for the session
string get username(); // reads the username from the user
string getpassword(); // reads the password from the user
```

* The authentication flow looks like the following

```
name = getusername()
pwd = getpassword()
rc = authenticate(name, pwd)
if(rc== 0) // success
  uid = getuid(name)
  setuid(uid)
```

One of the three authentication mechanism will be used to authenticate a user to the system. 

- LOCAL- local file system
- LDAP - Lightweight Directory Access Protocol
- KERBEROS - A third party authentication mechanism.

The user is defined in only one of these mechanisms. Therefore user must be searched until found where to registered. Unfortunately, each of the mechanisms provides their own set of APIs to be used. The APIs differ only in the name. The parameter and return types are same.

| LOCAL  | LDAP | KERBEROS | 
| ------------- | ------------- | ------------- |
| local_authenticate()  | ldap_authenticate()  | krb_authenticate() | 
| local_getuid()    | ldap_getuid()  | krb_getuid() |


The goal is to write an authentication application for the Operating System that will support the three authentication mechanisms given above. The OS does not have two LOCAL, LDAP, or KERBEROS authentication modules. It only has one module for each.

