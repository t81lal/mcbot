package org.topdank.mc.authyggdrasil.logout;

import java.io.IOException;
import java.net.Proxy;

import org.topdank.bot.auth.InternalServerResponse;
import org.topdank.bot.auth.Request;
import org.topdank.bot.auth.Response;
import org.topdank.mc.authyggdrasil.YggdrasilConstants;
import org.topdank.mc.authyggdrasil.YggdrasilResponseFactory;
import org.topdank.mc.util.NetUtil;

public class TokenLogoutRequest extends Request {
	
	private static final TokenLogoutSuccessResponse INSTANCE = new TokenLogoutSuccessResponse();
	
	public TokenLogoutRequest(String accessToken, String clientToken) {
		super(YggdrasilConstants.INVALIDATE_SERVER_URL, "application/json", formatToJson(accessToken, clientToken));
	}
	
	@Override
	public Response response(Proxy proxy) throws IOException {
		InternalServerResponse resp = NetUtil.post(proxy, this);
		String payload = resp.getPayload();
		if ((payload == null) || payload.isEmpty())
			return INSTANCE;
		return YggdrasilResponseFactory.createErrorResponse(resp);
	}
	
	private static String formatToJson(String accessToken, String clientToken) {
		return "{\"accessToken\":\"" + accessToken + "\", \"clientToken\":\"" + clientToken + "\"}";
	}
}