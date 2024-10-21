package com.api.testcases;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;

import com.api.constants.APIConstants;
import com.api.endpoints.ReqRes_Endpoints;
import com.api.reportgenerator.ReportGenerator;
import com.aventstack.extentreports.Status;

import io.restassured.http.Headers;
import io.restassured.response.Response;

public class ReqRes_TestCases extends APIConstants {

	public void testGetAllUsersAPI(HashMap<String, String> hashmap, ReportGenerator report) {

		Response res = ReqRes_Endpoints.getAllUsers(hashmap.get(QUERY_PARAMS));
		int code = Integer.parseInt(hashmap.get("ExpectedStatusCode"));
		Assert.assertEquals(res.statusCode(), code);
		report.logMessage("Status code is 200 :", Status.PASS);
		report.logMessage("Response is : " + res.jsonPath().prettyPrint(), Status.INFO);
		List<String> emails = res.jsonPath().getList("data.email");
		System.out.println(emails);
		for (String email : emails) {
			if (email.equalsIgnoreCase("lindsay.ferguson@reqres.in")) {
				System.out.println("email present");
			}
		}
	}

	public void testGetSingleUserAPI(HashMap<String, String> hashmap, ReportGenerator report) {

		Response res = ReqRes_Endpoints.getBySingleUser(hashmap.get(PATH_PARAM));
		int code = Integer.parseInt(hashmap.get("ExpectedStatusCode"));
		Assert.assertEquals(res.statusCode(), code);
		Headers headers =res.getHeaders();
		System.out.println(headers.toString());
		Assert.assertEquals(headers.get("Content-Type").getValue(), "application/json; charset=utf-8");
		report.logMessage("Status code is 200 :", Status.PASS);
		report.logMessage("Response is : " + res.jsonPath().prettyPrint(), Status.INFO);
	}

	public void testCreateUserAPI(HashMap<String, String> hashmap, ReportGenerator report) {

		Response res = ReqRes_Endpoints.createUser(hashmap.get(REQUEST_BODY));
		int code = Integer.parseInt(hashmap.get("ExpectedStatusCode"));
		Assert.assertEquals(res.statusCode(), code);
		String userName = res.jsonPath().getString("name");
		System.out.println(userName);
		Assert.assertEquals(userName, hashmap.get("ExpectedUserName"));
		report.logMessage("Status code is 201 :", Status.PASS);
		report.logMessage("Response is : " + res.jsonPath().prettyPrint(), Status.INFO);
	}

	public void testUpdateUserAPI(HashMap<String, String> hashmap, ReportGenerator report) {

		Response res = ReqRes_Endpoints.updateUserAPI(hashmap.get(PATH_PARAM), hashmap.get(REQUEST_BODY));
		int code = Integer.parseInt(hashmap.get("ExpectedStatusCode"));
		Assert.assertEquals(res.statusCode(), code);
		report.logMessage("Status code is 200 :", Status.PASS);
		report.logMessage("Response is : " + res.jsonPath().prettyPrint(), Status.INFO);
	}

	public void testDeleteUserAPI(HashMap<String, String> hashmap, ReportGenerator report) {
		Response res = ReqRes_Endpoints.deleteUserAPI(hashmap.get(PATH_PARAM));
		int code = Integer.parseInt(hashmap.get("ExpectedStatusCode"));
		Assert.assertEquals(res.statusCode(), code);
		report.logMessage("Status code is 204 :", Status.PASS);

	}

}
