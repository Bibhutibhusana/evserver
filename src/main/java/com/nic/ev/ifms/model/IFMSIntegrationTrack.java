package com.nic.ev.ifms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nic.ev.model.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="evt_ifms_transaction")
public class IFMSIntegrationTrack extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="file_ref_id")
	private String fileRefId;
	
	@Column(name="bill_no")
	private String billNo;
	
	@Column(name="org_bill_ref_no")
	private String billRefNo;
	
	@Column(name="res_file_name")
	private String resFileName;
	
	@Column(name="appl_no",unique=true,nullable=false)
	private String applNo;
	 
	@Column(name="regn_no",unique= true)
	private String regnNo;

	@Column(name="acc_no")
	private String accNo;
	 
	@Column(name="name")
	private String name;
	
	@Column(name="ifsc")
	private String ifsc;
	
	@Column(name="submit_status")
	private String submitStatus;
	
	@Column(name="submit_date")
	private Date submitDate;
	
	@Column(name="submit_err")
	private String submitErr;
	
	@Column(name="ack_status")
	private String ackStatus;
	
	@Column(name="ack_date")
	private Date ackDate;
	
	@Column(name="ack_err")
	private String ackErr;
	
	@Column(name="bill_status") 
	private String billStatus;
	
	
	@Column(name="bill_status_err")
	private String billStatusErr;
	
	@Column(name="check_status")
	private String checkStatus;
	
	@Column(name="check_status_err")
	private Date checkStatusErr;
	
	@Column(name="check_status_date")
	private Date checkStatusDate;
	
	@Column(name="voucher_no")
	private String voucherNo;
	
	@Column(name="voucher_date")
	private java.sql.Date voucherDate;
	
	@Column(name="bill_status_check")
	private String billStatusString;
	
	@Column(name="utr_no")
	private String utrNo;
	
	@Column(name="utr_date")
	private java.sql.Date utrDate;
	
	@Column(name="benf_payment_status")
	private String benfPaymentStatus;
	
	@Column(name="benf_bill_status")
	private String benefBillStatus;
	
	@Column(name="revert_status_date")
	private Date revertStatusDate;
	
	@Column(name="revert_status")
	private String revertStatus;
	
	@Column(name="ddo_check_status_date")
	private Date ddoCheckStatusDate;
	
	@Column(name="ddo_check_status")
	private String ddoCheckStatus;
	
	@Column(name="off_cd")
	private String offCd;
	
	
	private String query;
	

	
}
