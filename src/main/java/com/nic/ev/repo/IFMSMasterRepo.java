package com.nic.ev.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nic.ev.model.IFMS_master;
public interface IFMSMasterRepo extends JpaRepository<IFMS_master,Long> {

	IFMS_master findByIfsc(String ifscCode);

}
