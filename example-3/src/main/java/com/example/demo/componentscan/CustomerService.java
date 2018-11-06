package com.example.demo.componentscan;

import com.example.demo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@Component
@Component("customerServiceComponentScan")
public class CustomerService {

	private DataSource dataSource;

	public CustomerService(DataSource dataSource) {
		this.dataSource = dataSource;
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
