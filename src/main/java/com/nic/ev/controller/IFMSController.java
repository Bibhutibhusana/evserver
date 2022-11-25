package com.nic.ev.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.ifms.model.IFMSMaster;
import com.nic.ev.ifms.repo.IFMSMasterRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class IFMSController {

	@Autowired
	IFMSMasterRepo ifscRepo;
	
	@GetMapping("bankNames")
	public List<Map<String,String>> findAllBankNames(){
		return ifscRepo.findAllBankNames();
	}
	
	@PostMapping("/bankByName")
	public List<IFMSMaster> getAllBankDetailByBankName(@RequestBody Map<String,String> bankName){
		return ifscRepo.findByBank(bankName.get("bank"));
	}
}
