package com.nic.ev.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.livedbmodel.VehicleDetailsModel;
import com.nic.ev.model.BankDetails;
import com.nic.ev.repo.BankDetailsRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class BankDetailsInsertController {
	
	@Autowired BankDetailsRepo bankDetailsRepo;
	
	@PostMapping("/insertBankDetails")
	private BankDetails insertBankDetails(@Valid @RequestBody BankDetails bankDetails) {
		
		return bankDetailsRepo.save(bankDetails);
	}
	
	@GetMapping("/showImage")
	private List<BankDetails> getImage(){
		
		return bankDetailsRepo.findAll();
		
	}
	
	@GetMapping("/applNoGeneration/{regn}")
	private Map<String, String> getApplNo(@PathVariable(value="regn") String regn){
//		String applNo="";

		Map<String, String> applNo = new HashMap<String, String>();
		String temp="";
		Date d = new Date();
		int year = d.getYear()+1900;
		String lastFourDigits = regn.substring(regn.length() - 4);
		temp = "OD"+year+"EV"+System.currentTimeMillis();
		applNo.put("applNo", temp);
		
		
//		System.out.println(applNo);
		
		return applNo;
	}
	
	
	@GetMapping("/checkExistBankDetails/{id}")
	private Optional<BankDetails> checkIfExists(@PathVariable(value="id") String regn) {
		
		return bankDetailsRepo.findById(regn);
	}
	
	@DeleteMapping("/deleteBankDetailsByRegn/{regn}")
	 Map<String, Boolean> deleteByRegnNo( @PathVariable(value = "regn") String regn) {
		bankDetailsRepo.deleteById(regn);
		
		 Map<String, Boolean> rs = new HashMap<>();
		 
		 rs.put("deleted",true);
		 
		 return rs;
		
	}
	
 
}
