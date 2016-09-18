package com.wn.webapp.platform.account.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import com.wn.webapp.core.orm.jap.XqlcJpaRepository;
import com.wn.webapp.platform.account.entity.Menu;

public interface MenuDao extends XqlcJpaRepository<Menu, String> {
	
	/**
	 * 查找所有可见的菜单
	 * @return 菜单集合
	 */
	@Query("select m from Menu m where m.visible=true and m.isDelete=false")
	public abstract List<Menu> findAllVisible();

	/**
	 * 根据排序查找所有可见的菜单
	 * @param paramSort 排序规则
	 * @return 菜单集合
	 */
	@Query("select m from Menu m where m.visible=true and m.isDelete=false")
	public abstract List<Menu> findAllVisible(Sort paramSort);

	/**
	 * 根据根节点查找所有可见的菜单
	 * @param isRoot 是否为根节点 true/false
	 * @return 菜单集合
	 */
	@Query("select m from Menu m where m.visible=true and m.isDelete=false and m.isRoot=?1")
	public abstract List<Menu> findAllVisible(Boolean isRoot);

	/**
	 * 根据url和排序规则查找所有可见的菜单
	 * @param url 菜单路径
	 * @param paramSort 排序规则
	 * @return 菜单集合
	 */
	@Query("select m from Menu m where m.visible=true and m.isDelete=false and m.url like ?1")
	public abstract List<Menu> findVisibleByUrl(String url, Sort paramSort);
}
