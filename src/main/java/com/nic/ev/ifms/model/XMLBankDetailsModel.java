package com.nic.ev.ifms.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


@Entity
@Table(name="evt_bank_details")
@XmlRootElement(name  = "beneficiary")
public class XMLBankDetailsModel {
	
	@Id
	//@JacksonXmlProperty
	@JsonIgnore
	private String regnNo;
	
	//@JacksonXmlProperty
	@JsonIgnore
	private String bankName;
	
	@JacksonXmlProperty(isAttribute = false)
	@JsonIgnore
	private String branchName;
	
	@JacksonXmlProperty(localName = "accountNo")
	private String accNo;
	
	@JacksonXmlProperty(localName= "ifsc")
	private String ifscCode;
	
	@JacksonXmlProperty(localName="name")
	private String name;
	
	
	@JacksonXmlProperty(isAttribute = false)
	@JsonIgnore
	@Column(name="op_dt")
	private Date opDt;
	
	//@JacksonXmlProperty
	@JsonIgnore
	private String applNo;

	public String getRegnNo() {
		return regnNo;
	}

	public void setRegnNo(String regnNo) {
		this.regnNo = regnNo;
	}

	
	public String getBankName() {
		return bankName;
	}


	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	
	
	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Date getOpDt() {
		return opDt;
	}

	public void setOpDt(Date opDt) {
		this.opDt = opDt;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
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
	        final XMLBankDetailsModel other = (XMLBankDetailsModel) obj;
	       
	        if (!Objects.equals(this.name, other.name)) {
	            return false;
	        }

	        return Objects.equals(this.regnNo, other.regnNo);
	    }

}
