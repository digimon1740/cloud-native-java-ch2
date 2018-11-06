package com.example.demo.psa;

import com.example.demo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Component("customerServicePsa")
public class CustomerService {

	private final JdbcTemplate jdbcTemplate;

	public CustomerService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	Collection<Customer> findAll() {
		RowMapper<Customer> rowMapper = (rs, i) ->
			new Customer(rs.getLong("ID"), rs.getString("EMAIL"));
		return this.jdbcTemplate.query("select * from CUSTOMERS ", rowMapper);
	}
}
