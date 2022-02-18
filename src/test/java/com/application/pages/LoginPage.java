package com.application.pages;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.framewok.commons.WebCommons;
import com.framework.reports.ExtentReportClass;
import com.framework.utilities.ReadDataFromPropFile;
import com.framework.webdriver.WebDriverClass;

public class LoginPage extends WebCommons{
	
	ExtentTest logger = ExtentReportClass.getLogger();

	//This class will be used to maintain all methods and elements related to Login page
	
	@FindBy(xpath="//img[contains(@src,'logo.png')]")
	private WebElement logo;
	
	@FindBy(xpath="//div[@id='logInPanelHeading']")
	WebElement loginPanelHeader;
	
	@FindBy(xpath="//input[@name='txtUsername']")
	WebElement usernameTextbox;
	
	@FindBy(xpath="//input[@name='txtPassword']")
	WebElement passwordTextbox;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginButton;
	
	@FindBy(linkText="Forgot your password?")
	WebElement forgotPasswordLink;
	
	By HomePageElement = By.xpath("//a[@id='welcome']");
	
	public void launchApplication() throws IOException {
		try {
			driver.get(ReadDataFromPropFile.readProperties("Config.properties").getProperty("url"));
			logger.pass("Application Successfully Launched");
		}catch(Exception e) {
			logger.fail("Error While Launching the Application");
			logger.addScreenCaptureFromPath(takeScreenshot("AppLaunch"));
			Assert.fail("Error While Launching the Application");
		}
	}
	
	public void verifyApplicationTitle() throws IOException {
		try {
			Assert.assertEquals(driver.getTitle(), ReadDataFromPropFile.readProperties("Config.properties").getProperty("title"));
			logger.pass("Application Title is Displyed as expected");
		}catch(Exception e) {
			logger.fail("Wrong title displayed");
			logger.addScreenCaptureFromPath(takeScreenshot("AppTitle"));
			Assert.fail("Wrong title displayed");
		}
	}
	
	
	public void VerifyLoginPageHeader() throws IOException {
		try {
			Assert.assertEquals(loginPanelHeader.getText(), ReadDataFromPropFile.readProperties("Config.properties").getProperty("loginheader"));
			logger.pass("Application Header is Displyed as expected");
		}catch(Exception e) {
			logger.fail("Wrong Header displayed");
			logger.addScreenCaptureFromPath(takeScreenshot("loginHeader"));
			Assert.fail("Wrong Header displayed");
		}
	}
	
	public void UpdateUsernameAndPassword(String username,String password) throws IOException {
		try {
			logger.info("Username: "+username+", Password: "+password);
			EnterInfo(usernameTextbox, username);
			EnterInfo(passwordTextbox, password);
			logger.pass("Username and Password updated successfully");
		}catch(Exception e) {
			logger.fail("Error while updating username and password");
			logger.addScreenCaptureFromPath(takeScreenshot("EnterCredentials"));
			Assert.fail("Error while updating username and password");
		}
	}
	
	public void loginIntoApplication() throws IOException {
		try {
			Click(loginButton);
			logger.pass("Logged-in into the application");
		}catch(Exception e) {
			logger.fail("Error while login into the application");
			logger.addScreenCaptureFromPath(takeScreenshot("AppLogin"));
			Assert.fail("Error while login into the application");
		}
	}
	
	public void VerifySuccessfulLogin() throws IOException {
		try {
			ExplicitWait(HomePageElement);
			logger.pass("Login is Successful");
			logger.addScreenCaptureFromPath(takeScreenshot("WelcomePage"));
		}catch(Exception e) {
			logger.fail("Login is not successful");
			logger.addScreenCaptureFromPath(takeScreenshot("WelcomePage"));
			Assert.fail("Login is not successful");
		}
	}
	
	public static LoginPage getLoginPage() {
		return PageFactory.initElements(WebDriverClass.getDriver(), LoginPage.class);
	}

}
