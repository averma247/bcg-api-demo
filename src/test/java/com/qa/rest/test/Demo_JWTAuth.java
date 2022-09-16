package com.qa.rest.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class Demo_JWTAuth {

	/**
	 * 1. Register the user with the end point 
	 * 2. Authenticate and generate the token 
	 * 3. Extract the token using Json path 
	 * 4. Send the request with jwt token
	 */

	String token = null;
	String userId = null;
	public static HashMap<String, String> usercreds = new HashMap<String, String>();

	@BeforeClass
	public void registerandgenerateToken() {
		
		try {
			
			baseURI = "https://bookstore.toolsqa.com";

			usercreds.put("userName", "testuserajay105");
			usercreds.put("password", "TestPassword@2020");

			registerUser(usercreds);
			System.out.println("UserID generated: " + userId);

			generateToken(usercreds);
			System.out.println("Token value generated: " + token);
			
		}
		catch(Exception e) {
			System.out.println(e);						
		}

		

	}
	
	
	
	
	
	public void registerUser(HashMap<String, String> usercreds) {
		
		try{
			
		userId= given()
					.contentType(ContentType.JSON)
					.body(usercreds)
				.when()
					.post("/Account/v1/User")
				.then()
					.statusCode(201)
					.extract().path("userID");
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
		
	}
	
	
	public void generateToken(HashMap<String, String> usercreds) {
			token=			given()
								.contentType(ContentType.JSON)
								.body(usercreds)
							.when()
								.post("/Account/v1/GenerateToken")
							.then()
								.statusCode(200)
								.extract().path("token");

	}

	@Test
	public void getUsersBooksDetails() {
		
		System.out.println("Inside books id.");
		
		given()
			.auth()
			.oauth2(token)
		.when()
			.get("/Account/v1/User/"+userId)
		.then()
			.log()
			.body()
			.statusCode(200)
			.assertThat()
				.body("userId", equalTo(userId))
				.body("username",equalTo(usercreds.get("userName")));
			
		
		
	}
}
