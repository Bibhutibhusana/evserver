package com.nic.ev.ifms.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.nic.ev.ifms.model.dto.Code;

import lombok.Data;

@Data
@XmlRootElement(name = "beneficiary")
public class Beneficiary {

	@JsonInclude(Include.NON_NULL)
	private String benfId;
	@JsonInclude(Include.NON_NULL)
	private String name;
	@JsonInclude(Include.NON_NULL)
	private String accountNo;
	@JsonInclude(Include.NON_NULL)
	private String ifsc;
	@JsonInclude(Include.NON_NULL)
	private String mobileNo;
	@JsonInclude(Include.NON_NULL)
	private String address;
	@JsonInclude(Include.NON_NULL)
	private String accountType;
	@JsonInclude(Include.NON_NULL)
	private BigDecimal amount;
	
	@JsonInclude(Include.NON_NULL)
	private List<Code> codes;
	
	@JacksonXmlProperty(isAttribute = false)
	@JsonIgnore
	private String applNo;
	
	@JsonInclude(Include.NON_NULL)
	private String benefAcctNumber;
	
	@JsonInclude(Include.NON_NULL)
	private String benefIFSC;
	
	@JsonInclude(Include.NON_NULL)
	private String utrNo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	private Date utrDate;
	
	@JsonInclude(Include.NON_NULL)
	private String payStatus;
	
	@JsonInclude(Include.NON_NULL)
	private String statusDesc;
}
