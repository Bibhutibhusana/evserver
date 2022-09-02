package com.nic.ev.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.livedbmodel.VehicleDetailsModel;
import com.nic.ev.repo.VehicleDetailsRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class VehicleDetailsInsertController {
	
	@Autowired VehicleDetailsRepo vehicleDetailsRepo;
	
	
	@GetMapping("/checkExist/{id}")
	private Optional<VehicleDetailsModel> checkIfExists(@PathVariable(value="id") String regn) {
		
		return vehicleDetailsRepo.findById(regn);
	}

	@PostMapping("/insertVehicleDetails")
	private VehicleDetailsModel insertVehicleDetails(@Valid @RequestBody VehicleDetailsModel vehicleDetails) {
		
		return vehicleDetailsRepo.save(vehicleDetails);
	}
}
