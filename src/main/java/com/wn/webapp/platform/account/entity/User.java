package com.wn.webapp.platform.account.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;
import com.wn.webapp.core.orm.entity.IdEntity;


@Entity
@Table(name="t_p_account_user")
public class User extends IdEntity implements Serializable{

	private static final long serialVersionUID = 2642824748522386466L;
	private String userName;
	private String loginName;
	private String passWord;
	private String salt;//令牌
	private Boolean enable;//是否启用 true启用 false没启用
	private Status locked;//是否锁定0锁定,1正常
	private Boolean isDelete;//是否删除 true删除 false正常
	private List<Role> roles = Lists.newArrayList();//角色
	private UserImage userImage;
	
	public User(){
		//默认设置启用，正常，不删除状态
		this.enable = Boolean.TRUE;
		this.locked = Status.NORMAL;
		this.isDelete = Boolean.FALSE;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(unique=true, nullable=false)
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	@Column(nullable=false)
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Status getLocked() {
		return locked;
	}

	public void setLocked(Status locked) {
		this.locked = locked;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	@ManyToMany(cascade={javax.persistence.CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinTable(name="t_p_account_user_role", joinColumns={@JoinColumn(name="user_id")}, inverseJoinColumns={@JoinColumn(name="role_id")})
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("name")
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="image_id")
	public UserImage getUserImage() {
		return userImage;
	}
	public void setUserImage(UserImage userImage) {
		this.userImage = userImage;
	}

	//使用Enum字段存储，可以实现更复杂的用户状态实现。
	public static enum Status{
		LOCKED, NORMAL;
	}
	
	public String credentialsSalt() {
        return loginName + salt;
    }
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        return true;
	}
}
