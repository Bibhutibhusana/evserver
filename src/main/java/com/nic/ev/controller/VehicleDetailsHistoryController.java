package com.nic.ev.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.model.VehicleDetailsHistoryModel;
import com.nic.ev.repo.VehicleDetailsHistoryRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class VehicleDetailsHistoryController {
	
	@Autowired VehicleDetailsHistoryRepo vehicleHistoyRepo;
	
	@PostMapping("/insertToVehicleDetailsHistory")
	private VehicleDetailsHistoryModel insertUserStatus(@Valid @RequestBody VehicleDetailsHistoryModel vdhm) {
		
		return vehicleHistoyRepo.save(vdhm);	
		}

}
