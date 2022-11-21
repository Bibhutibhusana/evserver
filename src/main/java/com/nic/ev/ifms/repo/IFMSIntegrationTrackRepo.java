package com.nic.ev.ifms.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.ev.ifms.model.IFMSIntegrationTrack;

@Repository
public interface IFMSIntegrationTrackRepo extends JpaRepository<IFMSIntegrationTrack,Long>{

	@Query(value="SELECT distinct file_name"
			+ "	FROM evschema.evt_ifms_integration_track where ack_status is null",nativeQuery=true)
	List<Map<String, String>> findByAckStatus(Object object);

	IFMSIntegrationTrack findByApplNo(String applNo);

	@Query(value="select it from IFMSIntegrationTrack it where it.accNo =:benfAccNo and it.ifsc=:ifsc and billRefNo =:billRefId")
	IFMSIntegrationTrack findByBenfIdAndBillRefId(String benfAccNo,String ifsc, String billRefId);

	@Query(value="select id, createdby, createddate, updatedby, updateddate, hibversionno, acc_no, ack_date, ack_err, ack_status, appl_no, bill_no, file_ref_id, ifsc, "
			+ "name, regn_no, submit_date, submit_err, submit_status, org_bill_ref_no, res_file_name, bill_status_check, voucher_date, voucher_no, check_status, "
			+ "check_status_date, utr_date, utr_no, check_status_err, bill_status, bill_status_err, benf_bill_status, benf_payment_status, ddo_check_status, "
			+ "revert_status, check_status_check, ddo_check_status_date, revert_status_date,'q1' as query from evschema.evt_ifms_transaction where ack_err != '000000' and revert_status is  null "
			+ "union "
			+ "select id, createdby, createddate, updatedby, updateddate, hibversionno, acc_no, ack_date, ack_err, ack_status, appl_no, bill_no, file_ref_id, ifsc, "
			+ "name, regn_no, submit_date, submit_err, submit_status, org_bill_ref_no, res_file_name, bill_status_check, voucher_date, voucher_no, check_status, "
			+ "check_status_date, utr_date, utr_no, check_status_err, bill_status, bill_status_err, benf_bill_status, benf_payment_status, ddo_check_status, "
			+ "revert_status, check_status_check, ddo_check_status_date, revert_status_date,'q2' as query from evschema.evt_ifms_transaction where bill_status_check in ('BL002','BL004','BN002') and revert_status is  null "
			+ "union "
			+ "select id, createdby, createddate, updatedby, updateddate, hibversionno, acc_no, ack_date, ack_err, ack_status, appl_no, bill_no, file_ref_id, ifsc, "
			+ "name, regn_no, submit_date, submit_err, submit_status, org_bill_ref_no, res_file_name, bill_status_check, voucher_date, voucher_no, check_status, "
			+ "check_status_date, utr_date, utr_no, check_status_err, bill_status, bill_status_err, benf_bill_status, benf_payment_status, ddo_check_status, "
			+ "revert_status, check_status_check, ddo_check_status_date, revert_status_date,'q3' as query from evschema.evt_ifms_transaction where benf_bill_status in ('BN002') and revert_status is  null "
			+ "union "
			+ "select id, createdby, createddate, updatedby, updateddate, hibversionno, acc_no, ack_date, ack_err, ack_status, appl_no, bill_no, file_ref_id, ifsc, "
			+ "name, regn_no, submit_date, submit_err, submit_status, org_bill_ref_no, res_file_name, bill_status_check, voucher_date, voucher_no, check_status, "
			+ "check_status_date, utr_date, utr_no, check_status_err, bill_status, bill_status_err, benf_bill_status, benf_payment_status, ddo_check_status, "
			+ "revert_status, check_status_check, ddo_check_status_date, revert_status_date,'q4' as query from evschema.evt_ifms_transaction where check_status_err is not null and revert_status is  null",nativeQuery=true)
	List<IFMSIntegrationTrack> getIFMSIntegrationTrackRevertPendingList();

}
