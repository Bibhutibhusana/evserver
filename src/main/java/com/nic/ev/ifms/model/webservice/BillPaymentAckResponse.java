package com.nic.ev.ifms.model.webservice;

import javax.persistence.Column;

import lombok.Data;

@Data
public class BillPaymentAckResponse {
	private String errorCode;
	private Boolean status;
	private String rek;
	@Column(name="data")
	private String data;
	private String hmac;
	

}
