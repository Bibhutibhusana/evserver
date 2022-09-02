package com.nic.ev.ifms.model;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLFileDetails {
	


	@Size(max= 2, min=2)
	private String intgCode;
	@Size(max= 2, min=2)
	private String serviceCode;
	private String fileDate;
	private String fileName;
	@Size(max=3, min=3)
	private String fileSerialNumber;
	@Size(max= 5, min=1)
	private Number noOfBeneficiary;
	@Size(max=1, min=1)
	private Character dbtFlag;
	

	@JsonInclude(Include.NON_NULL)
	private String orgFileName;

	@JsonInclude(Include.NON_NULL)
	private String ackStatus;

	@JsonInclude(Include.NON_NULL)
	private String errorCode;
	
	
	
	public String getIntgCode() {
		return intgCode;
	}
	public void setIntgCode(String intgCode) {
		this.intgCode = intgCode;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getFileDate() {
		return fileDate;
	}
	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSerialNumber() {
		return fileSerialNumber;
	}
	public void setFileSerialNumber(String fileSerialNumber) {
		this.fileSerialNumber = fileSerialNumber;
	}
	public Number getNoOfBeneficiary() {
		return noOfBeneficiary;
	}
	public void setNoOfBeneficiary(Number noOfBeneficiary) {
		this.noOfBeneficiary = noOfBeneficiary;
	}
	public Character getDbtFlag() {
		return dbtFlag;
	}
	public void setDbtFlag(Character dbtFlag) {
		this.dbtFlag = dbtFlag;
	}
	public XMLFileDetails(@Size(max = 2, min = 1) String intgCode, @Size(max = 2, min = 1) String serviceCode,
			String fileDate, String fileName, @Size(max = 3, min = 1) String fileSerialNumber,
			@Size(max = 5, min = 1) Number noOfBeneficiary, @Size(max = 1, min = 1) Character dbtFlag) {
		super();
		this.intgCode = intgCode;
		this.serviceCode = serviceCode;
		this.fileDate = fileDate;
		this.fileName = fileName;
		this.fileSerialNumber = fileSerialNumber;
		this.noOfBeneficiary = noOfBeneficiary;
		this.dbtFlag = dbtFlag;
	}
	public XMLFileDetails() {
		super();
	}
	public String getOrgFileName() {
		return orgFileName;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	public String getAckStatus() {
		return ackStatus;
	}
	public void setAckStatus(String ackStatus) {
		this.ackStatus = ackStatus;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
	
}
