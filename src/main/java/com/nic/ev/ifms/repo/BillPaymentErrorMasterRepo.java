package com.nic.ev.ifms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nic.ev.ifms.model.webservice.BillPaymentErrorMaster;

public interface BillPaymentErrorMasterRepo extends JpaRepository<BillPaymentErrorMaster,Long>{

	BillPaymentErrorMaster findByErrorMsg(String statusDesc);

}
