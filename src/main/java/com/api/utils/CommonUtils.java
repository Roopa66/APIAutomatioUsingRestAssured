package com.api.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.json.JSONObject;

public class CommonUtils {
	
	public static final HashMap<String, String> getQueryParamsFromString(String queryParams) {
		HashMap<String, String> queryParamsList = new HashMap<>();

		String[] pairs = queryParams.split("&");

		// Iterate over the pairs and further split them by '=' to get key and value
		for (String pair : pairs) {
			String[] keyValue = pair.split("=");
			if (keyValue.length == 2) { // Ensure we have both key and value
				queryParamsList.put(keyValue[0], keyValue[1]);
			}
		}

		return queryParamsList;
	}
	public static String getFormatedDate(String format) {
		String sDateFormat;
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		Calendar currentDate = Calendar.getInstance();
		sDateFormat = sformat.format(currentDate.getTime());
		return sDateFormat;
	}
	public static final HashMap<String, String> getJSONBodyFromString(String request) {
		HashMap<String, String> requestBodyMap = new HashMap<>();
		JSONObject jsonObject = new JSONObject(request);
		for (String key : jsonObject.keySet()) {
			requestBodyMap.put(key, jsonObject.get(key).toString());
		}
		return requestBodyMap;
	}

}
