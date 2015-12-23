package org.topdank.bot.auth;

public class Response {

	private final int statusCode;

	public Response(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}
}