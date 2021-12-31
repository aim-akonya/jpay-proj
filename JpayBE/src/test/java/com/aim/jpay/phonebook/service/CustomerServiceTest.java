package com.aim.jpay.phonebook.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.aim.jpay.phonebook.model.Country;

@SpringBootTest()
class CustomerServiceTest {

	@Test
	void test() {
		String val = "(237) 678000959";
		for (Country country : Country.values()) {
			Pattern pattern = Pattern.compile(country.regexPattern());
			Matcher matcher = pattern.matcher(val);
			boolean matchFound = matcher.find();
			if(matchFound) {
				System.out.println("Country :" + country.name());
			}else {
				System.out.println("Country : NA");
			}
		}
	}

}
