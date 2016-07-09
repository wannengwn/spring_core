package com.wn.webapp.platform.account.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;
import com.wn.webapp.core.orm.entity.IdEntity;

@Entity
@Table(name = "t_p_account_permission")
public class Permission extends IdEntity implements Serializable {
	private static final long serialVersionUID = 8902025344953872667L;
	private String name;//权限名称
	private Permission permission;//权限
	private List<Role> roles = Lists.newArrayList();//角色
	private Boolean enable;//是否启用
	private Boolean isDelete;//是否删除
	private List<Permission> children = Lists.newArrayList();//子节点
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pid")
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	@ManyToMany(cascade={javax.persistence.CascadeType.REFRESH}, fetch=FetchType.LAZY, mappedBy="permissions")
	@Fetch(FetchMode.SUBSELECT)
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	@OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="permission")
	@Fetch(FetchMode.SUBSELECT)
	public List<Permission> getChildren() {
		return children;
	}
	public void setChildren(List<Permission> children) {
		this.children = children;
	}
	
	
}
