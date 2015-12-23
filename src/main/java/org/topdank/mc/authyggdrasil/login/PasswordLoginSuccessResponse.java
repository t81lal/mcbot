package org.topdank.mc.authyggdrasil.login;

import org.topdank.bot.auth.Profile;
import org.topdank.bot.auth.Response;

public class PasswordLoginSuccessResponse extends Response {
	
	private final String accessToken;
	private final Profile selectedProfile;
	
	public PasswordLoginSuccessResponse(String accessToken, Profile selectedProfile) {
		super(200);
		this.accessToken = accessToken;
		this.selectedProfile = selectedProfile;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public Profile getSelectedProfile() {
		return selectedProfile;
	}
}