package com.nelsonjrodrigues.twitter.client.commands;

import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.FileUtils;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

@Component
public class BannerProvider extends DefaultBannerProvider {

	public String getBanner() {
		StringBuilder builder = new StringBuilder();

		builder.append(FileUtils.readBanner(BannerProvider.class, "banner.txt") + OsUtils.LINE_SEPARATOR);

		builder.append(getVersion()).append(OsUtils.LINE_SEPARATOR);
		builder.append(OsUtils.LINE_SEPARATOR);

		return builder.toString();
	};

	@Override
	public String getWelcomeMessage() {
		return super.getWelcomeMessage().replaceAll("Spring", "Twitter Sample Client").replace("hint", "help");
	}

	@Override
	public String getVersion() {
		return getClass().getPackage().getImplementationVersion();
	}
}
