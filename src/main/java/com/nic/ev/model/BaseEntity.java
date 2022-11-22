package com.nic.ev.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="CREATEDBY")
	private Long createdBy;

	@Column(name="CREATEDDATETIME")
	private Date createdDatetime;
	
	@Column(name="UPDATEDBY")
	private Long updatedBy;
	
	@Column(name="UPDATEDDATETIME")
	private Date updatedDatetime;
	
	@Column(name="HIBVERSION")
	@Version
	private Long versionNo;

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(Date updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}
	
	public BaseEntity() {
		
	}
	
	public BaseEntity(Long createdBy, Date createdDatetime) {
		this.createdBy = createdBy;
		this.createdDatetime = createdDatetime == null ? null : new Timestamp(
				createdDatetime.getTime());
	}
	
	public BaseEntity(Long createdBy, Date createdDatetime,Long versionNo) {
		this.createdBy = createdBy;
		this.createdDatetime = createdDatetime == null ? null : new Timestamp(
				createdDatetime.getTime());
		this.versionNo = versionNo;
	}
	
	public BaseEntity(Long createdBy, Date createdDatetime, Long updatedBy,
			Date updatedDatetime) {
		this.createdBy = createdBy;
		this.createdDatetime = createdDatetime == null ? null : new Timestamp(
				createdDatetime.getTime());
		this.updatedBy = updatedBy;
		this.updatedDatetime = updatedDatetime == null ? null : new Timestamp(
				updatedDatetime.getTime());
	}
}
