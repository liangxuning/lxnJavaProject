package jndildap;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

public class LdapImpl implements Ldap
{
    private DirContext ds;

    @Override
    public synchronized void connect(String username, String password) throws NamingException
    {
        Hashtable<String, Object> env=new Hashtable<String,Object>(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://10.31.2.45:389/");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, username);
        env.put(Context.SECURITY_CREDENTIALS, password);
        ds=new InitialDirContext(env);
    }

    @Override
    public String search(String para) throws NamingException
    {
        String password=null;
        SearchControls searchCtls=new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
//        String searchFilter="objectClass="+para;
        String searchFilter="gidNumber="+para;
//        Attributes atr = new A
        String searchBase="cn=OMC-R,dc=my-domain,dc=com";
        String returnedAttrs[]={"omc-user-account-name"};
        searchCtls.setReturningAttributes(returnedAttrs);
        NamingEnumeration<SearchResult> entries=ds.search(searchBase, searchFilter, searchCtls);
//        NamingEnumeration<SearchResult> entries=ds.search(searchBase, searchFilter, searchCtls);
        while(entries.hasMoreElements()){
            SearchResult entry=entries.next();
            Attributes attrs=entry.getAttributes();
            System.out.println(attrs.get("omc-user-account-name"));
        }
        while(entries.hasMoreElements()){
            SearchResult entry=entries.next();
            Attributes attrs=entry.getAttributes();
            if(attrs!=null){
                for(NamingEnumeration<? extends Attribute> names=attrs.getAll();names.hasMore();){
                    Attribute attr=names.next();
                    for(NamingEnumeration<?> e =attr.getAll();e.hasMore();){
                        byte[] s=(byte[]) e.next();
                        password=new String(s);
                    }
                }
            }
        }
        return password;
    }

    @Override
    public void update() throws NamingException
    {
        ModificationItem[] mods=new ModificationItem[1];
        Attribute attr=new BasicAttribute("sn","张三");
        mods[0]=new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);
        ds.modifyAttributes("uid=test,o=founder,c=com", mods);
    }

    @Override
    public void add() throws NamingException
    {
        Attributes attrs=new BasicAttributes();
        attrs.put("uid","test");
        attrs.put("sn","test");
        attrs.put("cn","test Test");
        attrs.put("userPassword", "222");
        Attribute objclass=new BasicAttribute("objectClass");
//        objclass.add("persion");
        objclass.add("top");
//        objclass.add("organizationalPerson");
        objclass.add("inetOrgPerson");
        attrs.put(objclass);
        this.ds.createSubcontext("cn=test Test,dc=my-domain,dc=com", attrs);
    }

    @Override
    public void delete() throws NamingException
    {
        this.ds.destroySubcontext("cn=test Test,dc=my-domain,dc=com");
    }

    @Override
    public void close() throws NamingException
    {
        ds.close();
    }

    public DirContext getDs() {
        return ds;
    }

    public void setDs(DirContext ds) {
        this.ds = ds;
    }
}

