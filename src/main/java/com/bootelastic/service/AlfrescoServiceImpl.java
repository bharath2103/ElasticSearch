package com.bootelastic.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.bindings.spi.LinkAccess;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootelastic.model.FileModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AlfrescoServiceImpl implements AlfrescoService {

	@Autowired
	private Session session;

	public void createFileNFolder() {
		Folder root = session.getRootFolder();
		
		// Folder properties
		Map<String, Object> folderProperties = new HashMap<>();
		folderProperties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
		folderProperties.put(PropertyIds.NAME, "MyAlfrescoFolder");

		// Create the folder
		Folder parent = root.createFolder(folderProperties);
		String name = "NewTextFile.txt";
		
		// File properties
		Map<String, Object> fileProperties = new HashMap<>();
		fileProperties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
		fileProperties.put(PropertyIds.NAME, name);
		FileModel fileModel = new FileModel("001","Sample","This is a sample file","pdf","2.0","user/bin");
		ObjectMapper mapper = new ObjectMapper(); 
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(fileModel.toString());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		byte[] content = jsonString.getBytes();
		InputStream stream = new ByteArrayInputStream(content);
		ContentStream contentStream = new ContentStreamImpl(name, BigInteger.valueOf(content.length), "text/plain", stream);

		// Create a major version
		Document newDoc = parent.createDocument(fileProperties, contentStream, VersioningState.MINOR);
	}

	public String fetchDocument() {
		
		String uri = "";
		Document document = (Document) session.getObjectByPath("/MyAlfrescoFolder/NewTextFile.txt");
		List<Document> documents = document.getAllVersions();
		Iterator<Document> itr = documents.iterator();
		while(itr.hasNext()) {
			Document version = (Document) itr.next();
			System.out.println(
					((LinkAccess) session.getBinding().getObjectService())
					.loadContentLink(
							session.getRepositoryInfo().getId(),
							version.getId()
							)
					);

			uri = ((LinkAccess) session.getBinding().getObjectService()).loadContentLink(
					session.getRepositoryInfo().getId(),
					version.getId());
		}
		NodeRef node = new NodeRef(uri);
		
		
		node.getStoreRef();
		return uri;
	}

	public boolean checkDuplicates(String folder, String file) {
		String uri = "";
		boolean status = false;
		Document document = (Document) session.getObjectByPath("/"+folder+"/"+file);
		
		if(!document.getId().isEmpty()) {
			status = true;
		}
		return status;
	}

	

}
