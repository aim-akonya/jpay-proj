package com.aim.jpay.phonebook.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.aim.jpay.phonebook.dto.Phonebook;
import com.aim.jpay.phonebook.model.Country;
import com.aim.jpay.phonebook.model.Customer;
import com.aim.jpay.phonebook.model.States;

@Service
public interface CustomerService {

	@Cacheable(cacheNames = "phonebook", key = "#customer.getPhoneNumber()")
	public Phonebook buildPhonebookEntry(Customer customer);

	public List<Phonebook> fetchPhoneBook(Country country, States state);

}
