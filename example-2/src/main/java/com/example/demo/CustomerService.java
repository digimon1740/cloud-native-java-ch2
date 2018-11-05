package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerService {

	private final DataSource dataSource = new EmbeddedDatabaseBuilder()
		.setName("customers").setType(EmbeddedDatabaseType.H2).build();

	public static void main(String args[]) throws Throwable {
		CustomerService customerService = new CustomerService();

		// 1.
		DataSource dataSource = customerService.dataSource;
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
		Assert.isTrue(size == 2);

		// 결과 출력
		customers.forEach(System.out::println);
	}

	Collection<Customer> findAll() {
		List<Customer> customerList = new ArrayList<>();
		try {
			try (Connection c = dataSource.getConnection()) {
				Statement statement = c.createStatement();
				try (ResultSet rs = statement.executeQuery("select * from customers")) {
					while (rs.next()) {
						Long id = rs.getLong("id");
						String email = rs.getString("email");
						Customer customer = new Customer(id, email);
						customerList.add(customer);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return customerList;

	}
}
