package org.topdank.bot.auth;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;

public abstract class Request {

	private final URL url;
	private final String contentType;
	private final String payload;

	public Request(URL url, String contentType, String payload) {
		this.url = url;
		this.contentType = contentType;
		this.payload = payload;
	}

	public abstract Response response(Proxy proxy) throws IOException;

	public URL getURL() {
		return url;
	}

	public String getContentType() {
		return contentType;
	}

	@Override
	public String toString() {
		return payload;
	}
}