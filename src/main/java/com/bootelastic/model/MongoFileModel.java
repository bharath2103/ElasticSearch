package com.bootelastic.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "mongofilesystem")
public class MongoFileModel {
	
	private String _id;
	private String fileName;
	private String content;
	private String contentType;
	private String version;
	private String fileLocation;
}
