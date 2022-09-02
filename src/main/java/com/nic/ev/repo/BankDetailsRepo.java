package com.nic.ev.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nic.ev.model.BankDetails;

public interface BankDetailsRepo extends JpaRepository<BankDetails, String>{

	Optional<BankDetails> findByRegnNo(String regn);

}
