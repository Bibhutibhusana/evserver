package com.nic.ev.dto;

	import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown=true)

@Data
public class BankInfoDTO {

		private String regnNo;
		private String name;
		private String bankName;
		private String branchName;
		private String ifscCode;
		private String accNo;
		private Date op_dt;
		private byte[] passbookImg;
		private String applNo;

}
