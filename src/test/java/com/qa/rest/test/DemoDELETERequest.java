package com.qa.rest.test;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DemoDELETERequest {
	
	@BeforeClass
	public void setUp(){
		
		baseURI = "https://reqres.in";
		basePath = "/api/users/2";
	}
	
	@Test
	public void verifyDeleteRequest() {
		
		given()
		.when()
			.delete()				
		.then()
			.log()
			.body()
			.statusCode(204);
		
	}

}
