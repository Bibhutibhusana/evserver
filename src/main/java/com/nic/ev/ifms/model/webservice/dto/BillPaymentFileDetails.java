package com.nic.ev.ifms.model.webservice.dto;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
@XmlAccessorType(XmlAccessType.FIELD)
@Data
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
	
}
