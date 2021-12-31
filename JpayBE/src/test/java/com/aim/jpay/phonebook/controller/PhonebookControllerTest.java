package com.aim.jpay.phonebook.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class PhonebookControllerTest {

	@Autowired
	private PhonebookController underTestController;

	@Test
	void contextLoads() {
		Assertions.assertThat(underTestController).isNotNull();
	}
}
