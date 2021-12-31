package com.aim.jpay.phonebook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aim.jpay.phonebook.dto.PhonebookDTO;
import com.aim.jpay.phonebook.model.Customer;

@Service
public interface CustomerService {

	public PhonebookDTO buildPhonebookEntry(Customer customer);

	public List<PhonebookDTO> fetchPhoneBook();

}
