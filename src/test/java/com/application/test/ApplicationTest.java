package com.application.test;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.application.pages.ForgotPasswordPage;
import com.application.pages.HomePage;
import com.application.pages.LoginPage;
import com.framework.listners.RetryTestCase;
import com.framework.utilities.ReadDataFromExcel;
import com.framework.webdriver.WebDriverClass;

public class ApplicationTest extends WebDriverClass{
	
	//This class will be used to maintain all test methods (@Test) and Data methods (@DataProvider) for current project
	
	@Test(priority = 1 , groups= {"Smoke","Regression","Sanity"},retryAnalyzer=RetryTestCase.class)
	public void VerifyApplicationTitle() throws IOException {
		startReporting("Verify Application Title");
		LoginPage loginpage = LoginPage.getLoginPage();
		loginpage.launchApplication();
		loginpage.verifyApplicationTitle();
	}
	
	@Test(priority = 2 , groups= {"Sanity"},retryAnalyzer=RetryTestCase.class)
	public void VerifyApplicationHeader() throws IOException {
		startReporting("Verify Application Login Page Header");
		LoginPage loginpage = LoginPage.getLoginPage();
		loginpage.launchApplication();
		loginpage.VerifyLoginPageHeader();
	}
	
	@Test(priority = 3 , groups= {"Sanity"},dataProvider="logindata",retryAnalyzer=RetryTestCase.class)
	public void VerifyApplicationLogin(String username,String password) throws IOException {
		startReporting("Verify Application Login Page Header");
		LoginPage loginpage = LoginPage.getLoginPage();
		loginpage.launchApplication();
		loginpage.UpdateUsernameAndPassword(username, password);
		loginpage.loginIntoApplication();
		loginpage.VerifySuccessfulLogin();
	}
	
	@Test(dataProvider="logoutdata",priority=3, groups= {"Sanity"},retryAnalyzer=RetryTestCase.class)
	public void VerifyApplicationLogout(String Username, String Password) throws IOException {
		startReporting("Verify Application Logout Functionality");
		LoginPage loginPage = LoginPage.getLoginPage();
		HomePage homePage = HomePage.getHomePage();
		loginPage.launchApplication();
		loginPage.UpdateUsernameAndPassword(Username, Password);
		loginPage.loginIntoApplication();
		homePage.verifyApplicationLogout();
	}
	
	@Test(priority=4, groups= {"Sanity"},retryAnalyzer=RetryTestCase.class)
	public void VerifyForgotPasswordFeature() throws IOException {
		startReporting("Verify Forgot Password Page");
		LoginPage loginPage = LoginPage.getLoginPage();
		ForgotPasswordPage forgotPasswordPage = ForgotPasswordPage.getForgotPasswordPage();
		loginPage.launchApplication();
		loginPage.verifyApplicationTitle();
		forgotPasswordPage.verifyForgotPasswordPageElements();
	}
	
	@DataProvider(name = "logindata")
	public String[][] testdata() {
		String[][] data = ReadDataFromExcel.readData("TestData.xlsx", "Sheet1");
		return data;
	}
	
	@DataProvider(name = "logoutdata")
	public String[][] testdata2() {
		String[][] data = ReadDataFromExcel.readData("TestData.xlsx", "Sheet2");
		return data;
	}

}
