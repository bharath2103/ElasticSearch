package com.bootelastic.model;

import lombok.Data;

@Data
public class Student {
	private String id;
	private String name;
	private String age;
	private String city;
	private String country;
	
	
	
	public Student(String name, String age, String city, String country) {
		super();
		this.name = name;
		this.age = age;
		this.city = city;
		this.country = country;
	}



	public Student() {
		super();
	}
	
	
}
