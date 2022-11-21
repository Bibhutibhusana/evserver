package com.nic.ev.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.nic.ev.ifms.utils.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name="evt_bank_details")
public class BankDetails {
	
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
	
	private String accType;
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "benf_id_sequence")
//    @GenericGenerator(
//            name = "benf_id_sequence", 
//            strategy = "com.nic.ev.ifms.utils.StringPrefixedSequenceIdGenerator", 
//            parameters = { 
//                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
//                    @Parameter(name = StringPrefixedSequenceIdGenerator.CODE_NUMBER_SEPARATOR_PARAMETER, value = "_"), 
//                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
    
	private String benfId;
	
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

	public void setApplNo(String appNo) {
		this.applNo = appNo;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getBenfId() {
		return benfId;
	}

	public void setBenfId(String benfId) {
		this.benfId = benfId;
	}
	
	

}
