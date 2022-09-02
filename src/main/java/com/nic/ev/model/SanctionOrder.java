package com.nic.ev.model;

import java.sql.Date;

public class SanctionOrder {

	private Date approveDt; 
	  private int sub_amnt;
	  private String inWords;
	  private String ownerName;
	  private String regnNo;
	  private String chequeNo;
	public Date getApproveDt() {
		return approveDt;
	}
	public void setApproveDt(Date approveDt) {
		this.approveDt = approveDt;
	}
	public int getSub_amnt() {
		return sub_amnt;
	}
	public void setSub_amnt(int sub_amnt) {
		this.sub_amnt = sub_amnt;
	}
	public String getInWords() {
		return inWords;
	}
	public void setInWords(String inWords) {
		this.inWords = inWords;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getRegnNo() {
		return regnNo;
	}
	public void setRegnNo(String regnNo) {
		this.regnNo = regnNo;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	  
	  
}