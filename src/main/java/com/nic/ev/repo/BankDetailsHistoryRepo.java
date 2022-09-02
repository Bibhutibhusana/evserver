package com.nic.ev.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nic.ev.model.BankDetailsHistory;

public interface BankDetailsHistoryRepo extends JpaRepository<BankDetailsHistory, String>{

}
