package com.av.rfid.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.av.rfid.data.entity.Epc;
import com.av.rfid.data.entity.RfidTransaction;
import com.av.rfid.data.repo.EpcRepo;
import com.av.rfid.data.repo.RfidTransactionRepo;

@SpringBootApplication
@EnableJpaRepositories("com.av.rfid.data.repo")
@EntityScan(basePackages = { "com.av.rfid.data.entity" })
@ComponentScan(basePackages={"com.av.rfid"})
//@EnableTransactionManagement
public class Application extends SpringBootServletInitializer{
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(RfidTransactionRepo repository, EpcRepo epcRepo) {
		return (args) -> {
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			Epc epc = epcRepo.findOne(7l);
			log.info(epc.toString());
			log.info("asdfasdf");
			RfidTransaction customer = repository.findTop1ByEpcOrderByReportDateTimeDesc(epc);
			log.info(customer.toString());
			log.info("");

		};

	}

	// @Bean
	// public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	// return args -> {
	//
	// System.out.println("Let's inspect the beans provided by Spring Boot:");
	//
	// String[] beanNames = ctx.getBeanDefinitionNames();
	// Arrays.sort(beanNames);
	// for (String beanName : beanNames) {
	// System.out.println(beanName);
	// }
	//
	// };
	// }

	/*
	 * @Bean public CommandLineRunner demo(CustomerRepo repository) { return
	 * (args) -> { // save a couple of customers repository.save(new
	 * Customer("Jack", "Bauer")); repository.save(new Customer("Chloe",
	 * "O'Brian")); repository.save(new Customer("Kim", "Bauer"));
	 * repository.save(new Customer("David", "Palmer")); repository.save(new
	 * Customer("Michelle", "Dessler"));
	 * 
	 * // fetch all customers log.info("Customers found with findAll():");
	 * log.info("-------------------------------"); for (Customer customer :
	 * repository.findAll()) { log.info(customer.toString()); } log.info("");
	 * 
	 * // fetch an individual customer by ID Customer customer =
	 * repository.findOne(1L); log.info("Customer found with findOne(1L):");
	 * log.info("--------------------------------");
	 * log.info(customer.toString()); log.info("");
	 * 
	 * // fetch customers by last name
	 * log.info("Customer found with findByLastName('Bauer'):");
	 * log.info("--------------------------------------------"); for (Customer
	 * bauer : repository.findByLastName("Bauer")) { log.info(bauer.toString());
	 * } log.info(""); }; }
	 */

}
