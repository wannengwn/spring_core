package com.wn.webapp.realm;


import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wn.webapp.user.entity.User;
import com.wn.webapp.user.service.UserService;

/**
 * 
 * @author NENG
 *
 */
@Component
public class ShiroDatabaseRealm extends AuthorizingRealm {
	
	private Logger logger = LoggerFactory.getLogger(ShiroDatabaseRealm.class);
	@Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<String>();
        roles.add(username);
        Set<String> permissions = new HashSet<String>();
        permissions.add("helloWorldController01:list");
        permissions.add("helloWorldController02:list");
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	logger.info("doGetAuthenticationInfo");
        String loginName = (String)token.getPrincipal();
        logger.info("当前登录用户名:"+loginName);
        if (userService.isRootUser(loginName)){
        	//超级管理员root  123
        	return new SimpleAuthenticationInfo("root", "aeeaa849d945f2e80a5ba468672edc54", 
        			ByteSource.Util.bytes("root21a5cb0e348522d2b9e77bf5e32688a2"), 
        			getName());
        }
        User user = userService.findOneByLoginName(loginName);
        if(user == null || (user.getIsDelete().equals(true))) {
            throw new UnknownAccountException();//没找到帐号
        }

        if(User.Status.LOCKED.equals(user.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getLoginName(), //用户名
                user.getPassWord(), //密码
                ByteSource.Util.bytes(user.credentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
