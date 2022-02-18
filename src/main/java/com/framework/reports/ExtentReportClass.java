package com.framework.reports;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.framewok.commons.WebCommons;

public class ExtentReportClass {
	
	//This class will be used to maintain all methods related to genrate extent report for project
	
	public static ExtentReports extent = null; // store report 
	public static ExtentTest logger =null; // to log messages in report
	
	//Method to generate empty html report
	@BeforeSuite(alwaysRun=true)
	public static void generateReort() {
		ExtentHtmlReporter htmlreport = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\AutomationReport_"+WebCommons.uniqueid()+".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlreport);
	}
	
	//Method to start printing process for perticular test case
	public static void startReporting(String testcase) {
		logger =extent.createTest(testcase);
	}
	
	//Method to stop printing process
	public static void stopReporting() {
		extent.flush();
	}
	
	//Method to share logger details with other classes
	public static ExtentTest getLogger() {
		return logger;
	}

}
