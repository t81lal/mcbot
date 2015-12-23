package org.topdank.mc.authyggdrasil;

import java.net.Proxy;
import java.util.Map;
import java.util.UUID;

import org.topdank.bot.auth.AuthenticationService;
import org.topdank.bot.auth.Profile;

public class YggdrasilAuthenticationService implements AuthenticationService<YggdrasilSession> {

	public static final UUID CLIENT_TOKEN = UUID.randomUUID();

	@Override
	public String getClientToken() {
		return CLIENT_TOKEN.toString();
	}

	@Override
	public YggdrasilSession createSession(Map<String, Object> flags) throws IllegalArgumentException {
		Proxy proxy = (Proxy) flags.get(YggdrasilConstants.FLAG_PROXY);

		boolean clientToken = flags.containsKey(YggdrasilConstants.FLAG_CLIENT_TOKEN);
		boolean accessToken = flags.containsKey(YggdrasilConstants.FLAG_ACCESS_TOKEN);
		boolean selectedProfile = flags.containsKey(YggdrasilConstants.FLAG_SELECTED_PROFILE);
		boolean user = flags.containsKey(YggdrasilConstants.FLAG_USERNAME);
		boolean pass = flags.containsKey(YggdrasilConstants.FLAG_PASSWORD);

		YggdrasilSession session = null;

		if (clientToken) {
			if (user && pass) {
				session = new YggdrasilSession(this, proxy, (String) flags.get(YggdrasilConstants.FLAG_USERNAME), (String) flags.get(YggdrasilConstants.FLAG_PASSWORD), (String) flags.get(YggdrasilConstants.FLAG_CLIENT_TOKEN));
			} else if (accessToken && selectedProfile) {
				session = new YggdrasilSession(this, proxy, (Profile) flags.get(YggdrasilConstants.FLAG_SELECTED_PROFILE), (String) flags.get(YggdrasilConstants.FLAG_ACCESS_TOKEN), (String) flags.get(YggdrasilConstants.FLAG_CLIENT_TOKEN));
			}
		} else {
			if (user && pass) {
				session = new YggdrasilSession(this, proxy, (String) flags.get(YggdrasilConstants.FLAG_USERNAME), (String) flags.get(YggdrasilConstants.FLAG_PASSWORD));
			}
		}

		if (session == null)
			throw new IllegalArgumentException("Not enough args to create session");

		return session;
	}
}