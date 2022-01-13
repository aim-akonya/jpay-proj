package com.aim.jpay.phonebook.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aim.jpay.phonebook.dto.Phonebook;
import com.aim.jpay.phonebook.model.Country;
import com.aim.jpay.phonebook.model.Customer;
import com.aim.jpay.phonebook.model.States;
import com.aim.jpay.phonebook.repository.CustomerRepo;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@Mock
	private CustomerRepo customerRepo;

	private CustomerService customerService;

	private PhonebookProcessor processor;

	private List<Customer> testCustomers;

	@BeforeEach
	public void setUp() {
		processor = new PhonebookProcessorImpl();
		customerService = new CustomerServiceImpl(customerRepo, processor);
		testCustomers = new ArrayList<>();
		testCustomers.add(new Customer(1, "Michael Akonya", "(212) 698054317")); // MOROCCO valid
		testCustomers.add(new Customer(2, "Felix Achayo", "(256) 7503O6263")); // Uganda invalid
		testCustomers.add(new Customer(3, "Kelvin", "(258) 847651504")); // MOZAMBIQUE invalid
		testCustomers.add(new Customer(4, "JACKSON NELLY", "(256) 775069443"));

	}

	@Test
	public void canFetchAllContacts() {

		BDDMockito.given(customerRepo.findAll()).willReturn(testCustomers);

		List<Phonebook> phonebook = customerService.fetchPhoneBook(null, null);

		Mockito.verify(customerRepo).findAll();

		Assertions.assertThat(phonebook.size()).isEqualTo(4);
		phonebook.forEach(entry -> {
			Assertions.assertThat(entry).isNotNull();
		});
	}

	@Test
	public void canFilterByCountryAndState() {
		BDDMockito.given(customerRepo.findAll()).willReturn(testCustomers);
		List<Phonebook> phonebook = customerService.fetchPhoneBook(Country.UGANDA, States.NOT_VALID);
		Mockito.verify(customerRepo).findAll();

		Assertions.assertThat(phonebook.size()).isEqualTo(1);

		Phonebook entry = phonebook.get(0);
		Assertions.assertThat(entry.getCountryCode()).isEqualTo(Country.UGANDA.countryCode());
		Assertions.assertThat(entry.getCountry()).isEqualTo(Country.UGANDA);
		Assertions.assertThat(entry.getState()).isEqualTo(States.NOT_VALID);
	}

	@Test
	public void canFilterByCountry() {
		BDDMockito.given(customerRepo.findAll()).willReturn(testCustomers);
		List<Phonebook> phonebook = customerService.fetchPhoneBook(Country.UGANDA, null);
		Mockito.verify(customerRepo).findAll();
		Assertions.assertThat(phonebook.size()).isEqualTo(2);
		phonebook.forEach(entry -> {
			Assertions.assertThat(entry.getCountry()).isEqualTo(Country.UGANDA);
		});
	}

	@Test
	public void canFilterByState() {
		BDDMockito.given(customerRepo.findAll()).willReturn(testCustomers);
		List<Phonebook> phonebook = customerService.fetchPhoneBook(null, States.NOT_VALID);
		Mockito.verify(customerRepo).findAll();
		Assertions.assertThat(phonebook.size()).isEqualTo(2);
		phonebook.forEach(entry -> {
			Assertions.assertThat(entry.getState()).isEqualTo(States.NOT_VALID);
		});
	}

	@Test
	public void canBuildPhonebookEntry() {
		// given
		Customer customer = testCustomers.get(0);
		// when
		Phonebook phonebook = customerService.buildPhonebookEntry(customer);
		// then
		Assertions.assertThat(phonebook.getCountry()).isEqualTo(Country.MOROCCO);
		Assertions.assertThat(phonebook.getState()).isEqualTo(States.VALID);
		Assertions.assertThat(phonebook.getCountryCode()).isEqualTo(Country.MOROCCO.countryCode());
	}

	@AfterEach
	public void tearDown() {
		customerService = null;
		testCustomers = null;
	}

}
