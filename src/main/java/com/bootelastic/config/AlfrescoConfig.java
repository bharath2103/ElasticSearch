package com.bootelastic.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlfrescoConfig {
	
	@Value("${alfresco.username}")
	private String username;
	
	@Value("${alfresco.password}")
	private String password;
	
	@Value("${alfresco.atomPubUrl}")
	private String atomPubUrl;	
	
	@Bean
	public Session session() {
		SessionFactory factory = SessionFactoryImpl.newInstance();
		Map<String,String> parameter = new HashMap<>();

		// user credential
		parameter.put(SessionParameter.USER, username);
		parameter.put(SessionParameter.PASSWORD, password);

		// connection settings
		parameter.put(SessionParameter.ATOMPUB_URL, atomPubUrl);
		parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		// create session
		List<Repository> repositories = factory.getRepositories(parameter);
		return repositories.get(0).createSession();
	}
	

}
