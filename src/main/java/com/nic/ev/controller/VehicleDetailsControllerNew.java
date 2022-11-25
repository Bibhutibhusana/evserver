package com.nic.ev.controller;
import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.dto.VahanDetailsDTO;
import com.nic.ev.exception.BusinessException;
import com.nic.ev.exception.ResourceNotFoundException;
import com.nic.ev.livedbmodel.VehicleDetailsModel;
import com.nic.ev.repo.VehicleDetailsRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class VehicleDetailsControllerNew {

	@Autowired VehicleDetailsRepo vdRepo;
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/getVehicleDetailsToDelete")
	private ResponseEntity<VahanDetailsDTO> getVehicleDetails(@RequestBody String regn) throws ResourceNotFoundException, BusinessException {
		try{
		VehicleDetailsModel vdmodel=vdRepo.findById(regn).orElseThrow(() -> new ResourceNotFoundException("Vehicle Details not found for this id:" + regn));
		VahanDetailsDTO VehicleDetails=modelMapper.map(vdmodel, VahanDetailsDTO.class);
		return ResponseEntity.ok().body(VehicleDetails);
	} catch (BusinessException e) {
		throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
	}
	}

	@DeleteMapping("/deleteVehicleDetailsByRegn/{regn}")
	ResponseEntity<Map<String, Boolean>> deleteByRegnNo( @PathVariable(value = "regn") String regn)  throws ResourceNotFoundException, BusinessException {
		try {
			vdRepo.findById(regn)
			.orElseThrow(() -> new ResourceNotFoundException("Vehicle Details not found for this id:" + regn));

		vdRepo.deleteById(regn);

		 Map<String, Boolean> rs = new HashMap<>();

		 rs.put("deleted",true);

		 return ResponseEntity.ok().body(rs);      
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}
}