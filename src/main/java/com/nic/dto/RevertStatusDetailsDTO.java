package com.nic.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class RevertStatusDetailsDTO {
	private int id;
	private String regnNo;
	private String applNo;
	private String verification;
	private String approval;
	private Date opDt;
	private String reason;
	private String verifyUserId;
	private String approveUserId;
	private Date insertDt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public Date getOpDt() {
		return opDt;
	}
	public void setOpDt(Date opDt) {
		this.opDt = opDt;
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
	public Date getInsertDt() {
		return insertDt;
	}
	public void setInsertDt(Date insertDt) {
		this.insertDt = insertDt;
	}
	
}
