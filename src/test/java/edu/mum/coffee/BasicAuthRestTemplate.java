package edu.mum.coffee;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

public class BasicAuthRestTemplate extends RestTemplate {

	private String username;
	private String password;

	public BasicAuthRestTemplate(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		addAuthentication();
	}

	private void addAuthentication() {
		if (StringUtils.isEmpty(username)) {
			throw new RuntimeException("Username is mandatory for Basic Auth");
		}

		List<ClientHttpRequestInterceptor> interceptors = Collections.singletonList(new BasicAuthInterceptor(username, password));
		setRequestFactory(new InterceptingClientHttpRequestFactory(getRequestFactory(), interceptors));
	}
}

