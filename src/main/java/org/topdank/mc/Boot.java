package org.topdank.mc;

import static org.topdank.mc.authyggdrasil.YggdrasilConstants.FLAG_PASSWORD;
import static org.topdank.mc.authyggdrasil.YggdrasilConstants.FLAG_USERNAME;
import static org.topdank.mc.util.MapUtil.create;

import java.io.IOException;
import java.util.Map;

import org.topdank.bot.Protocol;
import org.topdank.bot.auth.AuthenticationException;
import org.topdank.bot.eventbus.impl.ConcurrentEventBus;
import org.topdank.bot.net.TcpClient;
import org.topdank.mc.authyggdrasil.YggdrasilAuthenticationService;
import org.topdank.mc.authyggdrasil.YggdrasilSession;
import org.topdank.mc.bot.MCClient;
import org.topdank.minenet.protocols.v47.Protocol47Impl;

public class Boot {

	public static void main(String[] cargs) throws AuthenticationException, IOException {
		YggdrasilAuthenticationService serv = new YggdrasilAuthenticationService();
		Map<String, Object> args = create(new Object[]{FLAG_USERNAME, cargs[0], FLAG_PASSWORD, cargs[1]});
		YggdrasilSession session = serv.createSession(args);
		session.login();

		String host = "localhost";
		int port = 25565;
		Protocol protocol = new Protocol47Impl();
		
		TcpClient<YggdrasilSession> client = new MCClient(session, host, port, protocol, new ConcurrentEventBus());
		client.connect();
	}
}