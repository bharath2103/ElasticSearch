package com.bootelastic.service;

import lombok.Data;

@Data
public class Preference {

	private String primaryLocation = "MONGO";
	private String indexedLocation = "ELASTICSEARCH";
	
}
