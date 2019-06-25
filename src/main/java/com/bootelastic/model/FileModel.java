package com.bootelastic.model;

import lombok.Data;

@Data
public class FileModel {
	private String id;
	private String fileName;
	private String content;
	private String contentType;
	private String version;
	private String fileLocation;
	
	
	public FileModel(String id, String fileName, String content, String contentType, String version,
			String fileLocation) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.content = content;
		this.contentType = contentType;
		this.version = version;
		this.fileLocation = fileLocation;
	}
	
	
	
}
