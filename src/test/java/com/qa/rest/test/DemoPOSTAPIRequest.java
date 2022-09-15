package com.qa.rest.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.qa.rest.payload.POSTResponseData;
import com.qa.rest.payload.PostPayload;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DemoPOSTAPIRequest {
	
	public static HashMap<String, String> payloadmap= new HashMap<String, String>();
	POSTResponseData postdata;
	PostPayload jsonpayload;
	
	@BeforeClass
	public void readPostPayload() throws FileNotFoundException {
		 BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/Resources/postpayload.json"));
		 jsonpayload=new Gson().fromJson(br, PostPayload.class); 
	}
	
	@BeforeTest
	public void postData() {
		
		payloadmap.put("name", "Ajay");
		payloadmap.put("job", "QA");
		
		baseURI="https://reqres.in";
		basePath="/api/users";
		
		
	}
	
	
	@Test
	public void verifyPOSTRequest() {
		
		given()
			.contentType("application/json")
			.body(payloadmap)
		
		.when()
			.post()
		
		.then()
			//.log()
			//.body()
			.statusCode(201)
			.and()
			.body("name", equalTo(payloadmap.get("name")))
			.and()
			.body("job", equalTo(payloadmap.get("job")));
			
			
	}
	
	@Test
	public void verifyPOSTRequestExtract() {
		
	String ResponseData=given()
				.contentType("application/json")
				.body(payloadmap)			
			.when()
				.post()
			
			.then()
				//.log()
				//.body()
				.statusCode(201)
				.extract().asPrettyString();
	
	
	System.out.println("Id created: "+ ResponseData);
	
	postdata = new Gson().fromJson(ResponseData,POSTResponseData.class);
	
	System.out.println(postdata.toString());
	System.out.println("Id: " + postdata.getId());
			
	}
	
	@Test
	public void verifyPOSTRequestUsingJSONFile() {
		
		String ResponseData=given()
					.contentType("application/json")
					.body(jsonpayload)			
				.when()
					.post()
				
				.then()
					//.log()
					//.body()
					.statusCode(201)
					.extract().asPrettyString();
		
		
		System.out.println("Id created: "+ ResponseData);
		
		postdata = new Gson().fromJson(ResponseData,POSTResponseData.class);
		
		System.out.println(postdata.toString());
		System.out.println("Id: " + postdata.getId());
				
		}

}
