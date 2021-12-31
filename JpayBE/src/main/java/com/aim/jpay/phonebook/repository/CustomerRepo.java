package com.aim.jpay.phonebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aim.jpay.phonebook.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
