package com.nic.ev.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class VehicleDetailsControllerNew {
	
	@Autowired VehicleDetailsRepo vdRepo;
	
	@PostMapping("/getVehicleDetailsToDelete")
	private Optional<VehicleDetailsModel> getVehicleDetails(@RequestBody String regn){
		
		return vdRepo.findById(regn);
	}
	
	@DeleteMapping("/deleteVehicleDetailsByRegn/{regn}")
	 Map<String, Boolean> deleteByRegnNo( @PathVariable(value = "regn") String regn) {
		vdRepo.deleteById(regn);
		
		 Map<String, Boolean> rs = new HashMap<>();
		 
		 rs.put("deleted",true);
		 
		 return rs;
		
	}

}
