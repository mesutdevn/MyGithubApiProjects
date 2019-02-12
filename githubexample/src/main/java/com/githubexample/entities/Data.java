package com.githubexample.entities;

public class Data {
	private String repoName;
	private String userName;
	private String userLocation;
	private String userCompany;
	private Integer userContributions;
	
	public Data(String repoName, String userName, String userLocation, String userCompany, Integer userContributions) {
		super();
		this.repoName = repoName;
		this.userName = userName;
		this.userLocation = userLocation;
		this.userCompany = userCompany;
		this.userContributions = userContributions;
	}
	
	public String getRepoName() {
		return repoName;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserLocation() {
		return userLocation;
	}
	public String getUserCompany() {
		return userCompany;
	}
	public Integer getUserContributions() {
		return userContributions;
	}
	
	
	@Override
	public String toString() {
		return "repo: " + repoName + ", user: " + userName + ", location: " + userLocation + ", company: " + userCompany + ", contributions: " + userContributions;
	}
	
}
