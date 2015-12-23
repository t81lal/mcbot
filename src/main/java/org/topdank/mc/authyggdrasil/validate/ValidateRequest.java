package org.topdank.mc.authyggdrasil.validate;

import java.io.IOException;
import java.net.Proxy;

import org.topdank.bot.auth.InternalServerResponse;
import org.topdank.bot.auth.Request;
import org.topdank.bot.auth.Response;
import org.topdank.mc.authyggdrasil.YggdrasilConstants;
import org.topdank.mc.authyggdrasil.YggdrasilResponseFactory;
import org.topdank.mc.util.NetUtil;

public class ValidateRequest extends Request {
	
	private static final ValidateSuccessResponse INSTANCE = new ValidateSuccessResponse();
	
	public ValidateRequest(String accessToken) {
		super(YggdrasilConstants.VALIDATE_SERVER_URL, "application/json", formatToJson(accessToken));
	}
	
	@Override
	public Response response(Proxy proxy) throws IOException {
		InternalServerResponse resp = NetUtil.post(proxy, this);
		String payload = resp.getPayload();
		if ((payload == null) || payload.isEmpty())
			return INSTANCE;
		return YggdrasilResponseFactory.createErrorResponse(resp);
	}
	
	private static String formatToJson(String accessToken) {
		return "{\"accessToken\":\"" + accessToken + "\"}";
	}
}