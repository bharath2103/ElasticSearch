package com.bootelastic.model;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName = "student", type = "register")
public class Student {
	private String id;
	private String name;
	private String age;
	private String city;
	private String country;
}
