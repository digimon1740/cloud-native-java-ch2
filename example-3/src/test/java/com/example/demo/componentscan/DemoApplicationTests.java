package com.example.demo.componentscan;

import com.example.demo.Customer;
import com.example.demo.DemoApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {

	private CustomerService customerService;

	@Autowired
	private DataSource dataSource;

	@Before
	public void before() {
		customerService = new CustomerService(dataSource);
	}

	@Test
	public void findAllTest() {
		Collection<Customer> customers = customerService.findAll();
		int size = customers.size();
		//Assert.isTrue(size == 2);
		Assert.assertEquals(2, size);
		// 결과 출력
		customers.forEach(System.out::println);
	}

}
