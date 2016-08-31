package com.av.rfid.data.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.av.rfid.data.entity.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}