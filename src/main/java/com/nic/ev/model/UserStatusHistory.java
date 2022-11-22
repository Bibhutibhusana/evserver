package com.nic.ev.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="evh_status")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserStatusHistory {
	
	@Id
	private String regnNo;
	
	private String applNo;
	
	
	private String userRegistered;
	
	
	private String verification;
	
	private String approval;
	
	private String payment;
	
	private Date opDt;
	
	private Date verifyDt;
	
	private Date approveDt;
	
	private String reason;
	
	private String verifyUserId;
	
	private String approveUserId;
	
	private String downloadStatus;
	
	private Date insertDt;

	public String getRegnNo() {
		return regnNo;
	}

	public void setRegnNo(String regnNo) {
		this.regnNo = regnNo;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getUserRegistered() {
		return userRegistered;
	}

	public void setUserRegistered(String userRegistered) {
		this.userRegistered = userRegistered;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Date getOpDt() {
		return opDt;
	}

	public void setOpDt(Date opDt) {
		this.opDt = opDt;
	}

	public Date getVerifyDt() {
		return verifyDt;
	}

	public void setVerifyDt(Date verifyDt) {
		this.verifyDt = verifyDt;
	}

	public Date getApproveDt() {
		return approveDt;
	}

	public void setApproveDt(Date approveDt) {
		this.approveDt = approveDt;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getVerifyUserId() {
		return verifyUserId;
	}

	public void setVerifyUserId(String verifyUserId) {
		this.verifyUserId = verifyUserId;
	}

	public String getApproveUserId() {
		return approveUserId;
	}

	public void setApproveUserId(String approveUserId) {
		this.approveUserId = approveUserId;
	}

	public String getDownloadStatus() {
		return downloadStatus;
	}

	public void setDownloadStatus(String downloadStatus) {
		this.downloadStatus = downloadStatus;
	}

	public Date getInsertDt() {
		return insertDt;
	}

	public void setInsertDt(Date insertDt) {
		this.insertDt = insertDt;
	}
	
	

}
