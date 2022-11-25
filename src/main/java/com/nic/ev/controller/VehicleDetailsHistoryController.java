package com.nic.ev.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.dto.VahanHistoryDetailsDTO;
import com.nic.ev.exception.BusinessException;
import com.nic.ev.model.VehicleDetailsHistoryModel;
import com.nic.ev.repo.VehicleDetailsHistoryRepo;

@RestController
@CrossOrigin
@RequestMapping("/")

public class VehicleDetailsHistoryController {

	@Autowired VehicleDetailsHistoryRepo vehicleHistoyRepo;
	@Autowired
	private ModelMapper modelMapper;
	@PostMapping("/insertToVehicleDetailsHistory")
	private ResponseEntity<VahanHistoryDetailsDTO> insertUserStatus(@Valid @RequestBody VahanHistoryDetailsDTO vdhmDTO)  throws BusinessException {
		// convert DTO to entity
		VehicleDetailsHistoryModel vdhm=modelMapper.map(vdhmDTO, VehicleDetailsHistoryModel.class);
		if (vdhm.getRegnNo().isEmpty() || vdhm.getRegnNo().length() == 0) {
			throw new BusinessException("Please provide Registration No.It is Blank");
		}
		try {
		vehicleHistoyRepo.save(vdhm);	
		// convert entity to DTO
		VahanHistoryDetailsDTO VahanHistoryDetails = modelMapper.map(vdhm, VahanHistoryDetailsDTO.class);
		return new ResponseEntity<VahanHistoryDetailsDTO>(VahanHistoryDetails, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("Given Vehicle History Details are null" + e.getMessage());
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

}