package com.framewok.commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.webdriver.WebDriverClass;

public class WebCommons {
	
	//This class will be used to maintain all common methods to handle any web application
	
	public WebDriver driver = WebDriverClass.getDriver();

	// Method to perform click
	public void Click(WebElement element) {
		if (element.isDisplayed() && element.isEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()", element);
			element.click();
		}
	}
	
	//Method to enter text	
	public void EnterInfo(WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		element.clear();
		element.sendKeys(value);
	}
	
	//Method to Select Checkbox
	public void SelectCheckbox(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		if(!element.isSelected())
			element.click();
	}
	
	//Method to Select Dropdown option
	public void SelectOption(WebElement element,String option, String By) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		Select s = new Select(element);
		if(By.equals("value")) {
			s.selectByValue(option);
		}else if(By.equals("visibleText")) {
			s.selectByVisibleText(option);
		}else if(By.equals("index")) {
			s.selectByIndex(Integer.parseInt(option));
		}		
	}
	
	//Method to perform double click
	public void DoubleClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
	}
	
	//Method to perform right click
	public void RightClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		Actions action = new Actions(driver);
		action.contextClick(element).build().perform();
	}
	
	//Method to perform drag and drop
	public void DragAndDrop(WebElement source, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(source,target).build().perform();
	}

	//Method to implicit wait
	public void ImplicitWait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//Method to wait for Element (explicit Wait)
	public void ExplicitWait(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
	}
	
	//Method to get title of the Page
	public String getTitle() {
		String title = driver.getTitle();
		return title;
	}
	
	// Method to click on alert button
	public void ClickOnAlert(String option) {
		Alert alert = driver.switchTo().alert();
		if (option.equals("ok"))
			alert.accept();
		else if (option.equals("cancel"))
			alert.dismiss();
	}
	
	//Method to take screenshot
	public String takeScreenshot(String screenshotname) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot)driver; // to take screenshot
		File screenshotfile = screenshot.getScreenshotAs(OutputType.FILE);  // to convert screenshot into file format
		String screenshotpath =System.getProperty("user.dir")+"\\Screenshots\\"+screenshotname+"_"+uniqueid()+".png"; //to collect the path to store screenshots
		FileUtils.copyFile(screenshotfile, new File(screenshotpath)); // to copy screenshots file into folder
		return screenshotpath;
	}
	
	//method to generate random unique id
	public static String uniqueid() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
		String uniqueId = sdf.format(Calendar.getInstance().getTime());
		return uniqueId;
	}
}
