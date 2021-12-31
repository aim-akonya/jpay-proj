package com.aim.jpay.phonebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aim.jpay.phonebook.model.Customer;
import com.aim.jpay.phonebook.repository.CustomerRepo;

@RequestMapping("/api/customer/phonebook")
@RestController
public class PhonebookController {

	@Autowired
	private CustomerRepo customerRepo;

	@GetMapping
	public ResponseEntity<?> getContacts(@RequestParam(required = true, defaultValue = "0") int pageIndex,
			@RequestParam(required = true, defaultValue = "10") int pageSize) {
		List<Customer> customers = customerRepo.findAll();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

}
