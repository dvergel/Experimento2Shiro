/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author Dev
 */
public class AutenticatorFilter extends AuthorizingRealm {
    
    private Realm realm = new JdbcRealm();
    private DefaultSecurityManager sm = new DefaultSecurityManager(realm);
 
    public AutenticatorFilter(){
        setName("AutenticatorFilter");
    }
 
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(
        AuthenticationToken authToken) throws AuthenticationException {
 
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
 
        SecurityUtils.setSecurityManager(sm);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
             return new SimpleAuthenticationInfo(
                token.getUsername(), token.getPassword(), getName());
        } catch (Exception ex) {
            throw new AuthenticationException(
                "Username not found: " + token.getUsername());
        }
        
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        throw new UnsupportedOperationException("No hay permisos sobre este recurso por HTTP");
    }
}
