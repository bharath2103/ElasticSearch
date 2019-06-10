package com.bootelastic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bootelastic.adapters.AdapterTypeEnum;

import lombok.Data;

@Data
@Document(collection = "student")
public class StudentMongo {
	private String name;
	private String age;
	private String city;
	private String country;
}
