package com.nic.ev.ifms.repo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nic.ev.ifms.model.IFMSIntegrationTrack;

public interface IFMSIntegrationTrackRepo extends JpaRepository<IFMSIntegrationTrack, Long>{

	//get pending treasury failure beneficiary list in rto login 
	@Query(value = "select * from evschema.evt_ifms_transaction where ack_err != '000000' and revert_status is  null "
			+ "union "
			+ "select * from evschema.evt_ifms_transaction where bill_status_check in ('B1','B4','A1') and revert_status is  null "
			+ "union "
			+ "select * from evschema.evt_ifms_transaction where benf_bill_status in ('A1') and revert_status is  null "
			+ "union "
			+ "select * from evschema.evt_ifms_transaction where check_status_err is not null and revert_status is  null", nativeQuery = true)
	List<IFMSIntegrationTrack> getAllPendingRevertCases();

	Optional<IFMSIntegrationTrack> findById(IFMSIntegrationTrack ifmsIntegrationTrack);

	@Query(value = "select eit.acc_no, eit.appl_no, eit.bill_no, eit.bill_status, eit.benf_bill_status, "
			+ "eit.ifsc, eit.name, eit.regn_no, eit.benf_payment_status, eit.revert_status, eit.submit_date, "
			+ "eit.check_status_date, eit.check_status, eit.ddo_check_status, eit.ddo_check_status_date, "
			+ "es.sub_amnt, es.approval, es.verification, es.rtgs_no,eiem.ev_err "
			+ "from evschema.evt_ifms_transaction eit "
			+ "inner join evschema.evt_status es on eit.regn_no=es.regn_no "
			+ "inner join evschema.evt_revert_status ers on es.regn_no=ers.regn_no "
			+ "inner join evschema.evt_ifms_error_master eiem on eiem.err_cd = eit.benf_bill_status "
			+ "where es.approval ='y' and es.verification ='y' and eit.revert_status ='y' and eit.ddo_check_status isnull"
			, nativeQuery=true)
	List<Map<String, Object>> getRectifiedDetails();

	Optional<IFMSIntegrationTrack> findByRegnNo(@Valid String regn_no);
	
}
