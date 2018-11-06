package com.example.demo.componentscan;

import com.example.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private DataSource dataSource;

	@Test
	public void findAllTest() {
		DataSourceInitializer init = new DataSourceInitializer();
		init.setDataSource(dataSource);

		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setScripts(new ClassPathResource("schema.sql"),
			new ClassPathResource("data.sql"));
		init.setDatabasePopulator(populator);
		init.afterPropertiesSet();

		// 2.
		Collection<Customer> customers = customerService.findAll();
		int size = customers.size();
		//Assert.isTrue(size == 2);

		// 결과 출력
		customers.forEach(System.out::println);
	}

}
