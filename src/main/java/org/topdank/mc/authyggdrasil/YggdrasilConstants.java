package org.topdank.mc.authyggdrasil;

import static org.topdank.mc.util.NetUtil.constantURL;

import java.net.URL;

public interface YggdrasilConstants {
	String FLAG_PROXY = "proxy";
	String FLAG_USERNAME = "username";
	String FLAG_PASSWORD = "password";
	String FLAG_ACCESS_TOKEN = "accessToken";
	String FLAG_CLIENT_TOKEN = "clientToken";
	String FLAG_SELECTED_PROFILE = "selectedProfile";
	
	String MOJANG_AUTH_SERVER_BASE_URL = " https://authserver.mojang.com";
	URL PASSWORD_LOGIN_SERVER_URL = constantURL(MOJANG_AUTH_SERVER_BASE_URL + "/authenticate");
	URL TOKEN_LOGIN_SERVER_URL = constantURL(MOJANG_AUTH_SERVER_BASE_URL + "/refresh");
	URL VALIDATE_SERVER_URL = constantURL(MOJANG_AUTH_SERVER_BASE_URL + "/validate");
	URL SIGNOUT_SERVER_URL = constantURL(MOJANG_AUTH_SERVER_BASE_URL + "/signout");
	URL INVALIDATE_SERVER_URL = constantURL(MOJANG_AUTH_SERVER_BASE_URL + "/invalidate");
	
	String MINECRAFT_SERVER_SERVER_BASE_URL = "https://sessionserver.mojang.com/session/minecraft";
	URL JOIN_MINECRAFT_SERVER_URL = constantURL(MINECRAFT_SERVER_SERVER_BASE_URL + "/join");
	URL CHECK_JOIN_MINECRAFT_SERVER_URL = constantURL(MINECRAFT_SERVER_SERVER_BASE_URL + "/hasJoined");
}