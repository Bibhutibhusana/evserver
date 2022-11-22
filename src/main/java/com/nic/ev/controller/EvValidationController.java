package com.nic.ev.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nic.dto.OemDetailsDTO;
import com.nic.ev.exception.BusinessException;
import com.nic.ev.exception.ResourceNotFoundException;
import com.nic.ev.model.EvmValidation;
import com.nic.ev.repo.EvValidationRepo;
import com.nic.ev.service.EVModelService;

@RestController
@CrossOrigin
@RequestMapping("/")
public class EvValidationController {
	@Autowired 
	EvValidationRepo evValidRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	EVModelService EvmService;
	
	@GetMapping(value="/evValidate",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<OemDetailsDTO>> getAllEvValidate() throws ResourceNotFoundException, BusinessException{
		try {
			List<OemDetailsDTO> listEVMValidation= evValidRepo.findAll().stream().map(evmvalidate -> modelMapper.map(evmvalidate, OemDetailsDTO.class))
					.collect(Collectors.toList());
			if (listEVMValidation== null) {
				throw new ResourceNotFoundException("EVM Validation Details not found");
			}
			return new ResponseEntity<List<OemDetailsDTO>>(listEVMValidation, HttpStatus.OK);
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}
	@GetMapping(value="/evValidate/{model}",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<OemDetailsDTO> getEvValidateById(@PathVariable(value="model") String model) throws ResourceNotFoundException, BusinessException{
		try {
			EvmValidation EVMValidation = evValidRepo.findByModelName(model).orElseThrow(() -> new ResourceNotFoundException("EVM Validation Details not found for this Model:" + model));
			OemDetailsDTO oemDetails=modelMapper.map(EVMValidation, OemDetailsDTO.class);
			return ResponseEntity.ok().body(oemDetails);
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
		//return evValidRepo.findByModelName(model);
	}
	@PostMapping("/insertEVModelDetails")
	private Map<String, Object> insertEVModelDetails(@Valid @RequestBody Map<String, Object> obj)  throws BusinessException{
		try
		{
		ObjectMapper mapper = new ObjectMapper();
		EvmValidation u = mapper.convertValue(obj.get("EVModelAddition"), EvmValidation.class);
		EvmService.SaveEVModel(u);
		return obj;
		}
		catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

}
