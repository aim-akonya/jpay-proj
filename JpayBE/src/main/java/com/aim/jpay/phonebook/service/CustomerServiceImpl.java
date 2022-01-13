package com.aim.jpay.phonebook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aim.jpay.phonebook.dto.Phonebook;
import com.aim.jpay.phonebook.model.Country;
import com.aim.jpay.phonebook.model.Customer;
import com.aim.jpay.phonebook.model.States;
import com.aim.jpay.phonebook.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepo customerRepo;
	private PhonebookProcessor processor;

	@Autowired
	public CustomerServiceImpl(CustomerRepo customerRepo, PhonebookProcessor processor) {
		this.customerRepo = customerRepo;
		this.processor = processor;
	}

	@Override
	public Phonebook buildPhonebookEntry(Customer customer) {
		return processor.processPhonebook(customer);
	}

	@Override
	public List<Phonebook> fetchPhoneBook(Country country, States state) {

		List<Phonebook> phonebooks = new ArrayList<>();
		List<Customer> customers = customerRepo.findAll();

		if (country != null && state != null) {
			customers.forEach(entry -> {
				Phonebook phonebook = this.buildPhonebookEntry(entry);
				if (phonebook.getCountry() == country && phonebook.getState() == state) {
					phonebooks.add(phonebook);
				}
			});

		} else if (country != null) {
			customers.forEach(entry -> {
				Phonebook phonebook = this.buildPhonebookEntry(entry);
				if (phonebook.getCountry() == country) {
					phonebooks.add(phonebook);
				}
			});

		} else if (state != null) {
			customers.forEach(entry -> {
				Phonebook phonebook = this.buildPhonebookEntry(entry);
				if (phonebook.getState() == state) {
					phonebooks.add(phonebook);
				}
			});

		} else {
			customers.forEach(entry -> {
				Phonebook phonebook = this.buildPhonebookEntry(entry);
				phonebooks.add(phonebook);
			});
		}

		return phonebooks;
	}

}
