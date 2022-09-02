package com.nic.ev.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="evh_bank_details")
public class BankDetailsHistory {


	@Id
	private String regnNo;
	
	private String name;
	
	private String bankName;
	
	private String branchName;
	
	private String ifscCode;
	
	private String accNo;
	
	private Date op_dt;
	
	private byte[] passbookImg;

	private String applNo;
	
	private Date inserDt;

	public String getRegnNo() {
		return regnNo;
	}

	public void setRegnNo(String regnNo) {
		this.regnNo = regnNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public Date getOp_dt() {
		return op_dt;
	}

	public void setOp_dt(Date op_dt) {
		this.op_dt = op_dt;
	}

	public byte[] getPassbookImg() {
		return passbookImg;
	}

	public void setPassbookImg(byte[] passbookImg) {
		this.passbookImg = passbookImg;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public Date getInserDt() {
		return inserDt;
	}

	public void setInserDt(Date inserDt) {
		this.inserDt = inserDt;
	}
	
	
}
