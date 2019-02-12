package com.githubexample.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubApi {
	
	@JsonProperty("repository_url")
	private String RepoUrl;
	
	@JsonProperty("organization_url")
	private String OrgUrl;

	public String getRepoUrl() {
		return RepoUrl;
	}

	public void setRepoUrl(String repoUrl) {
		RepoUrl = repoUrl;
	}

	public String getOrgUrl() {
		return OrgUrl;
	}

	public void setOrgUrl(String orgUrl) {
		OrgUrl = orgUrl;
	}
	
	
}
