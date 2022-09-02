package com.nic.ev.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nic.ev.model.BankNames;

@Repository
public interface BankNamesRepo extends JpaRepository<BankNames, Long>{

}
