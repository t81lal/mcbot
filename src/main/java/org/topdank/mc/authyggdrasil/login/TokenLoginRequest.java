package org.topdank.mc.authyggdrasil.login;

import java.io.IOException;
import java.net.Proxy;

import org.topdank.bot.auth.InternalServerResponse;
import org.topdank.bot.auth.Profile;
import org.topdank.bot.auth.Request;
import org.topdank.bot.auth.Response;
import org.topdank.mc.authyggdrasil.YggdrasilConstants;
import org.topdank.mc.authyggdrasil.YggdrasilResponseFactory;
import org.topdank.mc.util.GsonUtil;
import org.topdank.mc.util.NetUtil;

import com.google.gson.JsonObject;

public class TokenLoginRequest extends Request {
	
	public TokenLoginRequest(String accessToken, String clientToken, Profile selectedProfile) {
		super(YggdrasilConstants.TOKEN_LOGIN_SERVER_URL, "application/json", formatToJson(accessToken, clientToken, selectedProfile));
	}
	
	@Override
	public Response response(Proxy proxy) throws IOException {
		InternalServerResponse resp = NetUtil.post(proxy, this);
		if (resp.getStatusCode() == 200) {
			JsonObject root = GsonUtil.JSON_PARSER.parse(resp.getPayload()).getAsJsonObject();
			return new TokenLoginSuccessResponse(root.get("accessToken").getAsString());
		} else {
			return YggdrasilResponseFactory.createErrorResponse(resp);
		}
	}
	
	private static String formatToJson(String accessToken, String clientToken, Profile selectedProfile) {
		return "{\"accessToken\":\"" + accessToken + "\",\"clientToken\":\"" + clientToken + "\",\"selectedProfile\":\"" + selectedProfile + "\"}";
	}
}