package com.nelsonjrodrigues.twitter.client.commands;

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
public class TweetController extends BaseController implements CommandMarker {

	@Autowired
	private RestOperations restOperations;

	@Autowired
	private LoginController loginController;

	@CliAvailabilityIndicator({ "tweet", "timeline" })
	public boolean commandsAvailable() {
		return loginController.loginMade();
	}

	@CliCommand(value = "tweet", help = "Create a new tweet")
	public String tweet(@CliOption(key = "tweet", mandatory = true, help = "Tweet text") final String text) {
		restOperations.postForObject(getBaseUrl() + "/tweets/{username}?token={token}", text, Map.class, loginController.getUsername(),
				loginController.getApiToken());

		return "Tweeted";
	}

	@CliCommand(value = "timeline", help = "View user timeline")
	public void timeline() {
		@SuppressWarnings("unchecked")
		List<Map<String, String>> result = restOperations.getForObject(getBaseUrl() + "/tweets/{username}?token={token}", List.class,
				loginController.getUsername(), loginController.getApiToken());

		for (Map<String, String> tweet : result) {
			System.out.println(tweet);
		}
	}

}
