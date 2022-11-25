package com.nic.ev.ifms.repo;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nic.ev.ifms.model.IFMSErrorModel;

public interface IFMSErrorResponseRepo extends JpaRepository<IFMSErrorModel, Long>{

	@Query(value = "select * from evschema.evt_ifms_error_master where err_cd=?1", nativeQuery=true)
	IFMSErrorModel findByErrCode(@Valid String err_cd);

}