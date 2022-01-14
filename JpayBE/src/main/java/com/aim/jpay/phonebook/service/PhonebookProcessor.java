package com.aim.jpay.phonebook.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.aim.jpay.phonebook.dto.Phonebook;
import com.aim.jpay.phonebook.model.Customer;

@Service
public abstract interface PhonebookProcessor {

	@Cacheable(cacheNames = "phonebook", key = "#customer.phoneNumber")
	public abstract Phonebook processPhonebook(Customer customer);
	
	
}
