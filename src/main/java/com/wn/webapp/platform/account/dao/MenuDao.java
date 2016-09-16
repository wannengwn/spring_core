package com.wn.webapp.platform.account.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import com.wn.webapp.core.orm.jap.XqlcJpaRepository;
import com.wn.webapp.platform.account.entity.Menu;

public interface MenuDao extends XqlcJpaRepository<Menu, String> {

	@Query("select m from Menu m where m.visible=true and m.visible=false")
	public abstract List<Menu> findAllVisible();
	
	@Query("select m from Menu m where m.visible=true and m.visible=false")
	public abstract List<Menu> findAllVisible(Sort paramSort);

	@Query("select m from Menu m where m.visible=true and m.url like ?1")
	public abstract List<Menu> findVisibleByLikeUrl(String paramString, Sort paramSort);
}
