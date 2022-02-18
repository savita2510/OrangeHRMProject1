package com.application.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.framewok.commons.WebCommons;
import com.framework.webdriver.WebDriverClass;

public class HomePage extends WebCommons{

	ExtentTest logger = WebDriverClass.getLogger();

	//This class will be used to maintain all methods and elements related to Home page
	
	@FindBy(xpath ="//a[@id='welcome']")
	private WebElement welcomeLabel;
	
	By bywelcomeLabel = By.xpath("//a[@id='welcome']");
	
	@FindBy(xpath = "//a[text()='Logout']")
	private WebElement logoutButton;

	By bylogoutButton = By.xpath("//a[text()='Logout']");

	By byLoginPanelHeading = By.xpath("//div[@id='logInPanelHeading']");
	
	public void verifyApplicationLogout() throws IOException {
		try {
			Click(welcomeLabel);
			ExplicitWait(bylogoutButton);
			Click(logoutButton);
			ExplicitWait(byLoginPanelHeading);
			logger.pass("Application Logout is Successful");
		} catch (Exception e) {
			logger.addScreenCaptureFromPath(takeScreenshot("ApplicationLogout"));
			logger.fail("Application Logout is Not Successful");
			Assert.fail("Application Logout is Not Successful");
		}
	}
	
	public static HomePage getHomePage() {
		return PageFactory.initElements(WebDriverClass.getDriver(), HomePage.class);
	}
}
