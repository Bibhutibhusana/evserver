package com.nic.ev.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class IfscDetailsDTO {
	

	private Long id = 0l;
	private String bank;
	private String ifsc;
	private String branch;
	private String address;
	private String city1;
	private String city2;
	private String state;
	private String stateCd;
	private String phone;
}