package com.framework.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.framework.reports.ExtentReportClass;
import com.framework.utilities.ReadDataFromPropFile;

public class WebDriverClass extends ExtentReportClass {
	
	//This class will be used to maintain all methods related to browser
	
	protected static WebDriver driver;
	
	//Method to launch the browser window
	@BeforeMethod (alwaysRun=true)
	public void startBrowser() {
		String browser = ReadDataFromPropFile.readProperties("Config.properties").getProperty("browser");
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
	}
	
	//Method to close browser windows
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		driver.quit();
		stopReporting();
	}
	
	//Method to share driver with all other classes
	public static WebDriver getDriver() {
		return driver;
	}
	
	

}
