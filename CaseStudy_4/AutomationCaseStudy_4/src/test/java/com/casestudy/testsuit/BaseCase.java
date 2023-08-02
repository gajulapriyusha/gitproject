package com.casestudy.testsuit;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseCase {

	public static WebDriver driver;
	public static String testSuiteName;
	static WebDriverWait wait;

	@BeforeSuite
	@Parameters({"browserName", "baseUrl"})
	public static void launch_browser(String browserName, String baseUrl) {
		try {
			if (browserName.equalsIgnoreCase("Chrome")) {
				// For Chrome Browser
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(baseUrl);
				wait = new WebDriverWait(driver, Duration.ofSeconds(com.variables.Constant.DEFAULT_TIMEOUT));
				System.out.println("***************[Chrome Browser Opened Successfully]******************");
			} else if (browserName.equalsIgnoreCase("Internet Explorer")) {

				// For Internet Explorer Browser
				WebDriverManager.iedriver().setup();
				driver = new EdgeDriver();
				driver.manage().window().maximize();
				driver.get(baseUrl);
				wait = new WebDriverWait(driver, Duration.ofSeconds(com.variables.Constant.DEFAULT_TIMEOUT));
				System.out.println("***************[Edge Browser Opened Successfully]******************");
			} else {
				// For Firefox Browser
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.get(baseUrl);
				wait = new WebDriverWait(driver, Duration.ofSeconds(com.variables.Constant.DEFAULT_TIMEOUT));
				System.out.println("***************[Firefox Browser Opened Successfully]******************");
			}
		} catch (Exception e) {
			Assert.fail("Unable to Launch Browser");
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void closeBrowsers() {
		if (driver != null) {
			driver.quit();
			System.out.println("***************[Browser instances Closed Successfully]******************");
		}
	}

}
