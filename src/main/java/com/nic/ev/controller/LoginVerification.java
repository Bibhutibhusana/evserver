package com.nic.ev.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.dto.RegistrationDetailsDTO;
import com.nic.ev.exception.BusinessException;
import com.nic.ev.exception.ResourceNotFoundException;
import com.nic.ev.repo.LoginVerificationRepo;

@RestController
@CrossOrigin
@RequestMapping("")
public class LoginVerification {
	@Autowired 
	LoginVerificationRepo loginVerificationRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/login") 
		private ResponseEntity<List<RegistrationDetailsDTO>> getUserDetails(@RequestBody Map<String, String> user) throws ResourceNotFoundException,BusinessException{
		try {
			List<RegistrationDetailsDTO> listuserlogin= loginVerificationRepo.findByUsernameAndPassword(user.get("username"),user.get("password")).stream().map(userlogin -> modelMapper.map(userlogin, RegistrationDetailsDTO.class))
					.collect(Collectors.toList());
			if (listuserlogin == null) {
				throw new ResourceNotFoundException("Bank Details not found");
			}
			return new ResponseEntity<List<RegistrationDetailsDTO>>(listuserlogin, HttpStatus.OK);
		}catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}
}
