package com.av.rfid.data.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.av.rfid.data.entity.Customer;
import com.av.rfid.main.Application;

@RunWith(SpringRunner.class)
// @TestPropertySource(value = { "/datasource.properties" })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest(classes = Application.class)
public class CustomerRepoTest {

	private CustomerRepo customerRepo;

	@Autowired
	public void setProductRepository(CustomerRepo productRepository) {
		this.customerRepo = productRepository;
	}

	@Test
	public void testSaveProduct() {
		// setup product
		Customer product = new Customer(null, "Chetan", "Prajapati");

		// save product, verify has ID value after save
		assertNull(product.getId());
		customerRepo.save(product);
		assertNotNull(product.getId()); // not null after save

		// fetch from DB
		Customer fetchedProduct = customerRepo.findOne(product.getId());

		// should not be null
		assertNotNull(fetchedProduct);

		// should equal
		assertEquals(product.getId(), fetchedProduct.getId());

		// update description and save
	}
}
