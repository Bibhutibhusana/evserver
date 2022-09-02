package com.nic.ev.ifms.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "beneficiary")
@XmlType(propOrder = {
    "benfAccountNo",
    "benfIfsc",
    "ackErrorCode"
})
public class beneficiary {
	public String benfAccountNo;
	public String benfIfsc;
	public String ackErrorCode;
	
//	public String getBenfAccountNo() {
//		return benfAccountNo;
//	}
//	public void setBenfAccountNo(String benfAccountNo) {
//		this.benfAccountNo = benfAccountNo;
//	}
//	public String getBenfIfsc() {
//		return benfIfsc;
//	}
//	public void setBenfIfsc(String benfIfsc) {
//		this.benfIfsc = benfIfsc;
//	}
//	public String getAckErrorCode() {
//		return ackErrorCode;
//	}
//	public void setAckErrorCode(String ackErrorCode) {
//		this.ackErrorCode = ackErrorCode;
//	}
}
