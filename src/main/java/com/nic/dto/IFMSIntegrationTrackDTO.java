package com.nic.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class IFMSIntegrationTrackDTO {

	private Long id;
	
	private String fileRefId;
	
	private String billNo;
	
	private String billRefNo;
	
	private String resFileName;
	
	private String applNo;
	 
	private String regnNo;

	private String accNo;
	 
	private String name;
	
	private String ifsc;
	
	private String submitStatus;
	
	private Date submitDate;
	
	private String submitErr;
	
	private String ackStatus;
	
	private Date ackDate;
	
	private String ackErr;
	
	private String billStatus;
	
	
	private String billStatusErr;
	
	private String checkStatus;
	
	private Date checkStatusErr;
	
	private Date checkStatusDate;
	
	private String voucherNo;
	
	private Date voucherDate;
	
	private String billStatusString;
	
	private String utrNo;

	private Date utrDate;
	
	private String benfPaymentStatus;
	
	private String benefBillStatus;
	
	private String revertStatus;
	
	private Date revertStatusDate;
	
	private String ddoCheckStatus;
	
	private Date ddoCheckStatusDate;
	
	private String rtoCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileRefId() {
		return fileRefId;
	}

	public void setFileRefId(String fileRefId) {
		this.fileRefId = fileRefId;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBillRefNo() {
		return billRefNo;
	}

	public void setBillRefNo(String billRefNo) {
		this.billRefNo = billRefNo;
	}

	public String getResFileName() {
		return resFileName;
	}

	public void setResFileName(String resFileName) {
		this.resFileName = resFileName;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getRegnNo() {
		return regnNo;
	}

	public void setRegnNo(String regnNo) {
		this.regnNo = regnNo;
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

	public String getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getSubmitErr() {
		return submitErr;
	}

	public void setSubmitErr(String submitErr) {
		this.submitErr = submitErr;
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

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public String getBillStatusErr() {
		return billStatusErr;
	}

	public void setBillStatusErr(String billStatusErr) {
		this.billStatusErr = billStatusErr;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Date getCheckStatusErr() {
		return checkStatusErr;
	}

	public void setCheckStatusErr(Date checkStatusErr) {
		this.checkStatusErr = checkStatusErr;
	}

	public Date getCheckStatusDate() {
		return checkStatusDate;
	}

	public void setCheckStatusDate(Date checkStatusDate) {
		this.checkStatusDate = checkStatusDate;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public Date getVoucherDate() {
		return voucherDate;
	}

	public void setVoucherDate(Date voucherDate) {
		this.voucherDate = voucherDate;
	}

	public String getBillStatusString() {
		return billStatusString;
	}

	public void setBillStatusString(String billStatusString) {
		this.billStatusString = billStatusString;
	}

	public String getUtrNo() {
		return utrNo;
	}

	public void setUtrNo(String utrNo) {
		this.utrNo = utrNo;
	}

	public Date getUtrDate() {
		return utrDate;
	}

	public void setUtrDate(Date utrDate) {
		this.utrDate = utrDate;
	}

	public String getBenfPaymentStatus() {
		return benfPaymentStatus;
	}

	public void setBenfPaymentStatus(String benfPaymentStatus) {
		this.benfPaymentStatus = benfPaymentStatus;
	}

	public String getBenefBillStatus() {
		return benefBillStatus;
	}

	public void setBenefBillStatus(String benefBillStatus) {
		this.benefBillStatus = benefBillStatus;
	}

	public String getRevertStatus() {
		return revertStatus;
	}

	public void setRevertStatus(String revertStatus) {
		this.revertStatus = revertStatus;
	}

	public Date getRevertStatusDate() {
		return revertStatusDate;
	}

	public void setRevertStatusDate(Date revertStatusDate) {
		this.revertStatusDate = revertStatusDate;
	}

	public String getDdoCheckStatus() {
		return ddoCheckStatus;
	}

	public void setDdoCheckStatus(String ddoCheckStatus) {
		this.ddoCheckStatus = ddoCheckStatus;
	}

	public Date getDdoCheckStatusDate() {
		return ddoCheckStatusDate;
	}

	public void setDdoCheckStatusDate(Date ddoCheckStatusDate) {
		this.ddoCheckStatusDate = ddoCheckStatusDate;
	}

	public String getRtoCode() {
		return rtoCode;
	}

	public void setRtoCode(String rtoCode) {
		this.rtoCode = rtoCode;
	}
}
