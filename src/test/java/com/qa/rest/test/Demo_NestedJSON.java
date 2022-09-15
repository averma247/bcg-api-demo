package com.qa.rest.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.qa.rest.payload.GetResponseDataPayload;
import com.qa.rest.payload.PersonalData;

public class Demo_NestedJSON {
	
	GetResponseDataPayload getjsonpayload;
	
	@BeforeTest
	public void readGETPayload() throws FileNotFoundException {
		 BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/Resources/getresponsepayload.json"));
		 getjsonpayload=new Gson().fromJson(br, GetResponseDataPayload.class); 
	}
	
	@Test
	public void readJSONPayload() {
		PersonalData data[] = getjsonpayload.getData();
		
		for(PersonalData details: data)
		System.out.println(details.toString());
		
		System.out.println(getjsonpayload.getTotal());
	}

}
