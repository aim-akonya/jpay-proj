package com.aim.jpay.phonebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aim.jpay.phonebook.dto.PhonebookDTO;
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
	public ResponseEntity<?> getContacts() {
		ApiResponse<PhonebookDTO> apiResponse = new ApiResponse<>();
		List<PhonebookDTO> phonebook = customerService.fetchPhoneBook();
		apiResponse.setDataList(phonebook);
		apiResponse.setMessage("success");
		apiResponse.setStatus(1);
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	
	

}
