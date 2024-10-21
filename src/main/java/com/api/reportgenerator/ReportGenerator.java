package com.api.reportgenerator;

import java.io.File;

import org.testng.Reporter;

import com.api.utils.CommonUtils;
import com.api.utils.ConfigPropertyLoader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportGenerator {

	
	private ExtentHtmlReporter htmlReporter = null;
	private ExtentReports extent = null;
	private ExtentTest test = null;
	private static final String BASE_REPORT_DIR = System.getProperty("user.dir")  ;
	private String reportPath = null;
	
	
	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public ReportGenerator() {
		//Empty constructor to instantiate from test method 
	}
	
	public void setupExtendedReport(String testCaseNumber, String testMethodName) {
		// location of the extent report
		reportPath = BASE_REPORT_DIR + File.separator +"test-output"+File.separator+"extentreports"+File.separator+testCaseNumber;
		File reportDirectory = new File(reportPath);
		try {
			if (!reportDirectory.exists()) {
				reportDirectory.mkdirs();
			}
		}catch(Exception ex) {
			Reporter.log("error while creating folder with testcase name");
		}
		String htmlFilePath = reportPath + File.separator+ testMethodName + "_" + CommonUtils.getFormatedDate("MMddyyyyhh_mm_ss") +".html";
		htmlReporter = new ExtentHtmlReporter(htmlFilePath);
		extent = new ExtentReports(); // create object of ExtentReports
		extent.attachReporter(htmlReporter);

		htmlReporter.config().setDocumentTitle("RestAssured Test Automation Report"); // Title of Report
		htmlReporter.config().setReportName("RestAssured Test Automation Report"); // Name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);// Default Theme of Report

		// General information related to application
		extent.setSystemInfo("Application Name", "ReqRes RestAssuredAutomation");
		extent.setSystemInfo("Author", "roopa");
		test = extent.createTest(testMethodName);
		
	}
	
	
	/** 
	 * To log the test message to capture in the report. 
	 * @param message string value to be printed in the report
	 * @param logLevel logLevel could be one of the following values such as 
	 * PASS, FAIL, FATAL,ERROR, WARNING, INFO, DEBUG, SKIP
	 */
	public void logMessage(String message, Status logLevel) {
		Status level = Status.INFO;
		if(logLevel != null) level = logLevel;
		test.log(level, message);
		Reporter.log(message); //routing the message to testng reporter
	}

	
	public void endReport() {
		try {
			extent.flush();
		} catch (Exception e) {
			Reporter.log("Exception occured while pushing the test execution report due to " + e.getMessage());
		} finally {
			test = null;extent = null;htmlReporter = null;			
		}
	}
}
