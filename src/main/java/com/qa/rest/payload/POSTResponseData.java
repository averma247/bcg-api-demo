package com.qa.rest.payload;

public class POSTResponseData {
	
	private String name;
	private String job;
	private String id;
	private String createdAt;
	
	
	public String getName() {
		return name;		
	}
	
	public String getJob() {
		return job;		
	}
	
	public String getId() {
		return id;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}
	
	public String toString() {
		return String.format("Name:%s, Job:%s, Id:%s, createdAt:%s", name,job,id,createdAt);
	}

}
