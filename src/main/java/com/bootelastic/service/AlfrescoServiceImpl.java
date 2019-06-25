package com.bootelastic.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.bindings.spi.LinkAccess;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootelastic.model.UserAccount;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AlfrescoServiceImpl implements AlfrescoService {

	@Autowired
	private Session session;
	
	private static final String ATOMPUB_URL = "http://localhost:8080/alfresco/cmisatom";
	private static final String USER_NAME ="admin";
	private static final String PASSWORD = "admin";

	public void createFileNFolder() {
		Folder root = session.getRootFolder();
		// folder properties
		Map<String, Object> folderProperties = new HashMap<>();
		folderProperties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
		folderProperties.put(PropertyIds.NAME, "MyAlfrescoFolder");

		// create the folder
		Folder parent = createAlfrescoFolder(root, folderProperties);
		String name = "NewTextFile.txt";
		
		// check and fetch the folder name if exists

		// file properties
		Map<String, Object> fileProperties = new HashMap<>();
		fileProperties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
		fileProperties.put(PropertyIds.NAME, name);
		//	properties.put(PropertyIds.VERSION_LABEL, "2");

		// content
		UserAccount user1 = new UserAccount();
		user1.setUserid(002);
		user1.setUsername("Bharathkumar");
		ObjectMapper mapper = new ObjectMapper(); 
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(user1.toString());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	//	byte[] content = "Hello BharathkumarB ..!".getBytes();
		byte[] content = jsonString.getBytes();
		InputStream stream = new ByteArrayInputStream(content);
		ContentStream contentStream = new ContentStreamImpl(name, BigInteger.valueOf(content.length), "text/plain", stream);

		// create a major version
		Document newDoc = parent.createDocument(fileProperties, contentStream, VersioningState.MINOR);
		//	System.out.println("DONE..");
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

	private Folder createAlfrescoFolder(Folder root, Map<String, Object> folderProperties) {
		
		Folder pFolder = root.getFolderParent();
		ItemIterable<CmisObject> children = pFolder.getChildren();
		System.out.println("Children of " + pFolder.getName() + ":-");
		for (CmisObject o : children) {
		    System.out.println(o.getName());
		}
		return root.createFolder(folderProperties);
	}
	
	

}
