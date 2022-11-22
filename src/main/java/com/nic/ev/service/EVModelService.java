package com.nic.ev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nic.ev.model.EvmValidation;
import com.nic.ev.repo.EvValidationRepo;

@Service
public class EVModelService {
	@Autowired 
	EvValidationRepo evValidRepo;
	
	@Transactional
	public boolean SaveEVModel(EvmValidation EvmModel) {
		evValidRepo.save(EvmModel);
			return true;
	}
}
