package com.nic.ev.repo;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nic.ev.model.UserStatusHistory;

public interface UserStatusHistoryRepo extends JpaRepository<UserStatusHistory, String>{

	@Query(value="select distinct s.appl_no,s.regn_no,d.owner_name,b.acc_no,s.verification,s.approval,s.payment,s.user_registered,s.reason from evschema.evh_status s "
			+ "inner join evschema.evh_details d on s.regn_no = d.regn_no "
			+ "inner join evschema.evh_bank_details b on s.regn_no = b.regn_no "
			+ "where s.regn_no=?1 ",nativeQuery = true)
	Map<String, Object> getApplicationStatus(String regn_no);
}
