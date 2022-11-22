package com.nic.ev.ifms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nic.ev.model.BaseEntity;

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
	private Date voucherDate;
	
	@Column(name="bill_status_check")
	private String billStatusString;
	
	@Column(name="utr_no")
	private String utrNo;

	@Column(name="utr_date")
	private Date utrDate;
	
	@Column(name="benf_payment_status")
	private String benfPaymentStatus;
	
	@Column(name="benf_bill_status")
	private String benefBillStatus;
	
	@Column(name="revert_status")
	private String revertStatus;
	
	@Column(name="revert_status_date")
	private Date revertStatusDate;
	
	@Column(name="ddo_check_status")
	private String ddoCheckStatus;
	
	@Column(name="ddo_check_status_date")
	private Date ddoCheckStatusDate;
	
	@Column(name="rto_code")
	private String rtoCode;

	public IFMSIntegrationTrack() {
	}
	
	public IFMSIntegrationTrack(Long id, String fileRefId, String billNo, String billRefNo, String resFileName,
			String applNo, String regnNo, String accNo, String name, String ifsc, String submitStatus, Date submitDate,
			String submitErr, String ackStatus, Date ackDate, String ackErr, String billStatus, String billStatusErr,
			String checkStatus, Date checkStatusErr, Date checkStatusDate, String voucherNo, Date voucherDate,
			String billStatusString, String utrNo, Date utrDate, String benfPaymentStatus, String benefBillStatus,
			String revertStatus, Date revertStatusDate, String ddoCheckStatus, Date ddoCheckStatusDate, String rtoCode) {
		super();
		this.id = id;
		this.fileRefId = fileRefId;
		this.billNo = billNo;
		this.billRefNo = billRefNo;
		this.resFileName = resFileName;
		this.applNo = applNo;
		this.regnNo = regnNo;
		this.accNo = accNo;
		this.name = name;
		this.ifsc = ifsc;
		this.submitStatus = submitStatus;
		this.submitDate = submitDate;
		this.submitErr = submitErr;
		this.ackStatus = ackStatus;
		this.ackDate = ackDate;
		this.ackErr = ackErr;
		this.billStatus = billStatus;
		this.billStatusErr = billStatusErr;
		this.checkStatus = checkStatus;
		this.checkStatusErr = checkStatusErr;
		this.checkStatusDate = checkStatusDate;
		this.voucherNo = voucherNo;
		this.voucherDate = voucherDate;
		this.billStatusString = billStatusString;
		this.utrNo = utrNo;
		this.utrDate = utrDate;
		this.benfPaymentStatus = benfPaymentStatus;
		this.benefBillStatus = benefBillStatus;
		this.revertStatus = revertStatus;
		this.revertStatusDate = revertStatusDate;
		this.ddoCheckStatus = ddoCheckStatus;
		this.ddoCheckStatusDate = ddoCheckStatusDate;
		this.rtoCode = rtoCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileRefId() {
		return fileRefId;
	}

	public void setFileRefId(String fileRefId) {
		this.fileRefId = fileRefId;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBillRefNo() {
		return billRefNo;
	}

	public void setBillRefNo(String billRefNo) {
		this.billRefNo = billRefNo;
	}

	public String getResFileName() {
		return resFileName;
	}

	public void setResFileName(String resFileName) {
		this.resFileName = resFileName;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getRegnNo() {
		return regnNo;
	}

	public void setRegnNo(String regnNo) {
		this.regnNo = regnNo;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getSubmitErr() {
		return submitErr;
	}

	public void setSubmitErr(String submitErr) {
		this.submitErr = submitErr;
	}

	public String getAckStatus() {
		return ackStatus;
	}

	public void setAckStatus(String ackStatus) {
		this.ackStatus = ackStatus;
	}

	public Date getAckDate() {
		return ackDate;
	}

	public void setAckDate(Date ackDate) {
		this.ackDate = ackDate;
	}

	public String getAckErr() {
		return ackErr;
	}

	public void setAckErr(String ackErr) {
		this.ackErr = ackErr;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public String getBillStatusErr() {
		return billStatusErr;
	}

	public void setBillStatusErr(String billStatusErr) {
		this.billStatusErr = billStatusErr;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Date getCheckStatusErr() {
		return checkStatusErr;
	}

	public void setCheckStatusErr(Date checkStatusErr) {
		this.checkStatusErr = checkStatusErr;
	}

	public Date getCheckStatusDate() {
		return checkStatusDate;
	}

	public void setCheckStatusDate(Date checkStatusDate) {
		this.checkStatusDate = checkStatusDate;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public Date getVoucherDate() {
		return voucherDate;
	}

	public void setVoucherDate(Date voucherDate) {
		this.voucherDate = voucherDate;
	}

	public String getBillStatusString() {
		return billStatusString;
	}

	public void setBillStatusString(String billStatusString) {
		this.billStatusString = billStatusString;
	}

	public String getUtrNo() {
		return utrNo;
	}

	public void setUtrNo(String utrNo) {
		this.utrNo = utrNo;
	}

	public Date getUtrDate() {
		return utrDate;
	}

	public void setUtrDate(Date utrDate) {
		this.utrDate = utrDate;
	}

	public String getBenfPaymentStatus() {
		return benfPaymentStatus;
	}

	public void setBenfPaymentStatus(String benfPaymentStatus) {
		this.benfPaymentStatus = benfPaymentStatus;
	}

	public String getBenefBillStatus() {
		return benefBillStatus;
	}

	public void setBenefBillStatus(String benefBillStatus) {
		this.benefBillStatus = benefBillStatus;
	}

	public String getRevertStatus() {
		return revertStatus;
	}

	public void setRevertStatus(String revertStatus) {
		this.revertStatus = revertStatus;
	}

	public String getDdoCheckStatus() {
		return ddoCheckStatus;
	}

	public void setDdoCheckStatus(String ddoCheckStatus) {
		this.ddoCheckStatus = ddoCheckStatus;
	}

	public Date getRevertStatusDate() {
		return revertStatusDate;
	}

	public void setRevertStatusDate(Date revertStatusDate) {
		this.revertStatusDate = revertStatusDate;
	}

	public Date getDdoCheckStatusDate() {
		return ddoCheckStatusDate;
	}

	public void setDdoCheckStatusDate(Date ddoCheckStatusDate) {
		this.ddoCheckStatusDate = ddoCheckStatusDate;
	}

	public String getRtoCode() {
		return rtoCode;
	}

	public void setRtoCode(String rtoCode) {
		this.rtoCode = rtoCode;
	}
}
