package com.aim.jpay.phonebook.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aim.jpay.phonebook.dto.PhonebookDTO;
import com.aim.jpay.phonebook.model.Customer;
import com.aim.jpay.phonebook.model.States;

@SpringBootTest()
class CustomerServiceTest {

	@Autowired
	private CustomerService underTest;

	@Test
	void test_buildPhonebookEntry_stateValid() {
		// given
		Customer customer = new Customer();
		customer.setId(2001);
		customer.setName("Michael Akonya");
		customer.setPhoneNumber("(237) 678000959");

		// when
		PhonebookDTO phonebook = underTest.buildPhonebookEntry(customer);

		// then
		Assertions.assertThat(phonebook).isInstanceOf(PhonebookDTO.class);
		Assertions.assertThat(phonebook.getCountry().name()).isEqualTo("CAMEROON");
		Assertions.assertThat(phonebook.getState().name()).isEqualTo(States.Valid.name());
	}

	@Test
	void test_buildPhonebookEntry_stateInvalid() {
		// given
		Customer customer = new Customer();
		customer.setId(2001);
		customer.setName("Michael Akonya");
		customer.setPhoneNumber("(237) 6780009592");

		// when
		PhonebookDTO phonebook = underTest.buildPhonebookEntry(customer);

		// then
		Assertions.assertThat(phonebook).isInstanceOf(PhonebookDTO.class);
		Assertions.assertThat(phonebook.getCountry().name()).isEqualTo("CAMEROON");
		Assertions.assertThat(phonebook.getState().name()).isEqualTo(States.Not_VALID.name());
	}

	@Test
	void test_buildPhonebookEntry_countryNull() {
		// given
		Customer customer = new Customer();
		customer.setId(2001);
		customer.setName("Michael Akonya");
		customer.setPhoneNumber("(277) 6780009592");

		// when
		PhonebookDTO phonebook = underTest.buildPhonebookEntry(customer);

		// then
		Assertions.assertThat(phonebook).isInstanceOf(PhonebookDTO.class);
		Assertions.assertThat(phonebook.getCountry()).isNull();
		Assertions.assertThat(phonebook.getState()).isNotNull();
		Assertions.assertThat(phonebook.getState().name()).isEqualTo(States.Not_VALID.name());
	}

}
