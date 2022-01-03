package com.aim.jpay.phonebook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.aim.jpay.phonebook.dto.Phonebook;
import com.aim.jpay.phonebook.model.Country;
import com.aim.jpay.phonebook.model.Customer;
import com.aim.jpay.phonebook.model.States;
import com.aim.jpay.phonebook.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepo customerRepo;

	public CustomerServiceImpl(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}

	@Override
	@Cacheable(cacheNames = "phonebook", key = "#customer.getPhoneNumber()")
	public Phonebook buildPhonebookEntry(Customer customer) {

		Phonebook phonebook = new Phonebook();
		phonebook.setName(customer.getName());
		phonebook.setPhoneNumber(customer.getPhoneNumber());

		for (Country country : Country.values()) {
			Pattern pattern = Pattern.compile(country.regexPattern());
			Matcher matcher = pattern.matcher(customer.getPhoneNumber());
			boolean isMatch = matcher.matches();
			if (isMatch) {
				phonebook.setCountry(country);
				phonebook.setState(States.Valid);
				break;
			} else {
				// check if number starts with the country code
				StringBuilder countryCode = new StringBuilder();
				countryCode.append("(").append(country.countryCode()).append(")");
				if (customer.getPhoneNumber().startsWith(countryCode.toString())) {
					phonebook.setCountry(country);
					break;
				}
			}
		}
		if (phonebook.getState() == null) {
			phonebook.setState(States.Not_VALID);
		}
		return phonebook;
	}

	@Override
	public List<Phonebook> fetchPhoneBook(Country country, States state) {

		List<Phonebook> phonebooks = new ArrayList<>();

		if (country != null && state != null) {

		} else if (country != null) {

		} else if (state != null) {

		} else {

		}

		List<Customer> customers = customerRepo.findAll();

		customers.forEach(entry -> {
			Phonebook phonebook = this.buildPhonebookEntry(entry);
			phonebooks.add(phonebook);
		});
		return phonebooks;
	}

}
