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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PasswordLoginRequest extends Request {

	public PasswordLoginRequest(String username, String password, String clientToken) {
		super(YggdrasilConstants.PASSWORD_LOGIN_SERVER_URL, "application/json", formatToJson(username, password, clientToken));
	}

	@Override
	public Response response(Proxy proxy) throws IOException {
		InternalServerResponse resp = NetUtil.post(proxy, this);
		if (resp.getStatusCode() == 200) {
			JsonObject root = GsonUtil.JSON_PARSER.parse(resp.getPayload()).getAsJsonObject();
			String accessToken = root.get("accessToken").getAsString();
			JsonElement jsonSP = root.get("selectedProfile");
			Profile selectedProfile = GsonUtil.PRETTY_GSON.fromJson(jsonSP.getAsJsonObject(), Profile.class);
			return new PasswordLoginSuccessResponse(accessToken, selectedProfile);
		} else {
			return YggdrasilResponseFactory.createErrorResponse(resp);
		}
	}

	private static String formatToJson(String username, String password, String clientToken) {
		return String.format("{\"password\":\"%s\",\"agent\":{\"name\":\"Minecraft\",\"version\":1},\"clientToken\":\"%s\",\"username\":\"%s\"}", password, clientToken, username);
	}
}