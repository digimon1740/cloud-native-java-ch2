package com.example.demo.componentscan;

public class Customer {

	private Long id;
	private String email;

	Customer(Long id, String email) {
		this.id = id;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer{" +
			"id=" + id +
			", email='" + email + '\'' +
			'}';
	}
}
