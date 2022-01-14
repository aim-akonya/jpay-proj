package com.aim.jpay.phonebook.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aim.jpay.phonebook.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	@Query(nativeQuery = true, countQuery = "SELECT COUNT(*) FROM customer WHERE phone LIKE ?1%", 
			value = "SELECT * FROM customer WHERE phone LIKE ?1%")
	public List<Customer> findByCountry(String countryCode, Pageable pageable);
	
	
	@Query(nativeQuery = true, value="SELECT * FROM customer WHERE phone LIKE ?1% AND (phone REGEXP ?2)",
			countQuery="SELECT COUNT(*) FROM customer WHERE phone LIKE ?1% AND (phone REGEXP ?2)")
	public List<Customer> findValidByCountry(String countryCode, String regStr, Pageable pageable);
	
	

}
