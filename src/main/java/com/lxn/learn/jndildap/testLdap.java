package com.lxn.learn.jndildap;

import javax.naming.NamingException;

public class testLdap {
    public static void main(String[] args) {
        LdapImpl ldap = new LdapImpl();
        try {
            ldap.connect("cn=Manager,dc=my-domain,dc=com", "123");
//            ldap.add();
//            ldap.delete();
            ldap.search("500");
            ldap.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }
}
