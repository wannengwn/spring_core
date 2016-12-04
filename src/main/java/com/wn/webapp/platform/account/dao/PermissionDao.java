package com.wn.webapp.platform.account.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import com.wn.webapp.core.orm.jap.XqlcJpaRepository;
import com.wn.webapp.platform.account.entity.Menu;
import com.wn.webapp.platform.account.entity.Permission;

public interface PermissionDao extends XqlcJpaRepository<Menu, String> {

//	@Query("select p from Permission p where p.visible=true and p.isDelete=false")
//	public abstract List<Permission> findAllVisible();
//
//	@Query("select p from Permission p where p.visible=true and p.isDelete=false")
//	public abstract List<Permission> findAllVisible(Sort paramSort);
//
//	@Query("select p from Permission p where p.visible=true and p.isDelete=false and p.isRoot=?1")
//	public abstract List<Permission> findAllVisible(Boolean isRoot);
//
//	@Query("select p from Permission p where p.visible=true and p.isDelete=false and p.url like ?1")
//	public abstract List<Permission> findVisibleByUrl(String paramString, Sort paramSort);
}
