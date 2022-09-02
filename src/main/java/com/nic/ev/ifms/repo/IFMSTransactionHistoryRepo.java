package com.nic.ev.ifms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nic.ev.ifms.model.IFMSTransactionHistory;

@Repository									
public interface IFMSTransactionHistoryRepo extends JpaRepository<IFMSTransactionHistory,Long> {

}
