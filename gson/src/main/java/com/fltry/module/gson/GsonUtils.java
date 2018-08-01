package com.fltry.module.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GsonUtils {

	public static Object getInstanceByJson(Class<?> clazz, String json) {
		Object obj = null;
		Gson gson = new Gson();
		obj = gson.fromJson(json, clazz);
		return obj;
	}

	public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
		Gson gson = new Gson();
		T result = gson.fromJson(jsonData, type);
		return result;
	}

	public static <T> T parseJsonWithGson(String jsonData, Class<T> type, String timeType) {
		Gson gson = new GsonBuilder().setDateFormat(timeType).create();
		T result = gson.fromJson(jsonData, type);
		return result;
	}

	public static <T> T parseJsonWithGson(String jsonData, Type type) {
		Gson gson = new Gson();
		T result = gson.fromJson(jsonData, type);
		return result;
	}

	public static <T> T parseJsonWithGson(String jsonData, Type type, String timeType) {
		Gson gson = new GsonBuilder().setDateFormat(timeType).create();
		T result = gson.fromJson(jsonData, type);
		return result;
	}

	/**
	 * @author I321533
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> jsonToList(String json, Class<T[]> clazz) {
		Gson gson = new Gson();
		T[] array = gson.fromJson(json, clazz);
		return Arrays.asList(array);
	}

	/**
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
		Type type = new TypeToken<ArrayList<JsonObject>>() {
		}.getType();
		ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

		ArrayList<T> arrayList = new ArrayList<T>();
		for (JsonObject jsonObject : jsonObjects) {
			arrayList.add(new Gson().fromJson(jsonObject, clazz));
		}
		return arrayList;
	}
}
