package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SpringBootApplication
public class DemoCloudNativeCh21Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoCloudNativeCh21Application.class, args);
	}
}

@Entity
class Cat {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	Cat() {
	}

	public Cat(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cat{" +
			"id=" + id +
			", name='" + name + '\'' +
			'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

@RepositoryRestResource
interface CatRepository extends JpaRepository<Cat, Long> {

	// 추가된..
	Cat findByName(String name);
}

