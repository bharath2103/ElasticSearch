package com.bootelastic.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.model.filefolder.FileFolderServiceImpl;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlfrescoServiceImpl implements AlfrescoService {

	@Autowired
	private Session session;
	
	Folder parent = null;
	
	public void createFileNFolder() {
		Folder root = session.getRootFolder();
		// properties
		Map<String, Object> properties = new HashMap<>();
		properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
		properties.put(PropertyIds.NAME, "MyAlfrescoFolder");
		
		//check whether the folder exists
		if(doesFolderExist("MyAlfrescoFolder")) {
//			parent = root.createFolder(properties);
			System.out.println("Folder already exists");
			
			NodeRef contextNodeRef = new NodeRef("MyAlfrescoFolder");
			FileFolderServiceImpl FileFolderService = new FileFolderServiceImpl();
			List<FileInfo> fileInfo = FileFolderService.search(contextNodeRef, "MyAlfrescoFolder", true);
		}
		else {
			parent = root.createFolder(properties);
		}
		String name = "NewTextFile.txt";

		// properties
		Map<String, Object> properties2 = new HashMap<>();
		properties2.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
		properties2.put(PropertyIds.NAME, name);

		// content
		byte[] content = "Hello Bharathkumar ..!".getBytes();
		InputStream stream = new ByteArrayInputStream(content);
		ContentStream contentStream = new ContentStreamImpl(name, BigInteger.valueOf(content.length), "text/plain", stream);

		// create a major version
		Document newDoc = parent.createDocument(properties2, contentStream, VersioningState.MAJOR);

	}
	
	public boolean doesFolderExist(String folderName) {
        String queryString = "select cmis:objectId from cmis:folder where cmis:name = '" + "MyAlfrescoFolder" + "'";
       ItemIterable<QueryResult> results = session.query(queryString, false);
       if (results.getTotalNumItems() > 0) {
            return true;
       } else {
            return false;
       }
  }
}
