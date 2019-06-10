package com.bootelastic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration	
@EnableMongoRepositories(basePackages = "com.bootelastic.repository")
public class MongooConfig {
	@Value("${mongodb.host}")
	private String mongoHost;

	@Value("${mongodb.port}")
	private int mongoPort;

	@Value("${mongodb.database}")
	private String mongoDbName;

	@Value("${mongodb.collection}")
	private String mongoCollName;

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {

		MongoClient mongoClient = new MongoClient(mongoHost, mongoPort);
		UserCredentials userCredentials = new UserCredentials("", "");
		return new SimpleMongoDbFactory(mongoClient,mongoDbName);
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;

	}
}
