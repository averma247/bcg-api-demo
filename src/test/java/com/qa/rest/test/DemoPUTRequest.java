package com.qa.rest.test;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.qa.rest.payload.PostPayload;

public class DemoPUTRequest {

	PostPayload jsonpayload;

	@BeforeTest
	public void readPostPayload() throws FileNotFoundException {
		BufferedReader br = new BufferedReader(
				new FileReader(System.getProperty("user.dir") + "/Resources/postpayload.json"));
		jsonpayload = new Gson().fromJson(br, PostPayload.class);
		baseURI = "https://reqres.in";
		basePath = "/api/users/2";
	}

	@Test
	public void verifyPUTRequest() {

		given()
			.contentType("application/json")
			.body(jsonpayload)
		.when()
			.put()				
		.then()
			.log()
			.body()
			.statusCode(200);

	}

}
