package com.automationpanda.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.variables.GobalVariables;

public class AutomationPandaHomePage {

	public AutomationPandaHomePage(WebDriver driver) {
		GobalVariables.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(text(),'Contact')]")
	private WebElement contactLink;

	public static boolean waitForContactLink() {
		try {
			GobalVariables.wait = new WebDriverWait(GobalVariables.driver,
					Duration.ofSeconds(com.variables.Constant.WAIT_DEFAULT_TIMEOUT));
			GobalVariables.wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Contact')]")));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean waitForContactPage() {
		try {
			GobalVariables.wait = new WebDriverWait(GobalVariables.driver,
					Duration.ofSeconds(com.variables.Constant.WAIT_DEFAULT_TIMEOUT));
			GobalVariables.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("entry-title")));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean clickonContactLink() {

		try {
			// Wait for Contact Link
			GobalVariables.bStatus = waitForContactLink();
			if (GobalVariables.bStatus == false) {
				return false;
			}
			// Click on Contact Link
			 contactLink.click();

			// Wait for Contact Page
			GobalVariables.bStatus = waitForContactPage();
			if (GobalVariables.bStatus == false) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
