package org.topdank.mc.authyggdrasil.logout;

import java.io.IOException;
import java.net.Proxy;

import org.topdank.bot.auth.InternalServerResponse;
import org.topdank.bot.auth.Request;
import org.topdank.bot.auth.Response;
import org.topdank.mc.authyggdrasil.YggdrasilConstants;
import org.topdank.mc.authyggdrasil.YggdrasilResponseFactory;
import org.topdank.mc.util.NetUtil;

public class PasswordLogoutRequest extends Request {
	
	private static final PasswordLogoutSuccessResponse INSTANCE = new PasswordLogoutSuccessResponse();
	
	public PasswordLogoutRequest(String username, String password) {
		super(YggdrasilConstants.SIGNOUT_SERVER_URL, "application/json", formatToJson(username, password));
	}
	
	@Override
	public Response response(Proxy proxy) throws IOException {
		InternalServerResponse resp = NetUtil.post(proxy, this);
		String payload = resp.getPayload();
		if ((payload == null) || payload.isEmpty())
			return INSTANCE;
		return YggdrasilResponseFactory.createErrorResponse(resp);
	}
	
	private static String formatToJson(String username, String password) {
		return "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
	}
}