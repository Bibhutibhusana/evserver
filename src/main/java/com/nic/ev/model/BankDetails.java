package com.nic.ev.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@Entity
@Table(name="evt_bank_details")
@JsonIgnoreProperties(ignoreUnknown=true)
@XmlRootElement(name  = "beneficiary")
public class BankDetails {
	
	@Id
	private String regnNo;
	
	@JacksonXmlProperty(localName="name")
	private String name;
	
	private String bankName;
	
	@JacksonXmlProperty(isAttribute = false)
	private String branchName;
	
	@JacksonXmlProperty(localName= "ifsc")
	private String ifscCode;
	
	@JacksonXmlProperty(localName = "accountNo")
	private String accNo;
	
	@JacksonXmlProperty(isAttribute = false)
	private Date op_dt;
	
	private byte[] passbookImg;

	private String applNo;
	
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
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BankDetails other = (BankDetails) obj;
       
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }

        return Objects.equals(this.regnNo, other.regnNo);
    }
}
