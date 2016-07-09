package com.wn.webapp.platform.account.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.wn.webapp.core.orm.entity.IdEntity;

@Entity
@Table(name = "t_p_account_user_image")
public class UserImage extends IdEntity implements Serializable {

	private static final long serialVersionUID = -6447864107407469166L;
	private byte[] source;//资源
	private byte[] big;//大图
	private byte[] normal;//正常
	private byte[] small;//小图

	@Lob
	@Basic(fetch = FetchType.LAZY)
	public byte[] getSource() {
		return this.source;
	}

	public void setSource(byte[] source) {
		this.source = source;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	public byte[] getBig() {
		return this.big;
	}

	public void setBig(byte[] big) {
		this.big = big;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	public byte[] getNormal() {
		return this.normal;
	}

	public void setNormal(byte[] normal) {
		this.normal = normal;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	public byte[] getSmall() {
		return this.small;
	}

	public void setSmall(byte[] small) {
		this.small = small;
	}

}
