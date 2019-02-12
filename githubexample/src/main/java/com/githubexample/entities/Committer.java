package com.githubexample.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Committer {
	
	 @JsonProperty("id")
	 private String id;
	
	 @JsonProperty("login")
	 private String username;
	 
	 @JsonProperty("url")
	 private String url;
	 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Committer [id=" + id + ", username=" + username + ", url=" + url + "]";
	}
}
