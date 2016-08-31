package com.av.rfid.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.av.rfid.data.entity.Customer;
import com.av.rfid.data.repo.CustomerRepo;
import com.av.rfid.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
	@Autowired
	CustomerRepo customerRepo;

	public List<Customer> getCustomerList() {
		log.info("Listing all Customers");
		List<Customer> list = new ArrayList<>();
		customerRepo.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

}
