package com.nic.ev.repo;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.ev.model.BankDetails;
@Repository
public interface BankDetailsRepo extends JpaRepository<BankDetails, String>{

	Optional<BankDetails> findByRegnNo(String regn);


	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,d.v_class,st.sub_amnt,st.verify_dt,st.approve_dt "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ " inner join evschema.evt_status st on d.regn_no = st.regn_no "
			+ "where (d.op_dt between ?1 and ?2) and d.off_cd = ?3",nativeQuery = true)
	List<Map<String, Object>> getTotalApplied(Date fd, Date dt, String off_cd);


	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,d.v_class,st.sub_amnt,st.verify_dt,st.approve_dt "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ " inner join evschema.evt_status st on d.regn_no = st.regn_no "
			+ "where (d.op_dt between ?1 and ?2) and d.off_cd = ?3 and st.verification='y' ",nativeQuery = true)
	List<Map<String, Object>> getTotalVerified(Date fd, java.sql.Date dt, String off_cd);


	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,d.v_class,st.sub_amnt,st.verify_dt,st.approve_dt "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ " inner join evschema.evt_status st on d.regn_no = st.regn_no "
			+ "where (d.op_dt between ?1 and ?2) and d.off_cd = ?3 and st.verification is null",nativeQuery = true)
	List<Map<String, Object>> getPendingToVerify(Date fd, java.sql.Date dt, String off_cd);
	
	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,d.v_class,st.sub_amnt,st.verify_dt,st.approve_dt "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ " inner join evschema.evt_status st on d.regn_no = st.regn_no "
			+ "where (d.op_dt between ?1 and ?2) and d.off_cd = ?3 and st.approval is null",nativeQuery = true)
	List<Map<String, Object>> getPendingToApprove(Date fd, java.sql.Date dt, String off_cd);
	
	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,d.v_class,st.sub_amnt,st.verify_dt,st.approve_dt "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ " inner join evschema.evt_status st on d.regn_no = st.regn_no "
			+ "where (d.op_dt between ?1 and ?2) and d.off_cd = ?3 and st.approval='y' ",nativeQuery = true)
	List<Map<String, Object>> getTotalApproved(Date fd, java.sql.Date dt, String off_cd);
	
	
	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,d.v_class,st.sub_amnt,d.off_cd,,st.verify_dt,st.approve_dt,ofc.descr "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ " inner join evschema.evt_status st on d.regn_no = st.regn_no "
			+ " inner join evschema.tm_office ofc on d.off_cd=ofc.off_cd_int "
			+ "where (d.op_dt between ?1 and ?2) and (st.approval='y' and st.verification='y') and (st.disbursement is null) order by ofc.descr ",nativeQuery = true)
	List<Map<String, Object>> getPendingToDisbursement(Date fd, java.sql.Date dt);
	
	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,d.v_class,st.sub_amnt,,st.verify_dt,st.approve_dt,d.off_cd,ofc.descr "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ " inner join evschema.evt_status st on d.regn_no = st.regn_no "
			+ " inner join evschema.tm_office ofc on d.off_cd=ofc.off_cd_int "
			+ "where (d.op_dt between ?1 and ?2) and (st.approval='y' and st.verification='y') and (st.disbursement = 'y') order by ofc.descr ",nativeQuery = true)
	List<Map<String, Object>> getTotalDisbursement(Date fd, java.sql.Date dt);


	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,d.v_class,st.sub_amnt,d.off_cd,ofc.descr as descr "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ " inner join evschema.evt_status st on d.regn_no = st.regn_no "
			+ " inner join evschema.tm_office ofc on d.off_cd=ofc.off_cd_int "
			+ "where (d.op_dt between ?1 and ?2) order by descr ",nativeQuery = true)
	List<Map<String, Object>> getTotalAppliedReport(Date fd, Date dt);


	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,d.v_class,st.sub_amnt,d.off_cd,ofc.descr "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ " inner join evschema.evt_status st on d.regn_no = st.regn_no "
			+ " inner join evschema.tm_office ofc on d.off_cd=ofc.off_cd_int "
			+ "where (d.op_dt between ?1 and ?2) and st.verification='y' order by ofc.descr ",nativeQuery = true)
	List<Map<String, Object>> getTotalVerifiedReport(Date fd, java.sql.Date dt);


	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,d.v_class,st.sub_amnt,d.off_cd,ofc.descr "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ " inner join evschema.evt_status st on d.regn_no = st.regn_no "
			+ " inner join evschema.tm_office ofc on d.off_cd=ofc.off_cd_int "
			+ "where (d.op_dt between ?1 and ?2) and (st.verification is null and st.approval is null) order by ofc.descr ",nativeQuery = true)
	List<Map<String, Object>> getPendingToVerifyReport(Date fd, java.sql.Date dt);
	
	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,d.v_class,st.sub_amnt,d.off_cd,ofc.descr "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ " inner join evschema.evt_status st on d.regn_no = st.regn_no "
			+ " inner join evschema.tm_office ofc on d.off_cd=ofc.off_cd_int "
			+ "where (d.op_dt between ?1 and ?2) and (st.approval is null and st.verification='y') order by ofc.descr ",nativeQuery = true)
	List<Map<String, Object>> getPendingToApproveReport(Date fd, java.sql.Date dt);
	
	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,d.v_class,st.sub_amnt,d.off_cd,ofc.descr "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ " inner join evschema.evt_status st on d.regn_no = st.regn_no "
			+ " inner join evschema.tm_office ofc on d.off_cd=ofc.off_cd_int "
			+ "where (d.op_dt between ?1 and ?2) and st.approval='y' order by ofc.descr ",nativeQuery = true)
	List<Map<String, Object>> getTotalApprovedReport(Date fd, java.sql.Date dt);
	
	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,d.v_class,st.sub_amnt,d.off_cd,ofc.descr "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ " inner join evschema.evt_status st on d.regn_no = st.regn_no "
			+ " inner join evschema.tm_office ofc on d.off_cd=ofc.off_cd_int "
			+ "where (d.op_dt between ?1 and ?2) and (st.approval='y' and st.verification='y') and (st.disbursement is null) order by ofc.descr ",nativeQuery = true)
	List<Map<String, Object>> getPendingToDisbursementReport(Date fd, java.sql.Date dt);
	
	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,d.v_class,st.sub_amnt,d.off_cd,ofc.descr "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ " inner join evschema.evt_status st on d.regn_no = st.regn_no "
			+ " inner join evschema.tm_office ofc on d.off_cd=ofc.off_cd_int "
			+ "where (d.op_dt between ?1 and ?2) and (st.approval='y' and st.verification='y') and (st.disbursement = 'y') order by ofc.descr ",nativeQuery = true)
	List<Map<String, Object>> getTotalDisbursementReport(Date fd, java.sql.Date dt);

	@Query(value="select d.appl_no from evschema.evt_bank_details d where d.regn_no=?1 ",nativeQuery = true)
	List<Map<String, Object>> getToKnowApplNo(String regn);

	@Query(value="select bd.regn_no,st.appl_no,st.verification,st.approval from evschema.evt_bank_details bd "
			+" inner join evschema.evt_status st on bd.regn_no=st.regn_no where bd.regn_no=?1",nativeQuery=true)
	Map<String,Object> findByStatusAndBankId(String regn);
	
	@Query(value="select ebd.appl_no,ebd.name, acc_no as accountNo,ed.regn_no,ebd.acc_type,  ifsc_code as ifsc, ed.mob_no as mobileNo, ed.address,"
			+ "es.sub_amnt as amount, es.op_dt,ebd.appl_no as applNo from evschema.evt_bank_details ebd "
			+ "inner join evschema.evt_details ed on ed.regn_no=ebd.regn_no "
			+ "inner join evschema.evt_status es  on ebd.appl_no = es.appl_no  where ebd.op_dt =:toDay and es.approval='y' and not exists(select * from  evschema.evt_ifms_transaction eit where eit.regn_no=ebd.regn_no ) limit 10;",nativeQuery=true)
	public Collection<Map<String,Object>> findByOpDate(Date toDay);
}
