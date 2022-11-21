package com.nic.ev.ifms.repo;

import java.sql.Date;
import java.util.Collection;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nic.ev.ifms.model.XMLBankDetailsModel;

public interface XMLBankDetailsRepo extends JpaRepository<XMLBankDetailsModel, String>{

	
	Collection<? extends XMLBankDetailsModel> findByOpDt(Date toDay);
	
	@Query(value="select acc_no, ebd.appl_no, ifsc_code, name, es.regn_no, ebd.bank_name, ebd.branch_name, es.op_dt from evschema.evt_bank_details ebd  join evschema.evt_status es on ebd.appl_no = es.appl_no  where es.disbursement='y' limit 4;",nativeQuery=true)
	public Collection<? extends XMLBankDetailsModel> find20DummyRecordsFromDisbursedList();
	
	@Query(value="select ebd.appl_no,ebd.name, acc_no as accountNo,ed.regn_no,ebd.acc_type,  ifsc_code as ifsc, ed.mob_no as mobileNo, ed.address,"
			+ "es.sub_amnt as amount, es.op_dt,ebd.appl_no ,ed.off_cd from evschema.evt_bank_details ebd "
			+ "inner join evschema.evt_details ed on ed.regn_no=ebd.regn_no "
			+ "inner join evschema.evt_status es  on ebd.appl_no = es.appl_no  where ebd.op_dt =:toDay and es.approval='y' and not exists(select * from  evschema.evt_ifms_transaction eit where eit.regn_no=ebd.regn_no ) limit 10;",nativeQuery=true)
	public Collection<Map<String,Object>> findByOpDate(Date toDay);

	
}
 