package com.bootelastic.model;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName = "elastic", type = "filesystem")
public class ElasticFileModel {
	
	private String id;
	private String fileName;
	private String content;
	private String contentType;
	private String version;
	private String fileLocation;
}
