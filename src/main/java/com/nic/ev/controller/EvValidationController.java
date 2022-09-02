package com.nic.ev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.model.EvmValidation;
import com.nic.ev.repo.EvValidationRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class EvValidationController {
	@Autowired EvValidationRepo evValidRepo;
	
	@GetMapping(value="/evValidate",produces = MediaType.APPLICATION_JSON_VALUE)
	private List<EvmValidation> getAllEvValidate(){
		return evValidRepo.findAll();
	}
	@GetMapping(value="/evValidate/{model}",produces = MediaType.APPLICATION_JSON_VALUE)
	private Optional<EvmValidation> getEvValidateById(@PathVariable(value="model") String model){
		return evValidRepo.findByModelName(model);
	}

}
