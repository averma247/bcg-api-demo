package com.qa.rest.payload;

public class PostPayload {
	
	private String name;
	private String job;
	
	public String getName() {
		return name;
	}
	
	public String getJob() {
		return job;
	}
	
	public String toString() {
		 return String.format("Name:%s, Job:%s", name, job);
	}

}
