package com.qa.rest.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;


public class DemoGETAPIRequest {

	@BeforeClass
	public void setUp() {
		
		baseURI="https://reqres.in";
		
	}
	
	@Test
	public void verifyGETRequest() {
	
			given()//will add headers and prerequisite steps before sending HTTP request.
			.when()
				.get("/api/users?page=2")
			.then()
				//.log()
				//.body()
				.statusCode(200)
				.assertThat()
					.body("total", equalTo(12))
					.body("data.first_name[1]", equalTo("Lindsay"))
					.body("support.text", containsString("contributions towards"))
					.header("content-type", "application/json; charset=utf-8");
			
	}
	
	@Test
	public void verifyGETExtractResult() {
		ArrayList<String> names = given()//will add headers and prerequisite steps before sending HTTP request.
							   .when()
									.get("/api/users?page=2")
								.then()
									//.log()
									//.body()
									.statusCode(200)
									.extract().path("data.first_name");
		
		System.out.println("Names records: "+ names);
		
		boolean userFound=false;
		
		for(String name:names) {
			if(name.equals("Byron")) 
				userFound=true;
					
		}
		
		Assert.assertTrue("Name not found", userFound);
		
	}
	
	
	

}
