package com.nic.ev.ifms.model.webservice;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nic.ev.ifms.model.webservice.dto.BillStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonCreator
	public BillDetail(@JsonProperty("billStatus") BillStatus billStatus, @JsonProperty("orgFileSlNo") String orgFileSlNo) {
		this.billStatus = billStatus;
		this.orgFileSlNo = orgFileSlNo;
		
	}

	@JsonInclude(Include.NON_NULL)
	private String billNumber; 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	private Date billDate;
	@JsonInclude(Include.NON_NULL)
	private int billTypeId;
	@JsonInclude(Include.NON_NULL)
	private String ddoCode;
	@JsonInclude(Include.NON_NULL)
	private String ddoLoginId;
	@JsonInclude(Include.NON_NULL)
	private BigDecimal grossAmount;
	@JsonInclude(Include.NON_NULL)
	private BigDecimal netAmount;
	@JsonInclude(Include.NON_NULL)
	private int noOfBenf;
	@JsonInclude(Include.NON_NULL)
	private String sancAuthUserLoginId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	private Date paymentDate;
	
	//////////////////// For Ack Status check /////////////////////////////
	@JsonInclude(Include.NON_NULL)
	private BillStatus billStatus;
	@JsonInclude(Include.NON_NULL)
	private String orgFileSlNo;
	
	@JsonInclude(Include.NON_NULL)
	private String billRefNo;
	/////////////  For Response Status checks /////////////////////
	@JsonInclude(Include.NON_NULL)
	private String orgDeptRefId;
	
	@JsonInclude(Include.NON_NULL)
	private String tokenNumber;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	private Date tokenDate;
	
	@JsonInclude(Include.NON_NULL)
	private String orgBillRefNo;
	

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	private Date voucherDate;
	
	@JsonInclude(Include.NON_NULL)
	private String voucherNo;
}
