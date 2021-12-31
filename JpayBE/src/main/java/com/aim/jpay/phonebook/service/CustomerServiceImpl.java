package com.aim.jpay.phonebook.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.aim.jpay.phonebook.dto.PhonebookDTO;
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
	public PhonebookDTO buildPhonebookEntry(Customer customer) {

		PhonebookDTO phonebook = new PhonebookDTO();
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
				String code = customer.getPhoneNumber().split(" ")[0];
				if (code.contains(country.countryCode())) {
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
	public List<PhonebookDTO> fetchPhoneBook() {
		// TODO Auto-generated method stub
		return null;
	}

}
