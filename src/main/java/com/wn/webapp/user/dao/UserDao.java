package com.wn.webapp.user.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.wn.webapp.user.entity.User;



public interface UserDao extends PagingAndSortingRepository<User, String> {
	
	@Query("select u from User u  where  u.loginName=?1")
	public abstract User findOneByLoginName(String paramString);
}
