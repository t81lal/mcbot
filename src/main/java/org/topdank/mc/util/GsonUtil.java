package org.topdank.mc.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

public interface GsonUtil {
	Gson PRETTY_GSON = new GsonBuilder().setPrettyPrinting().create();
	JsonParser JSON_PARSER = new JsonParser();
}