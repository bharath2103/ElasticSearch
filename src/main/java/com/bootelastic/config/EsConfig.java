package com.bootelastic.config;

import java.net.InetAddress;

import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.bootelastic.repository")
public class EsConfig {

	@Value("${elasticsearch.host}")
	private String esHost;

	@Value("${elasticsearch.port}")
	private int esPort;

	@Value("${elasticsearch.clustername}")
	private String esClusterName;

	@SuppressWarnings("resource")
	@Bean
	public Client client() throws Exception {
		Settings esSettings = Settings.builder().put("cluster.name", esClusterName).build();
		TransportClient client = new PreBuiltTransportClient(esSettings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
//		UpdateRequest 
		
		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws Exception {
		return new ElasticsearchTemplate(client());
	}

}
