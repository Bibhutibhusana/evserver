package com.nic.ev.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.model.BankNames;
import com.nic.ev.repo.BankNamesRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class BankNamesController {
	@Autowired BankNamesRepo bankNamesRepo;
	
	@GetMapping(value="/banks",produces = MediaType.APPLICATION_JSON_VALUE)
	private List<BankNames> getAllBankNames(){
		return bankNamesRepo.findAll();
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
