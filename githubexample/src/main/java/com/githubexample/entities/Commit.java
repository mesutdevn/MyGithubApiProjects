package com.githubexample.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {
	
	@JsonProperty("sha")
	private String sha;
	
	@JsonProperty("committer")
	private Committer committer;

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public Committer getCommitter() {
		return committer;
	}

	public void setCommitter(Committer committer) {
		this.committer = committer;
	}

	@Override
	public String toString() {
		return "Commit [sha=" + sha + ", committer=" + committer + "]";
	}
	
}
