package com.nic.ev.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.dto.VahanDetailsDTO;
import com.nic.ev.exception.BusinessException;
import com.nic.ev.exception.ResourceNotFoundException;
import com.nic.ev.livedbmodel.VehicleDetailsModel;
import com.nic.ev.repo.VehicleDetailsRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class VehicleDetailsInsertController {
	
	@Autowired VehicleDetailsRepo vehicleDetailsRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/checkExist/{id}")
	private ResponseEntity<VahanDetailsDTO> checkIfExists(@PathVariable(value="id") String regn) throws ResourceNotFoundException, BusinessException  {
		try{
		VehicleDetailsModel vdm=vehicleDetailsRepo.findById(regn).orElseThrow(() -> new ResourceNotFoundException("Vehicle Details not found for this id:" + regn));
		VahanDetailsDTO VehicleDetails=modelMapper.map(vdm, VahanDetailsDTO.class);
		return ResponseEntity.ok().body(VehicleDetails);
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

	@PostMapping("/insertVehicleDetails")
	private ResponseEntity<VahanDetailsDTO> insertVehicleDetails(@Valid @RequestBody VahanDetailsDTO vehicleDetailsdto)  throws BusinessException  {
		// convert DTO to entity
		VehicleDetailsModel vehicleDetails=modelMapper.map(vehicleDetailsdto, VehicleDetailsModel.class);
		if (vehicleDetails.getRegnNo().isEmpty() || vehicleDetails.getRegnNo().length() == 0) {
			throw new BusinessException("Please provide Registration No.It is Blank");
		}
		try {
		vehicleDetailsRepo.save(vehicleDetails);
		// convert entity to DTO
		VahanDetailsDTO VahanDetailsResponse = modelMapper.map(vehicleDetails, VahanDetailsDTO.class);
		return new ResponseEntity<VahanDetailsDTO>(VahanDetailsResponse, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("Given Vehicle Details are null" + e.getMessage());
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}
}
