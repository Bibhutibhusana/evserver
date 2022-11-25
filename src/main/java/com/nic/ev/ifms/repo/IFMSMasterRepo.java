package com.nic.ev.ifms.repo;



import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nic.ev.ifms.model.IFMSMaster;

public interface IFMSMasterRepo extends JpaRepository<IFMSMaster,Long> {

	IFMSMaster findByIfsc(String ifscCode);

	@Query(value="select distinct bank from evschema.evt_ifms_master ;",nativeQuery=true)
	List<Map<String, String>> findAllBankNames();

	List<IFMSMaster> findByBank(String bankName);

}