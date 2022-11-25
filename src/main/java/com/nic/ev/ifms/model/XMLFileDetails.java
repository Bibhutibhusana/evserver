package com.nic.ev.ifms.model;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

	
	
	
}
