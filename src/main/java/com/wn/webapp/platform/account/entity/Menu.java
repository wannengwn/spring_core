package com.wn.webapp.platform.account.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;
import com.wn.webapp.core.orm.entity.IdEntity;

@Entity
@Table(name = "t_p_account_menu")
public class Menu extends IdEntity implements Serializable {
	private static final long serialVersionUID = 5902247051054622049L;
	private String name;//菜单名称
	private String url;//菜单url
	private Boolean isRoot;//是否是根节点
	private Boolean visible;//是否可见
	private Boolean isDelete;//是否删除
	private String iconClass;//样式
	private String description;//描述
	private Permission permission;//权限
	private Menu menu;
	private List<Menu> children = Lists.newArrayList();//子节点
	
	public Menu(){
		this.isRoot = Boolean.FALSE;
		this.visible = Boolean.FALSE;
		this.isDelete = Boolean.FALSE;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getIsRoot() {
		return isRoot;
	}
	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * ManyToOne(fetch=FetchType.LAZY)
	 * fetch=FetchType.LAZY：懒加载，加载一个实体时，定义懒加载的属性不会马上从数据库中加载。
	 * fetch=FetchType.EAGER：急加载，加载一个实体时，定义急加载的属性会立即从数据库中加载。
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="permission_id")
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mid")
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="menu")
	@Fetch(FetchMode.SUBSELECT)
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
}
