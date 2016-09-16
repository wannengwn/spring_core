package com.wn.webapp.platform.account.dao;

import org.springframework.data.jpa.repository.Query;
import com.wn.webapp.core.orm.jap.XqlcJpaRepository;
import com.wn.webapp.platform.account.entity.User;

public interface UserDao extends XqlcJpaRepository<User, String> {
	
	@Query("select u from User u  where  u.loginName=?1")
	public abstract User findOneByLoginName(String paramString);
}
