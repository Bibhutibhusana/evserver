package com.nic.ev.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.exception.BusinessException;
import com.nic.ev.repo.VehicleDetailsRepo;


@RestController
@CrossOrigin
@RequestMapping("/")
public class SanctionOrderController {
	

    @Autowired VehicleDetailsRepo vehicleDetailsRepo;
    
    @PostMapping(value = "/sanctionOrder", produces = MediaType.APPLICATION_JSON_VALUE)
	private Map<String, Object> getToApprove(@Valid @RequestBody String appl_no) throws BusinessException {
		try {
			Map<String, Object> so = new HashMap<>();
			Map<String, Object> so1 = new HashMap<>();

			so = vehicleDetailsRepo.getSanctionOrder(appl_no);

			int amnt = Integer.parseInt((String) so.get("sub_amnt"));

			so1.putAll(so);
			so1.put("inWords", numberToWord(amnt));

			return so1;
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

	public String numberToWord(int number) throws BusinessException {
		try {
			// variable to hold string representation of number
			String words = "";
			String unitsArray[] = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
					"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
					"nineteen" };
			String tensArray[] = { "zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
					"ninety" };
			if (number == 0) {
				return "zero";
			}
			// add minus before conversion if the number is less than 0
			if (number < 0) {
				// convert the number to a string
				String numberStr = "" + number;
				// remove minus before the number
				numberStr = numberStr.substring(1);
				// add minus before the number and convert the rest of number
				return "minus " + numberToWord(Integer.parseInt(numberStr));
			}
			// check if number is divisible by 1 million
			if ((number / 1000000) > 0) {
				words += numberToWord(number / 1000000) + " million ";
				number %= 1000000;
			}
			// check if number is divisible by 1 thousand
			if ((number / 1000) > 0) {
				words += numberToWord(number / 1000) + " thousand ";
				number %= 1000;
			}
			// check if number is divisible by 1 hundred
			if ((number / 100) > 0) {
				words += numberToWord(number / 100) + " hundred ";
				number %= 100;
			}
			if (number > 0) {
				// check if number is within teens
				if (number < 20) {
					// fetch the appropriate value from unit array
					words += unitsArray[number];
				} else {
					// fetch the appropriate value from tens array
					words += tensArray[number / 10];
					if ((number % 10) > 0) {
						words += "-" + unitsArray[number % 10];
					}
				}
			}
			return words;
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

}




