package com.api.suites;

import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.api.dataprovider.AutomationTestDataProvider;
import com.api.reportgenerator.ReportGenerator;
import com.api.testcases.ReqRes_TestCases;

public class ReqRes_Suite {
	ReqRes_TestCases testCases = null;
	ReportGenerator report = null;

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext iTestContext) {
		report = new ReportGenerator();
	}

	@AfterMethod(alwaysRun = true)
	public void doAfterMethod(ITestResult result) {
		report.endReport();
	}

	@BeforeSuite
	public void beforeSuite() {
		testCases = new ReqRes_TestCases();
	}

	@Test(groups = "Level1", testName = "Verify ReqRes APIs", dataProvider = "CsvMapDataProvider", dataProviderClass = AutomationTestDataProvider.class)
	public void testReqGetResAPIs(ITestContext iTestContext, HashMap<String, String> hashmap) {
		report.setupExtendedReport(hashmap.get("TestcaseNumber"), hashmap.get("TestcaseName"));
		iTestContext.setAttribute("testDataMap", hashmap);
		if (hashmap.get("TestcaseNumber").equalsIgnoreCase("C1")) {
			testCases.testGetAllUsersAPI(hashmap, report);
		}
		if (hashmap.get("TestcaseNumber").equalsIgnoreCase("C2")) {
			testCases.testGetSingleUserAPI(hashmap, report);
		}

		if (hashmap.get("TestcaseNumber").equalsIgnoreCase("C3")) {
			testCases.testCreateUserAPI(hashmap, report);
		}

		if (hashmap.get("TestcaseNumber").equalsIgnoreCase("C4")) {
			testCases.testUpdateUserAPI(hashmap, report);
		}

		if (hashmap.get("TestcaseNumber").equalsIgnoreCase("C5")) {
			testCases.testDeleteUserAPI(hashmap, report);
		}

	}
	@Test
	public void testBDDKeyword() {
		
		
	}

}
