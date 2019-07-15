package jndildap;

import javax.naming.NamingException;

public interface Ldap {
    public void connect(String username, String password) throws NamingException;
    public String search(String para) throws NamingException;
    public void update() throws NamingException;
    public void add() throws NamingException;
    public void delete() throws NamingException;
    public void close() throws NamingException;
}
