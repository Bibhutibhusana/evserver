package com.nic.ev.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.nic.ev.ifms.model.webservice.ByTransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	
@MappedSuperclass
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 6950438514663523722L;

	@Column(name="CREATEDBY")
	private Long createdBy;
	
	@Column(name="CREATEDDATE")
	private Date createdDate;
	
	@Column(name="UPDATEDBY")
	private Long updatedBy;
	
	@Column(name="UPDATEDDATE")
	private Date updatedDate = new Date();
	
	@Column(name="HIBVERSIONNO")
	@Version
	private Long versionNo;
	
	
	
	public BaseEntity() {
		
	}
	
	public BaseEntity(Long createdBy, Date createdDate) {
		this.createdBy = createdBy;
		this.createdDate = createdDate == null ? null : new Timestamp(
				createdDate.getTime());
	}
	
	public BaseEntity(Long createdBy, Date createdDate,Long versionNo) {
		this.createdBy = createdBy;
		this.createdDate = createdDate == null ? null : new Timestamp(
				createdDate.getTime());
		this.versionNo = versionNo;
	}

	public BaseEntity(Long createdBy, Date createdDate, Long updatedBy,
			Date updatedDate,Long versionNo) {
		this.createdBy = createdBy;
		this.createdDate = createdDate == null ? null : new Timestamp(
				createdDate.getTime());
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate == null ? null : new Timestamp(
				updatedDate.getTime());
		this.versionNo = versionNo;
	}

}
