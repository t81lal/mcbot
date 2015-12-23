package org.topdank.bot.auth;

public class InternalServerResponse extends Response {

	protected String payload;

	public InternalServerResponse(int statusCode, String payload) {
		super(statusCode);
		this.payload = payload;
	}

	public String getPayload() {
		return payload;
	}
}