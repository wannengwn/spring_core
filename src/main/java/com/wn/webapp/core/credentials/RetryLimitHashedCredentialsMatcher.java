package com.wn.webapp.core.credentials;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

/**
 * 
 * @author NENG
 *
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
	
    private Ehcache passwordRetryCache;
    private int limit = 5;
    
    @Resource(name="shiroEhcacheManager")
    private EhCacheManager cacheManager;
    
    public void init(){
      passwordRetryCache = cacheManager.getCacheManager().getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
        //retry count + 1
        Element element = passwordRetryCache.get(username);
        if(element == null) {
            element = new Element(username , new AtomicInteger(0));
            passwordRetryCache.put(element);
        }
        AtomicInteger retryCount = (AtomicInteger)element.getObjectValue();
        //验证登录次数
        if(retryCount.incrementAndGet() > limit) {
            //if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //clear retry count
            passwordRetryCache.remove(username);
        }
        return matches;
    }
    
   
}
