package com.nelsonjrodrigues.twitter.client.commands;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

@Component
public class UsersControllers extends BaseController implements CommandMarker {

	@Autowired
	private RestOperations restOperations;

	@Autowired
	private LoginController loginController;

	@CliAvailabilityIndicator({ "list users", "create user", "user details", "followers", "following", "start following", "stop following" })
	public boolean commandsAvailable() {
		return loginController.loginMade();
	}

	@CliCommand(value = "list users", help = "List all users")
	public void listUsers() {
		@SuppressWarnings("unchecked")
		List<Map<String, String>> users = restOperations.getForObject(getBaseUrl() + "/users?token={token}", List.class,
				loginController.getApiToken());

		for (Map<String, String> user : users) {
			System.out.println(user);
		}
	}

	@CliCommand(value = "create user", help = "Create new user")
	public String createUser(@CliOption(key = "username", mandatory = true, help = "Username to create") final String username) {
		restOperations.postForObject(getBaseUrl() + "users?token={token}", Collections.singletonMap("username", username), Map.class,
				loginController.getApiToken());

		return "Created user " + username;
	}

	@CliCommand(value = "user details", help = "Get current user details")
	public String userDetails() {
		@SuppressWarnings("unchecked")
		Map<String, String> user = restOperations.getForObject(getBaseUrl() + "/users/{username}?token={token}", Map.class,
				loginController.getUsername(), loginController.getApiToken());

		return user.toString();
	}

	@CliCommand(value = "followers", help = "Get current user followers")
	public void followers() {

		@SuppressWarnings("unchecked")
		List<Map<String, String>> users = restOperations.getForObject(getBaseUrl() + "/users/{username}/followers?token={token}", List.class,
				loginController.getUsername(), loginController.getApiToken());

		for (Map<String, String> user : users) {
			System.out.println(user);
		}

	}

	@CliCommand(value = "following", help = "Get current user following")
	public void following() {

		@SuppressWarnings("unchecked")
		List<Map<String, String>> users = restOperations.getForObject(getBaseUrl() + "/users/{username}/following?token={token}", List.class,
				loginController.getUsername(), loginController.getApiToken());

		for (Map<String, String> user : users) {
			System.out.println(user);
		}

	}

	@CliCommand(value = "start following", help = "Start following a user")
	public String follow(@CliOption(key = { "username" }, mandatory = true, help = "The username to start following") final String username) {
		restOperations.postForObject(getBaseUrl() + "/users/{follower}/following?token={token}", Collections.singletonMap("username", username),
				Map.class, loginController.getUsername(), loginController.getApiToken());

		return "Started following user " + username;
	}

	@CliCommand(value = "stop following", help = "Stop following a user")
	public String unfollow(@CliOption(key = { "username" }, mandatory = true, help = "The username to stop following") final String username) {
		restOperations.delete(getBaseUrl() + "/users/{follower}/following/{username}?token={token}", loginController.getUsername(), username,
				loginController.getApiToken());

		return "Stopped following user " + username;

	}
}
