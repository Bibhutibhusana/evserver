package com.nic.ev.ifms.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class BillPaymentFileDetails {

	private String fileType;
	private String intgCode;
	private String serviceCode;
	private String fileRefId;
	private String fileSlNo;
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fileDate;
	@JsonInclude(Include.NON_NULL)
	private String fileName;
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
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
	public String getFileRefId() {
		return fileRefId;
	}
	public void setFileRefId(String fileRefId) {
		this.fileRefId = fileRefId;
	}
	public String getFileSlNo() {
		return fileSlNo;
	}
	public void setFileSlNo(String fileSlNo) {
		this.fileSlNo = fileSlNo;
	}
	public Date getFileDate() {
		return fileDate;
	}
	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
