package com.githubexample.Tools;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriTemplate;

public class Helper {
	
	public static HttpHeaders AddHeader(String username, String password) {
		String plainCreds = username + ":" + password;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		
		return headers;
	}
	
	public static String UriFormat(String url, Map<String, ?> uriVariables) {
		UriTemplate uriTemplate = new UriTemplate(url);
		return uriTemplate.expand(uriVariables).toString();
	}
}
