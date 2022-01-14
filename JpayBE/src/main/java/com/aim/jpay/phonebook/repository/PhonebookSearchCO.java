package com.aim.jpay.phonebook.repository;

import java.util.Optional;

import com.aim.jpay.phonebook.model.Country;
import com.aim.jpay.phonebook.model.States;

/*
 * PhonebookSearchCo
 * command object with custom filter and pagination setup
 * */

public class PhonebookSearchCO {

	Optional<Integer> page;
	Optional<Integer> size;

	private Country country;

	private States state;

	public Optional<Integer> getPage() {
		return page;
	}

	public void setPage(Optional<Integer> page) {
		this.page = page;
	}

	public Optional<Integer> getSize() {
		return size;
	}

	public void setSize(Optional<Integer> size) {
		this.size = size;
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
