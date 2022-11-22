package com.nic.ev.repo;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nic.ev.livedbmodel.VehicleDetailsModel;

public interface VehicleDetailsRepo extends JpaRepository<VehicleDetailsModel, String>{
	
	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,bd.ifsc_code,bd.passbook_img,st.verification,d.fuel,d.v_class,d.purchase_dt,bd.branch_name,mob_no, "
			+ " st.approval,st.op_dt,st.verify_user_id,st.approve_user_id "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ "inner join evschema.evt_status st on d.regn_no=st.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ "where (st.verification is null or st.approval='rev') and d.off_cd = ?1",nativeQuery = true)
	List<Map<String, Object>> getToVerify(String off_cd);
	
	
	@Query(value="select distinct d.regn_no,owner_name,bd.appl_no,bd.acc_no,bd.ifsc_code,bd.passbook_img,st.approval,d.sale_amnt,d.v_class,d.fuel,d.purchase_dt,bd.branch_name,mob_no, "
			+ " st.verification,st.op_dt,st.verify_user_id,st.approve_user_id "
			+ " from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ "inner join evschema.evt_status st on d.regn_no=st.regn_no "
			+ " inner join evschema.user_login ul on d.off_cd=ul.off_cd "
			+ "where (st.approval is null or st.approval='rev') and st.verification='y' and d.off_cd = ?1",nativeQuery = true)
	List<Map<String, Object>> getToApprove(String off_cd);
	
	@Query(value="select d.regn_no,owner_name,bd.appl_no,bd.acc_no,bd.ifsc_code,bd.bank_name,st.sub_amnt,mob_no from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ "inner join evschema.evt_status st on d.regn_no=st.regn_no "
			+ "where st.approval = 'y' and st.verification='y' and st.payment is null and download_status is null "
			+ " and approve_dt = ?1 and d.off_cd = ?2",nativeQuery = true)
	List<Map<String, Object>> getFinalApproveList(Date dt,String off_cd);
	
	@Query(value="select distinct d.regn_no,d.owner_name, st.sub_amnt,st.approve_dt,st.appl_no,st.cheque_no "
			+ "from evschema.evt_details d "
			+ "inner join evschema.evt_status st on d.regn_no=st.regn_no  "
			+ "where st.appl_no=?1",nativeQuery = true)
	Map<String, Object> getSanctionOrder(String appl_no);
	
	@Query(value="select d.regn_no,owner_name,bd.appl_no,bd.acc_no,bd.ifsc_code,bd.bank_name,mob_no from evschema.evt_details d "
			+ "inner join evschema.evt_bank_details bd on d.regn_no=bd.regn_no "
			+ "inner join evschema.evt_status st on d.regn_no=st.regn_no "
			+ "where st.approval = 'y' and st.verification='y' and st.payment is null and download_status='y' "
			+ " and approve_dt = ?1 and d.off_cd = ?2 and disbursement is null",nativeQuery = true)
	List<Map<String, Object>> getFinalDisbursementList(Date dt,String off_cd);
	
	

}
