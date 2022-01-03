package com.aim.jpay.phonebook.dto;

import com.aim.jpay.phonebook.model.Country;
import com.aim.jpay.phonebook.model.States;

public class Phonebook {

	private String name;

	private String phoneNumber;

	private String countryCode;

	private Country country;

	private States state;

	public Phonebook() {

	}

	public Phonebook(String name, String phoneNumber, String countryCode, Country country, States state) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.countryCode = countryCode;
		this.country = country;
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
