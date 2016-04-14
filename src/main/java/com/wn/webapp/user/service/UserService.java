package com.wn.webapp.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wn.webapp.user.dao.UserDao;
import com.wn.webapp.user.entity.User;
import com.wn.webapp.utils.PasswordHelper;

@Component
@Transactional
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public boolean isRootUser(String loginName){
	    return "root".equals(loginName);
	}

	public User findUserByLoginName(String loginName){
	    if (isRootUser(loginName)) {
	      User user = new User();
	      user.setId("root");
	      user.setLoginName("root");
	      user.setUserName("超级管理员");;
	      user.setLocked(User.Status.NORMAL);
	      return user;
	    }
	    return userDao.findOneByLoginName(loginName);
	}
	public void save(User user){
		PasswordHelper passwordHelper = new PasswordHelper();
		//加密密码
        passwordHelper.encryptPassword(user);
		userDao.save(user);
	}
	
	public User findOneByLoginName(String loginName){
		User user = userDao.findOneByLoginName(loginName);
		return user ;
	};
	
}
