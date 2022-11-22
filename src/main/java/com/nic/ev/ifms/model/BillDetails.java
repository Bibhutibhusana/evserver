package com.nic.ev.ifms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nic.ev.ifms.model.dto.BillStatus;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonCreator
	public BillDetails(@JsonProperty("billStatus") BillStatus billStatus, @JsonProperty("orgFileSlNo") String orgFileSlNo) {
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
	

	@JsonInclude(Include.NON_NULL)
	private BillStatus billStatus;
	@JsonInclude(Include.NON_NULL)
	private String orgFileSlNo;
	
	@JsonInclude(Include.NON_NULL)
	private String billRefNo;

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
