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
}
