package com.wn.webapp.platform.account.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;
import com.wn.webapp.core.orm.entity.IdEntity;

@Entity
@Table(name = "t_p_account_role")
public class Role extends IdEntity implements Serializable {
	private static final long serialVersionUID = 6455070286165540936L;
	private String name;//角色名称
	private String description;//角色描述
	private Boolean enable;//是否启用
	private Boolean isDelete;//是否删除
	private List<User> users = Lists.newArrayList();//对应用户
	private List<Permission> permissions = Lists.newArrayList();//对应权限
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	@ManyToMany(cascade={javax.persistence.CascadeType.REFRESH}, fetch=FetchType.LAZY, mappedBy="roles")
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("loginName")
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@ManyToMany(cascade={javax.persistence.CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinTable(name="t_p_account_role_permission", joinColumns={@JoinColumn(name="role_id")}, inverseJoinColumns={@JoinColumn(name="permission_id")})
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("name")
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	
}
