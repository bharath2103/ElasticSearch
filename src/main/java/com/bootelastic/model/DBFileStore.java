package com.bootelastic.model;

import lombok.Data;

@Data
public class DBFileStore {
	private String id;
	private String fileName;
	private String fileType;
	private String fileLocation;
}
