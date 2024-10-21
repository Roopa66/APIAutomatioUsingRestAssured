package com.api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.json.JSONObject;

import com.api.urls.Request_Urls;
import com.api.utils.CommonUtils;

import io.restassured.response.Response;

public class ReqRes_Endpoints {
	// Rest Assured Methods

	public static Response getAllUsers(String queryParam) {
		HashMap<String, String> queryParamsList = CommonUtils.getQueryParamsFromString(queryParam);
		String pageParamValue = queryParamsList.get("page");
		Response response = given().contentType("application/json").queryParam("page", pageParamValue)
				.get(Request_Urls.USERS_URL);
		return response;
	}

	public static Response getBySingleUser(String pathParam) {
		Response response = given().contentType("application/json").pathParam("UserId", pathParam)
				.get(Request_Urls.SINGLE_USER_URL);
		return response;
	}

	public static Response createUser(String requestBody) {
		// HashMap<String, String> mapRequestBody=
		// CommonUtils.getJSONBodyFromString(requestBody);
		JSONObject jsonRequestBody = new JSONObject(requestBody);
		Response response = given().contentType("application/json").body(jsonRequestBody.toString())
				.post(Request_Urls.USERS_URL);

		return response;
	}

	public static Response updateUserAPI(String pathParam, String requestBody) {
		JSONObject jsonRequestBody = new JSONObject(requestBody);
		Response response = given().contentType("application/json").pathParam("UserId", pathParam)
				.body(jsonRequestBody.toString()).put(Request_Urls.SINGLE_USER_URL);
		return response;
	}

	public static Response deleteUserAPI(String pathParam) {
		Response response = given().pathParam("UserId", pathParam)
				.delete(Request_Urls.SINGLE_USER_URL);
		return response;
	}

}
