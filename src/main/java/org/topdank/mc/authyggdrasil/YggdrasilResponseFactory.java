package org.topdank.mc.authyggdrasil;

import org.topdank.bot.auth.InternalServerResponse;
import org.topdank.bot.auth.Response;

public final class YggdrasilResponseFactory {

	public static Response createErrorResponse(InternalServerResponse resp) {
		// System.out.println("Got error: " + resp.getStatusCode());
		// System.out.println("Payload: " + resp.getPayload());
		// TODO: impl
		return resp;
	}
}