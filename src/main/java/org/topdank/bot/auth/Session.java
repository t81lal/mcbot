package org.topdank.bot.auth;

public interface Session {

	boolean canLogIn();

	boolean canJoinServer();

	void setUsername(String username);

	void setPassword(String password);

	void setAuthenticatedToken(String accessToken);

	String getUserID();

	String getClientToken();

	String getAuthenticatedToken();

	void login() throws AuthenticationException;

	void logout();
}