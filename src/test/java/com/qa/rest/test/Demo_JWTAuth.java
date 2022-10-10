package com.qa.rest.test;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

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
	ExtentHtmlReporter htmlReporter;
	static ExtentTest test;
	static ExtentReports report;
	
	@BeforeClass
	public void registerandgenerateToken() {
		
		try {
			
			  htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/ExtentReport.html");	 
			  report = new ExtentReports();
			  report.attachReporter(htmlReporter);
			
			  //configuration items to change the look and feel
		        //add content, manage tests etc
		        //htmlReporter.config().setChartVisibilityOnOpen(true);
		        htmlReporter.config().setDocumentTitle("BCG API Automation Report");
		        htmlReporter.config().setReportName("Test Report");
		        //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		        htmlReporter.config().setTheme(Theme.DARK);
		        //htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
			 
			
			baseURI = "https://bookstore.toolsqa.com";

			usercreds.put("userName", "testuserajay119");
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
		
		//test.log(Status.INFO, "Used id generated: "+userId);
			
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
			
			//test.log(Status.INFO, "Used id generated: "+token);
	}

	@Test
	public void getUsersBooksDetails() {
		
		System.out.println("Inside books id.");
		test=report.createTest("Test cases 1 for getting user details");
		
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
			
		test.log(Status.INFO,"Getting valid response from server.");
	
		
	}
	
	
	@AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL,result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getTestName());
        }
        else {
            test.log(Status.SKIP, result.getTestName());
        }
    }
	
	

    @AfterTest
    public void tearDown() {
    	
        //to write or update test information to reporter
    	report.flush();
    }
	
	
}
