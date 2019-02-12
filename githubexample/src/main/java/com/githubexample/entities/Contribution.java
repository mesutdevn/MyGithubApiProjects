package com.githubexample.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contribution implements Comparable<Contribution>{
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("login")
	private String username;
	
	@JsonProperty("contributions")
	private int contributions;
	
	@JsonProperty("url")
	private String userUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getContributions() {
		return contributions;
	}

	public void setContributions(int contributions) {
		this.contributions = contributions;
	}

	public String getUserUrl() {
		return userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	@Override
	public String toString() {
		return "Contribution [id=" + id + ", username=" + username + ", contributions=" + contributions + ", userUrl="
				+ userUrl + "]";
	}
	
	@Override
	public int compareTo(Contribution o) {
		int compareQuantity = o.getContributions();
		return compareQuantity - this.contributions;
	}
}
