package com.githubexample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.githubexample.Printer.TextPrinter;
import com.githubexample.Tools.Helper;
import com.githubexample.entities.Contribution;
import com.githubexample.entities.Data;
import com.githubexample.entities.GithubApi;
import com.githubexample.entities.Organization;
import com.githubexample.entities.Repo;
import com.githubexample.entities.User;

public class Runner {
	private final Logger log = LoggerFactory.getLogger(Runner.class);
	
	HttpEntity<String> request = new HttpEntity<String>(Helper.AddHeader("mesutdevn", "password or token"));//API rate limit exceeded for 95.9.245.227. (But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.)
	
	private RestTemplate restTemplate = null;
	
	String apiUrl = "https://api.github.com";//api
	int requestedNumberOfRepo = 5; //istenilen repo sayisi
	int requestedNumberOfUser = 10; //istenilen kullanici sayisi
	
	public void run() throws Exception {
		List<Data> result = new ArrayList<>();
		
		GithubApi github = this.getGithubApi(apiUrl);
		
		Map<String, String> orgParameters = new HashMap<>();
		orgParameters.put("org","apache");
		
		String organizationUrl = Helper.UriFormat(github.getOrgUrl(), orgParameters);
		
		Organization org = this.getOrganization(organizationUrl);
		
		Repo[] repos = this.getRepos(org.getReposUrl());
		
		Arrays.sort(repos); //compareTo method una gore; büyükten küçüğe doğru siralama
		
		int requestedNumberOfRepoFinal = requestedNumberOfRepo > repos.length ? repos.length:requestedNumberOfRepo; 
		
		for (int i = 0; i < requestedNumberOfRepoFinal; i++) {
			Repo nextRepo = repos[i];
			
			Contribution[] contributions = this.getContributions(nextRepo.getContributorsUrl(), nextRepo.getOwner().getName(), nextRepo.getName());
			
			Arrays.sort(contributions); //compareTo method una gore; büyükten küçüğe doğru siralama
			
			int requestedNumberOfContributionFinal = requestedNumberOfUser > contributions.length ? contributions.length:requestedNumberOfUser;
			
			for (int j = 0; j < requestedNumberOfContributionFinal; j++) {
				Contribution nextContribution = contributions[j];
				User userInfo = this.getUser(nextContribution.getUserUrl());
				
				String RepoName = nextRepo.getName();
				String Username = nextContribution.getUsername();
				String UserLocation = userInfo.getLocation()==null?"**No Data**":userInfo.getLocation();
				String UserCompany = userInfo.getCompany()==null?"**No Data**":userInfo.getCompany();
				int ContributionCount = nextContribution.getContributions();
				
				Data data = new Data(RepoName, Username, UserLocation, UserCompany, ContributionCount);
				/*
				if(result==null) {
					result = new Data[requestedNumberOfContributionFinal * requestedNumberOfRepoFinal];
				}
				result[i * j] = data;
				*/
				result.add(data);
			}
			
		}
		
		TextPrinter text = new TextPrinter();
		text.SetData(result);
		text.Print();
	}

	public void loadRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public GithubApi getGithubApi(String apiUrl) throws Exception {
		ResponseEntity<GithubApi> api = restTemplate.exchange(apiUrl, HttpMethod.GET, request, GithubApi.class);
		if(api.getStatusCode() == HttpStatus.OK) {
			GithubApi ResultApi = api.getBody();
			if(ResultApi == null) {
				throw new Exception("Api bulunamadı! Açıklama: " + apiUrl );
			}
			log.info(String.format("%s adresinden Api bilgisi bulundu.\n", apiUrl));
			
			return ResultApi;
		}
		return null;
	}
	
	public Organization getOrganization(String OrganizationUrl) throws Exception {
		ResponseEntity<Organization> organization = restTemplate.exchange(OrganizationUrl, HttpMethod.GET, request, Organization.class);
		if(organization.getStatusCode() == HttpStatus.OK) {
			Organization ResultOrganization = organization.getBody();
			if(ResultOrganization == null) {
				throw new Exception("Organization bulunamadi! Aciklama: " + OrganizationUrl );
			}
			log.info(String.format("%s adresinden Organization bilgisi bulundu.\n", OrganizationUrl));
			
			return ResultOrganization;
		}
		return null;
	}
	
	public Repo[] getRepos(String orgRepoUrl) throws Exception {
		ResponseEntity<Repo[]> repos = restTemplate.exchange(orgRepoUrl, HttpMethod.GET, request, Repo[].class);
		if(repos.getStatusCode() == HttpStatus.OK) {
			Repo[] ResultRepos = repos.getBody();
			if(ResultRepos==null || ResultRepos.length == 0) {
				throw new Exception("Repo bulunamadi! Aciklama: " + orgRepoUrl);
			}
			log.info(String.format("Toplam %s adet Repository bulundu.\n", ResultRepos.length));
			
			return ResultRepos;
		}
		return null;
	}
	
	/*private Commit[] getCommits(String CommitUrl) throws Exception {
		ResponseEntity<Commit[]> commits = restTemplate.exchange(CommitUrl, HttpMethod.GET, request, Commit[].class);
		if(commits.getStatusCode() == HttpStatus.OK) {
			Commit[] ResultCommits = commits.getBody();
			if(ResultCommits == null || ResultCommits.length == 0) {
				throw new Exception("Commit bulunamadı!");
			}
			log.info(String.format("Toplam %s adet Commit bulundu.\n", ResultCommits.length));
			
			return ResultCommits;
		}
		return null;
	}*/
	
	private Contribution[] getContributions(String contributors_url, String organizationName, String RepoName) throws Exception {
		ResponseEntity<Contribution[]> contributions = restTemplate.exchange(contributors_url, HttpMethod.GET, request, Contribution[].class);
		if(contributions.getStatusCode() == HttpStatus.OK) {
			Contribution[] ResultContribution = contributions.getBody();
			if(ResultContribution==null || ResultContribution.length == 0) {
				throw new Exception("Repo bulunamadi! Aciklama: " + contributors_url + ", " + organizationName + ", " + RepoName);
			}
			log.info(String.format("Toplam %s adet Contribution bulundu.\n", ResultContribution.length));
			
			return ResultContribution;
		}
		return null;
	}
	
	/*private Contribution getContributionByUsername(Contribution[] contributions, String username) {
		for (Contribution contribution : contributions) {
			if(username.equals(contribution.getUsername())) {
				return contribution;
			}
		}
		return null;
	}*/
	
	private User getUser(String UserUrl) throws Exception {
		ResponseEntity<User> user = restTemplate.exchange(UserUrl, HttpMethod.GET, request, User.class);
		if(user.getStatusCode() == HttpStatus.OK) {
			User ResultUser = user.getBody();
			if(ResultUser == null) {
				throw new Exception("User bulunamadı! Aciklama: " + UserUrl );
			}
			log.info(String.format("%s adresin User bilgisi bulundu.\n", UserUrl));
			
			return ResultUser;
		}
		return null;
	}
	
}
