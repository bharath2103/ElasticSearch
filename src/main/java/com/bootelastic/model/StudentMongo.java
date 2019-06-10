package com.bootelastic.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "student")
public class StudentMongo {
	private String _id;
	private String name;
	private String age;
	private String city;
	private String country;
}
