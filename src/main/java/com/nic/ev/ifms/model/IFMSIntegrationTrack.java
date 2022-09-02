package com.nic.ev.ifms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="evt_ifms_transaction")
public class IFMSIntegrationTrack {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="file_name")
	private String fileName;
	
	@Column(name="appl_no")
	private String applNo;
	 
	@Column(name="acc_no")
	private String accNo;
	
	@Column(name="name")
	private String name;
	
	@Column(name="ifsc")
	private String ifsc;
	
	@Column(name="push_date")
	private Date pushDate;
	
	@Column(name="push_status")
	private String pushStatus;
	
	@Column(name="push_err")
	private String pushErr;
	
	@Column(name="ack_status")
	private String ackStatus;
	
	@Column(name="ack_date")
	private Date ackDate;
	
	@Column(name="ack_err")
	private String ackErr;
	
	@Column(name="res_status")
	private String resStatus;
	
	@Column(name="res_date")
	private Date resDate;
	
	@Column(name="res_err")
	private String resErr;
	
	@Column(name="is_verified")
	private Boolean isVerified;
	
	@Column(name="op_dt")
	private Date opDt;
	
	@Column(name="unique_id",unique= true, nullable = false)
	private String uniqueId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public Date getPushDate() {
		return pushDate;
	}

	public void setPushDate(Date pushDate) {
		this.pushDate = pushDate;
	}

	public String getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(String pushStatus) {
		this.pushStatus = pushStatus;
	}

	public String getPushErr() {
		return pushErr;
	}

	public void setPushErr(String pushErr) {
		this.pushErr = pushErr;
	}

	public String getAckStatus() {
		return ackStatus;
	}

	public void setAckStatus(String ackStatus) {
		this.ackStatus = ackStatus;
	}

	public Date getAckDate() {
		return ackDate;
	}

	public void setAckDate(Date ackDate) {
		this.ackDate = ackDate;
	}

	public String getAckErr() {
		return ackErr;
	}

	public void setAckErr(String ackErr) {
		this.ackErr = ackErr;
	}

	public String getResStatus() {
		return resStatus;
	}

	public void setResStatus(String resStatus) {
		this.resStatus = resStatus;
	}

	public Date getResDate() {
		return resDate;
	}

	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}

	public String getResErr() {
		return resErr;
	}

	public void setResErr(String resErr) {
		this.resErr = resErr;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Date getOpDt() {
		return opDt;
	}

	public void setOpDt(Date opDt) {
		this.opDt = opDt;
	}
	
	
}
