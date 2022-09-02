package com.nic.ev.repo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nic.ev.model.UserStatus;

public interface UserStatusRepo extends JpaRepository<UserStatus, String>{
	
	Optional<UserStatus> findByRegnNo(String regn);
	
	@Query(value="select distinct s.appl_no,s.regn_no,d.owner_name,b.acc_no,s.verification,s.approval,s.payment,s.user_registered,s.reason from evschema.evt_status s "
			+ "inner join evschema.evt_details d on s.regn_no = d.regn_no "
			+ "inner join evschema.evt_bank_details b on s.regn_no = b.regn_no "
			+ "where s.regn_no=?1 ",nativeQuery = true)
	Map<String, Object> getApplicationStatus(String regn_no);

	Optional<UserStatus> findByApplNo(String applNo);
	
	@Query(value="UPDATE evschema.evt_status SET  disbursement='y' WHERE regn_no= ?1", nativeQuery = true)
	Map<String, Boolean> updateDisbursementStatus(String regn);

	
	@Query(value="select s.sub_amnt,s.appl_no,s.regn_no,mob_no from evschema.evt_status s "
			+ "inner join evschema.evt_details d on s.regn_no=d.regn_no where s.regn_no=?1",nativeQuery = true)
	List<Map<String, Object>> getDataForDisburseMsg(String regn);

}
