package com.bootelastic.service;

public interface AlfrescoService {
	
	public void createFileNFolder();
	
	public String fetchDocument();
	
	public boolean checkDuplicates(String folder, String file);
	
	public void doesFolderExist(String folderName);

}
