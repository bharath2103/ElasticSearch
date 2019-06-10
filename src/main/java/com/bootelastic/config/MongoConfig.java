package com.bootelastic.config;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import lombok.Data;

@Configuration
@Data
public class MongoConfig {
	@Value("${mongodb.host}")
	private String mongoHost;

	@Value("${mongodb.port}")
	private int mongoPort;

	@Value("${mongodb.database}")
	private String mongoDbName;
	
	@Value("${mongodb.collection}")
	private String mongoCollName;
	
	@Bean
	public MongoDatabase mongoDatabase() {
		MongoClientURI uri = new MongoClientURI(getHostName());
		MongoClient mongo_client = new MongoClient(uri);
		MongoDatabase db = mongo_client.getDatabase(mongoDbName);
		return db;
	}
	
	public String getHostName(){
		String client_url = "mongodb://" + mongoHost + ":" + mongoPort + "/" + mongoDbName;
		return client_url;
	}
}
