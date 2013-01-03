package com.nelsonjrodrigues.twitter.client.commands;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;

@Component
public class LoginController extends BaseController implements CommandMarker {

	@Autowired
	private RestOperations restOperations;

	private String username;
	private String apiToken;

	public String getApiToken() {
		return apiToken;
	}

	public String getUsername() {
		return username;
	}

	public boolean loginMade() {
		return StringUtils.hasText(apiToken);
	}

	@CliCommand(value = "login", help = "Login to the Twitter Sample app")
	public String login(@CliOption(key = { "username" }, mandatory = true, help = "The username to login with") final String username) {

		@SuppressWarnings("unchecked")
		Map<String, String> response = restOperations.getForObject(getBaseUrl() + "/login/{username}", Map.class, username);

		this.username = username;
		this.apiToken = response.get("id");

		return "The username is " + username + " and apiToken is " + apiToken;
	}
}
