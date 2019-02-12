package com.githubexample.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repo implements Comparable<Repo>{
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("private")
	private boolean _private;
	
	@JsonProperty("forks_count")
	private int forkCount;
	
	@JsonProperty("commits_url")
	private String commitUrl;
	
	@JsonProperty("contributors_url")
	private String contributorsUrl;
	
	@JsonProperty("owner")
	private Owner owner;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean is_private() {
		return _private;
	}

	public void set_private(boolean _private) {
		this._private = _private;
	}

	public int getForkCount() {
		return forkCount;
	}

	public void setForkCount(int forkCount) {
		this.forkCount = forkCount;
	}

	public String getCommitUrl() {
		return commitUrl;
	}

	public void setCommitUrl(String commitUrl) {
		this.commitUrl = commitUrl;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getContributorsUrl() {
		return contributorsUrl;
	}

	public void setContributorsUrl(String contributorsUrl) {
		this.contributorsUrl = contributorsUrl;
	}

	@Override
	public String toString() {
		return "Repo [id=" + id + ", name=" + name + ", _private=" + _private + ", forkCount=" + forkCount
				+ ", commitUrl=" + commitUrl + "]";
	}

	@Override
	public int compareTo(Repo o) { //pozitif deger dondugunde karsilastirilan deger daha buyuk 0 ise esit negatif ise kücük oldugunu belirtir.
		int compareQuantity = ((Repo) o).getForkCount();
		return compareQuantity - this.forkCount;
	}

    
}
