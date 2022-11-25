package com.nic.ev.ifms.model.webservice;

import java.io.Serializable;

import javax.persistence.Column;

import com.nic.ev.ifms.model.beneficiary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillPaymentAckResponse implements Serializable{
	private String errorCode;
	private Boolean status;
	private String rek;
	@Column(name="data")
	private String data;
	private String hmac;
	
}
