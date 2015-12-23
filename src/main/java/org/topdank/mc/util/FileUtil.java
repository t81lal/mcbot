package org.topdank.mc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class FileUtil {

	public static String readText(String path) {
		URL url = FileUtil.class.getResource(path);
		try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = br.readLine()) != null) {
				sb.append(line);
				sb.append('\n');
			}
			return sb.toString();
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}