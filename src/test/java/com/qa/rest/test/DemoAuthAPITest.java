package com.qa.rest.test;

import static io.restassured.RestAssured.baseURI;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class DemoAuthAPITest {
	
	@BeforeTest
	public void setup() {
		
		baseURI="https://postman-echo.com";
		
	}
	
	
	@Test
	public void verifyBasicAuth() {
		
			given()
				.auth()
				.basic("postman", "password")
			.when()
				.get("/basic-auth")
			.then()
				.log()
				.body()
				.statusCode(200);
	}
	
//	
//	@Test
//	public void verifyDigestAuth() {
//		
//			given()
//				.auth()
//				.digest("postman", "password")
//			.when()
//				.get("/digest-auth")
//			.then()
//				.log()
//				.body()
//				.statusCode(200);
//	}
//	
	
	@Test
	public void verifyOAuth1() {
		
			given()
				.auth()
				.oauth("RKCGzna7bv9YD57c", "D+EdQ-gs$-%@2Nu7","","")
			.when()
				.get("/oauth1")
			.then()
				.log()
				.body()
				.statusCode(200);
				
	}
	

}
