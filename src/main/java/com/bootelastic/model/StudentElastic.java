package com.bootelastic.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;

import com.bootelastic.adapters.AdapterTypeEnum;
import org.springframework.data.repository.Repository;

import lombok.Data;

@Data
@Document(indexName = "student", type = "register")
public class StudentElastic {
	private String id;
	private String name;
	private String age;
	private String city;
	private String country;
}
