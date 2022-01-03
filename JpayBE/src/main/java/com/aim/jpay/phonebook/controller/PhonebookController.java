package com.aim.jpay.phonebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aim.jpay.phonebook.dto.Phonebook;
import com.aim.jpay.phonebook.model.Country;
import com.aim.jpay.phonebook.model.States;
import com.aim.jpay.phonebook.service.CustomerService;
import com.aim.jpay.util.ApiResponse;

@RequestMapping("/api/customer/phonebook")
@RestController
public class PhonebookController {

	private CustomerService customerService;

	@Autowired
	public PhonebookController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public ResponseEntity<?> getContacts(@RequestParam(value = "country", required = false) Country country,
			@RequestParam(value = "state", required = false) States state) {
		ApiResponse<Phonebook> apiResponse = new ApiResponse<>();
		List<Phonebook> phonebook = customerService.fetchPhoneBook(country, state);
		apiResponse.setDataList(phonebook);
		apiResponse.setMessage("success");
		apiResponse.setStatus(1);
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

}
