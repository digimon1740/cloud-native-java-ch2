package com.example.demo.boot;

import com.example.demo.Customer;
import com.example.demo.componentscan.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CustomerRestController {

	private final CustomerService customerService;

	public CustomerRestController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/customers")
	public Collection<Customer> readAll() {
		return this.customerService.findAll();
	}
}
