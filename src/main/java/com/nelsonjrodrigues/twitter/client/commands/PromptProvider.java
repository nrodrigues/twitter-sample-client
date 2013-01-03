package com.nelsonjrodrigues.twitter.client.commands;

import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.stereotype.Component;

@Component
public class PromptProvider extends DefaultPromptProvider {

	@Override
	public String getPrompt() {
		return "twitter-shell>";
	}

	@Override
	public String name() {
		return "twitter sample client provider";
	}
}
