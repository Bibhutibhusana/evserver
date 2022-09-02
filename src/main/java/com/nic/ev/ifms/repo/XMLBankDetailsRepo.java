package com.nic.ev.ifms.repo;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nic.ev.ifms.model.XMLBankDetailsModel;

public interface XMLBankDetailsRepo extends JpaRepository<XMLBankDetailsModel, String>{

	
	Collection<? extends XMLBankDetailsModel> findByOpDt(Date toDay);
	
	@Query(value="select acc_no, ebd.appl_no, ifsc_code, name, es.regn_no, ebd.bank_name, ebd.branch_name, es.op_dt from evschema.evt_bank_details ebd  join evschema.evt_status es on ebd.appl_no = es.appl_no  where es.disbursement='y' limit 4;",nativeQuery=true)
	public Collection<? extends XMLBankDetailsModel> find20DummyRecordsFromDisbursedList();

	
}
