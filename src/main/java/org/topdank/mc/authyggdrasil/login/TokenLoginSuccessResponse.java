package org.topdank.mc.authyggdrasil.login;

import org.topdank.bot.auth.Response;

public class TokenLoginSuccessResponse extends Response {
	
	private String changedAccessToken;
	
	public TokenLoginSuccessResponse(String changedAccessToken) {
		super(200);
	}
	
	public String getChangedAccessToken() {
		return changedAccessToken;
	}
}