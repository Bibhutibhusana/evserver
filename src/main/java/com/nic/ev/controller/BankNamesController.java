package com.nic.ev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.exception.BusinessException;
import com.nic.ev.exception.ResourceNotFoundException;
import com.nic.ev.model.BankNames;
import com.nic.ev.repo.BankNamesRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class BankNamesController {
	@Autowired BankNamesRepo bankNamesRepo;
	
	@GetMapping(value="/banks",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<BankNames>> getAllBankNames() throws ResourceNotFoundException, BusinessException{
		try {
			List<BankNames> bankNamesDetails= bankNamesRepo.findAll();
			if (bankNamesDetails== null) {
				throw new ResourceNotFoundException("Bank Names not found");
			}
			return new ResponseEntity<List<BankNames>>(bankNamesDetails, HttpStatus.OK);
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}
	
//	@GetMapping("/otp")
//	private String getOtp(){
//		 String numbers = "0123456789";
//		
//		 Random rndm_method = new Random();
//		 
//		  
//	        char[] otp = new char[4];
//	  
//	        for (int i = 0; i < 4; i++)
//	        {
//	            otp[i] =
//	             numbers.charAt(rndm_method.nextInt(numbers.length()));
//	        }
//		
//		return String.valueOf(otp);
//	}
	

}
