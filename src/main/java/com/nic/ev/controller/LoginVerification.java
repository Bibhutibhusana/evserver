package com.nic.ev.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.model.EvmValidation;
import com.nic.ev.model.UserLogin;
import com.nic.ev.repo.LoginVerificationRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class LoginVerification {
	@Autowired LoginVerificationRepo loginVerificationRepo;
	
	@PostMapping("/login") 
		private List<UserLogin> getUserDetails(@RequestBody Map<String, String> user){
			return loginVerificationRepo.findByUsernameAndPassword(user.get("username"),user.get("password"));
	}

}
