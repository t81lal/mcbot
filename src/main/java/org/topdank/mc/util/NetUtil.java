package org.topdank.mc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import org.topdank.bot.auth.InternalServerResponse;
import org.topdank.bot.auth.Request;

public final class NetUtil {

	protected static HttpURLConnection createUrlConnection(URL url, Proxy proxy) throws IOException {
		if (proxy == null)
			proxy = Proxy.NO_PROXY;
		HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
		connection.setConnectTimeout(15000);
		connection.setReadTimeout(15000);
		connection.setUseCaches(false);
		return connection;
	}

	public static InternalServerResponse post(Proxy proxy, Request request) throws IOException {
		if (proxy == null)
			proxy = Proxy.NO_PROXY;
		// System.out.println("Performing " + request.getClass().getSimpleName());
		HttpURLConnection connection = createUrlConnection(request.getURL(), proxy);
		byte[] postAsBytes = request.toString().getBytes("UTF-8");

		connection.setRequestProperty("Content-Type", request.getContentType() + "; charset=utf-8");
		connection.setRequestProperty("Content-Length", String.valueOf(postAsBytes.length));
		connection.setDoOutput(true);

		OutputStream outputStream = null;
		try {
			outputStream = connection.getOutputStream();
			outputStream.write(postAsBytes);
			outputStream.flush();
		} finally {
			closeQuietly(outputStream);
		}
		// System.out.println("resp: " + connection.getResponseCode() + " for
		// request " + request.getClass().getSimpleName());
		InputStream inputStream = null;
		try {
			inputStream = connection.getInputStream();
			String result = readFromStream(inputStream);
			return new InternalServerResponse(connection.getResponseCode(), result);
		} catch (IOException e) {
			closeQuietly(inputStream);
			inputStream = connection.getErrorStream();
			if (inputStream != null) {
				String result = readFromStream(inputStream);
				return new InternalServerResponse(connection.getResponseCode(), result);
			}
			throw e;
		} finally {
			closeQuietly(inputStream);
		}
	}

	public static InternalServerResponse get(Request request, Proxy proxy) throws IOException {
		HttpURLConnection connection = createUrlConnection(request.getURL(), proxy);

		InputStream inputStream = null;
		try {
			inputStream = connection.getInputStream();
			String result = readFromStream(inputStream);
			return new InternalServerResponse(connection.getResponseCode(), result);
		} catch (IOException e) {
			e.printStackTrace();
			closeQuietly(inputStream);
			inputStream = connection.getErrorStream();
			if (inputStream != null) {
				String result = readFromStream(inputStream);
				return new InternalServerResponse(connection.getResponseCode(), result);
			}
			throw e;
		} finally {
			closeQuietly(inputStream);
		}
	}

	protected static String readFromStream(InputStream stream) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				if (sb.length() > 0)
					sb.append('\n');
				sb.append(line);
			}
			if (sb.toString().trim().isEmpty())
				return null;
			String s = sb.toString();
			return s;
		}
	}

	public static void closeQuietly(InputStream stream) {
		try {
			if (stream != null)
				stream.close();
		} catch (IOException e) {
		}
	}

	public static void closeQuietly(OutputStream stream) {
		try {
			stream.close();
		} catch (IOException e) {
		}
	}

	public static URL constantURL(String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException ex) {
			throw new Error("Couldn't create constant for " + url, ex);
		}
	}
}