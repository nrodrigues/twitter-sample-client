package com.nelsonjrodrigues.twitter.client.commands;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ShellConfig {

	@Bean
	public RestTemplate createRestTemplate() {
		return new RestTemplate();
	}
}
