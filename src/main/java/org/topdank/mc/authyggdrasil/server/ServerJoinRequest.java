package org.topdank.mc.authyggdrasil.server;

import java.io.IOException;
import java.net.Proxy;

import org.topdank.bot.auth.InternalServerResponse;
import org.topdank.bot.auth.Request;
import org.topdank.bot.auth.Response;
import org.topdank.mc.authyggdrasil.YggdrasilConstants;
import org.topdank.mc.authyggdrasil.YggdrasilResponseFactory;
import org.topdank.mc.util.NetUtil;

public class ServerJoinRequest extends Request {
	
	private static final ServerJoinSuccessResponse INSTANCE = new ServerJoinSuccessResponse();
	
	public ServerJoinRequest(String accessToken, String selectedProfileId, String serverId) {
		super(YggdrasilConstants.JOIN_MINECRAFT_SERVER_URL, "application/json", formatToJson(accessToken, selectedProfileId, serverId));
	}
	
	@Override
	public Response response(Proxy proxy) throws IOException {
		InternalServerResponse response = NetUtil.post(proxy, this);
		String payload = response.getPayload();
		if ((payload == null) || payload.isEmpty())
			return INSTANCE;
		return YggdrasilResponseFactory.createErrorResponse(response);
	}
	
	private static String formatToJson(String accessToken, String selectedProfileId, String serverId) {
		return "{\"accessToken\":\"" + accessToken + "\",\"selectedProfile\":\"" + selectedProfileId + "\",\"serverId\":\"" + serverId + "\"}";
	}
}