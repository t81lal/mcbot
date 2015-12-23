package org.topdank.bot.auth;

import java.util.Map;

public interface AuthenticationService<T extends Session> {

	String getClientToken();

	T createSession(Map<String, Object> flags) throws IllegalArgumentException;
}