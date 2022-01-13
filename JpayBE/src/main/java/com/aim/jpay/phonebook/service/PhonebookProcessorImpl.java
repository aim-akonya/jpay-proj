package com.aim.jpay.phonebook.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.aim.jpay.phonebook.dto.Phonebook;
import com.aim.jpay.phonebook.model.Country;
import com.aim.jpay.phonebook.model.Customer;
import com.aim.jpay.phonebook.model.States;

@Service
public class PhonebookProcessorImpl implements PhonebookProcessor {

	@Override
	public Phonebook processPhonebook(Customer customer) {

		System.out.println("Called ======= >");
		Phonebook phonebook = new Phonebook();
		phonebook.setName(customer.getName());
		phonebook.setPhoneNumber(customer.getPhoneNumber());

		for (Country country : Country.values()) {
			Pattern pattern = Pattern.compile(country.regexPattern());
			Matcher matcher = pattern.matcher(customer.getPhoneNumber());
			boolean isMatch = matcher.matches();
			if (isMatch) {
				phonebook.setCountry(country);
				phonebook.setCountryCode(country.countryCode());
				phonebook.setState(States.VALID);
				break;
			} else {
				// check if number starts with the country code
				StringBuilder countryCode = new StringBuilder();
				countryCode.append("(").append(country.countryCode()).append(")");
				if (customer.getPhoneNumber().startsWith(countryCode.toString())) {
					phonebook.setCountry(country);
					phonebook.setCountryCode(country.countryCode());
					break;
				}
			}
		}

		if (phonebook.getState() == null) {
			phonebook.setState(States.NOT_VALID);
		}
		return phonebook;

	}

}
