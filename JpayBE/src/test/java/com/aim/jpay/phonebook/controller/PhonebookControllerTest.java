package com.aim.jpay.phonebook.controller;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.aim.jpay.phonebook.dto.Phonebook;
import com.aim.jpay.phonebook.model.Country;
import com.aim.jpay.phonebook.model.States;
import com.aim.jpay.phonebook.service.CustomerService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class PhonebookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	private List<Phonebook> testEntries;

	@BeforeEach
	void setUp() {
		testEntries = new ArrayList<>();
		testEntries.add(new Phonebook("JACKSON NELLY", "(256) 775069443", "256", Country.UGANDA, States.VALID));
		testEntries.add(new Phonebook("Walid Hammadi", "(212) 6007989253", "212", Country.MOROCCO, States.NOT_VALID));
		testEntries.add(new Phonebook("Edunildo Gomes Alberto ", "(258) 847651504", "258", Country.MOZAMBIQUE,
				States.NOT_VALID));
	}

	@Test
	public void shouldReturnAllContacts() throws Exception {

		Mockito.when(customerService.fetchPhoneBook(null, null)).thenReturn(testEntries);
		

		mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/phonebook").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.dataList", Matchers.hasSize(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.dataList[0].name", Matchers.is("JACKSON NELLY")));

		ArgumentCaptor<States> stateArgumentCaptor = ArgumentCaptor.forClass(States.class);
		ArgumentCaptor<Country> countryArgumentCaptor = ArgumentCaptor.forClass(Country.class);

		Mockito.verify(customerService).fetchPhoneBook(countryArgumentCaptor.capture(), stateArgumentCaptor.capture());

		States capturedState = stateArgumentCaptor.getValue();
		Country capturedCountry = countryArgumentCaptor.getValue();
		Assertions.assertThat(capturedCountry).isNull();
		Assertions.assertThat(capturedState).isNull();
	}

	@Test
	public void shouldFilterContacts() throws Exception {
		testEntries.remove(0);
		Mockito.when(customerService.fetchPhoneBook(null, States.NOT_VALID)).thenReturn(testEntries);

		String filterState = States.NOT_VALID.name();

		mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/phonebook?state=" + filterState)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.dataList", Matchers.hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.dataList[0].name", Matchers.is("Walid Hammadi")));

		ArgumentCaptor<States> stateArgumentCaptor = ArgumentCaptor.forClass(States.class);
		ArgumentCaptor<Country> countryArgumentCaptor = ArgumentCaptor.forClass(Country.class);

		Mockito.verify(customerService).fetchPhoneBook(countryArgumentCaptor.capture(), stateArgumentCaptor.capture());

		States capturedState = stateArgumentCaptor.getValue();
		Country capturedCountry = countryArgumentCaptor.getValue();

		Assertions.assertThat(capturedCountry).isNull();
		Assertions.assertThat(capturedState).isEqualTo(States.NOT_VALID);
	}

	@Test
	public void tearDown() {
		this.testEntries = null;
	}

}
