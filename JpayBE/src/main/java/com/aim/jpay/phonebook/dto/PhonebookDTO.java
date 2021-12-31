package com.aim.jpay.phonebook.dto;

import com.aim.jpay.phonebook.model.Country;
import com.aim.jpay.phonebook.model.States;

public class PhonebookDTO {

	private String name;

	private String phoneNumber;

	private Country country;

	private States state;

	public PhonebookDTO() {

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
	
	

}
