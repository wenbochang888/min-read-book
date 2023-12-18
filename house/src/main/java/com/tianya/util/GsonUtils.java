package com.tianya.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

/**
 * @author changwenbo
 * @date 2022/6/18 19:18
 */
public class GsonUtils {
	private static final JsonParser JSON_PARSER = new JsonParser();

	public static final Gson DEFAULT_GSON = new Gson();

	private static Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
			.disableHtmlEscaping()
			.setVersion(1.0)
			.create();

	private static Gson PRETTY_PRINTING_GSON = new GsonBuilder().setPrettyPrinting().create();

	public static String toJson(Object src) {
		try {
			if (src instanceof String){
				return (String)src;
			}
			return gson.toJson(src);
		}catch (Exception e) {
			return null;
		}
	}

	public static <T> T fromJson(String jsonStr, Class<T> clazz) {
		try {
			return gson.fromJson(jsonStr, clazz);
		}catch (Exception e) {
			return null;
		}
	}

	public static <T> T fromJson(JsonElement jsonElement, Class<T> clazz) {
		try {
			return gson.fromJson(jsonElement, clazz);
		}catch (Exception e) {
			return null;
		}
	}

	public static <T> T fromJson(String json, TypeToken<T> type) {
		try {
			return gson.fromJson(json, type.getType());
		}catch (Exception e) {
			return null;
		}
	}

	public static JsonElement toJsonTree(Object src) {
		try {
			return gson.toJsonTree(src);
		}catch (Exception e) {
			return null;
		}
	}

	public static JsonObject toJsonObject(Object src) {
		try {
			return gson.toJsonTree(src).getAsJsonObject();
		}catch (Exception e) {
			return null;
		}
	}

	public static JsonObject parse(String json) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		try {
			return (JsonObject) JSON_PARSER.parse(json);
		}catch (Exception e){
		}
		return null;
	}

	public static JsonElement parseElement(String json) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		try {
			return JSON_PARSER.parse(json);
		}catch (Exception e){
			return null;
		}
	}

	public static JsonObject parseWithNullable(String json) {
		if (StringUtils.isBlank(json)) {
			return new JsonObject();
		}
		try {
			return (JsonObject) JSON_PARSER.parse(json);
		}catch (Exception e){
		}
		return new JsonObject();
	}

	public static boolean checkIsJsonArray(String json) {
		if (StringUtils.isBlank(json)) {
			return false;
		}
		JsonElement element;
		try {
			element = JSON_PARSER.parse(json);
		}catch (Exception e){
			return false;
		}
		return element!=null && element.isJsonArray();
	}

	public static boolean checkIsJsonObject(String json) {
		if (StringUtils.isBlank(json)) {
			return false;
		}
		JsonElement element;
		try {
			element = JSON_PARSER.parse(json);
		} catch (Exception e) {
			return false;
		}
		return element != null && element.isJsonObject();
	}

	public static boolean hasKey(JsonObject jsonObject, String key) {
		return null != jsonObject && jsonObject.has(key);
	}

	public static boolean hasKeyValue(JsonObject jsonObject, String key) {
		return null != jsonObject && jsonObject.has(key) && !jsonObject.get(key).isJsonNull();
	}

	public static String getValue(JsonObject jsonObject, String key) {
		if(hasKey(jsonObject,key)){
			return jsonObject.get(key).getAsString();
		} else {
			return null;
		}
	}

	public static JsonObject getAsJsonObject(JsonObject jsonObject, String key) {
		if(hasKey(jsonObject,key)){
			return jsonObject.get(key).getAsJsonObject();
		} else {
			return new JsonObject();
		}
	}

	public static String getValueOrDefault(JsonObject jsonObject, String key, String defaultValue) {
		return hasKey(jsonObject, key) ? jsonObject.get(key).getAsString() : defaultValue;
	}

	public static JsonObject getJsonObject(JsonObject jsonObject, String key) {
		return hasKey(jsonObject, key) ? jsonObject.get(key).getAsJsonObject() : null;
	}

	public static String toPrettyFormat(String json) {
		return PRETTY_PRINTING_GSON.toJson(parseWithNullable(json));
	}

	public static String toPrettyFormat(JsonObject jsonObject) {
		return PRETTY_PRINTING_GSON.toJson(jsonObject);
	}

	public static boolean getValueOrDefault(JsonObject jsonObject, String key, boolean defaultValue) {
		return hasKeyValue(jsonObject, key) ? jsonObject.get(key).getAsBoolean() : defaultValue;
	}
}
