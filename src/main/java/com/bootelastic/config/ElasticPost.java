package com.bootelastic.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class ElasticPost {

	public static void main(String[] args) {

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:9200/accounts/account/");

			
			String jsonData = "{\n" + 
					"	\"id\": 1,\n" + 
					"          \"namedAddressId\": 1508,\n" + 
					"          \"namedAddressOrganizationId\": 1507,\n" + 
					"          \"ownerEid\": 600000001,\n" + 
					"          \"type\": EID,\n" + 
					"          \"value\": 700000032,\n" + 
					"}";
			
//			5c9b873f7e079040c83e91f8
//			Meta_Test1
			for (int i = 0; i < 1600; i++) {
				jsonData = jsonData.replaceAll("::id::", "5c9b873f7e079040c83e91f"+i);
				jsonData = jsonData.replaceAll("::poNumber::", "Meta_Test"+i);
				StringEntity input = new StringEntity(jsonData);
				input.setContentType("application/json");
				postRequest.setEntity(input);
				
				HttpResponse response = httpClient.execute(postRequest);
				if (response.getStatusLine().getStatusCode() != 201) {
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
				}
				
				BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
				
				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}
			}
			


			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
