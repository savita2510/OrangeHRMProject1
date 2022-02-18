package com.application.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.framewok.commons.WebCommons;
import com.framework.webdriver.WebDriverClass;

public class ForgotPasswordPage extends WebCommons{
	
	ExtentTest logger = WebDriverClass.getLogger();
	
	//This class will be used to maintain all methods and elements related to Forgot Password page
	
	@FindBy(linkText = "Forgot your password?")
	private WebElement forgotPasswordLink;

	@FindBy(xpath = "//input[@id='securityAuthentication_userName']")
	private WebElement usernameTxtb;

	@FindBy(xpath = "//input[@id='btnSearchValues']")
	private WebElement resetPasswordBtn;

	@FindBy(xpath = "//input[@id='btnCancel']")
	private WebElement cancelBtn;
	
	public void verifyForgotPasswordPageElements() throws IOException {
		try {
			Click(forgotPasswordLink);
			usernameTxtb.isDisplayed();
			resetPasswordBtn.isDisplayed();
			cancelBtn.isDisplayed();
			logger.pass("Forgot Password Page Elements Available");
		} catch (Exception e) {
			logger.addScreenCaptureFromPath(takeScreenshot("ForgotPasswordPage"));
			logger.fail("Forgot Password Page Elements not Available");
			Assert.fail("Forgot Password Page Elements not Available");
		}
	}
	
	public static ForgotPasswordPage getForgotPasswordPage() {
		return PageFactory.initElements(WebDriverClass.getDriver(), ForgotPasswordPage.class);
	}
}
