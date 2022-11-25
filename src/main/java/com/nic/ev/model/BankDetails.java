package com.nic.ev.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.nic.ev.ifms.model.webservice.ByTransfer;
import com.nic.ev.ifms.utils.StringPrefixedSequenceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="evt_bank_details")
public class BankDetails {
	
	@Id
	private String regnNo;
	
	private String name;
	
	private String bankName;
	
	private String branchName;
	
	private String ifscCode;
	
	private String accNo;
	
	private Date op_dt;
	
	private byte[] passbookImg;

	private String applNo;
	
	private String accType;
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "benf_id_sequence")
//    @GenericGenerator(
//            name = "benf_id_sequence", 
//            strategy = "com.nic.ev.ifms.utils.StringPrefixedSequenceIdGenerator", 
//            parameters = { 
//                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
//                    @Parameter(name = StringPrefixedSequenceIdGenerator.CODE_NUMBER_SEPARATOR_PARAMETER, value = "_"), 
//                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
    
	private String benfId;
	

}
