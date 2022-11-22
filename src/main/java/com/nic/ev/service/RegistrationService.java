package com.nic.ev.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nic.ev.model.BankDetails;
import com.nic.ev.model.UserStatus;
import com.nic.ev.repo.BankDetailsRepo;
import com.nic.ev.repo.UserStatusRepo;

@Service
public class RegistrationService {
	@Autowired
	UserStatusRepo userStatusRepo;
	@Autowired
	BankDetailsRepo bankDetailsRepo;
	@Transactional
	public boolean updateBankAndStatusDetails(UserStatus userStatus, BankDetails bankDetails) {
			bankDetailsRepo.save(bankDetails);
			userStatusRepo.save(userStatus);
			return true;
	}
}
