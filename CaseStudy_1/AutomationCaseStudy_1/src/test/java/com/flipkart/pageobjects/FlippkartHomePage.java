package com.flipkart.pageobjects;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.variables.ApplicationVariables;
import com.variables.GobalVariables;

public class FlippkartHomePage {

	public FlippkartHomePage(WebDriver driver) {
		GobalVariables.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "q")
	private WebElement inputsearchforproducts;

	@FindBy(xpath = "//span[text()='iphone 14']/parent::span")
	private WebElement gettextofitemcount;

	@FindBy(xpath = "//span[text()='Login']//ancestor::div/button")
	private WebElement loginclosebutton;

	@FindBy(xpath = "//span[text()='iphone 14']/parent::span//ancestor::div[3]/following-sibling::div[1]//a/div[2]/div[1]/div[1]")
	private WebElement fristdisplayedelement;

	public boolean waitForsearchforproductstextbox() {
		try {
			GobalVariables.wait = new WebDriverWait(GobalVariables.driver,
					Duration.ofSeconds(com.variables.Constant.WAIT_DEFAULT_TIMEOUT));
			GobalVariables.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean waitForsearchforproducts() {
		try {
			GobalVariables.wait = new WebDriverWait(GobalVariables.driver,
					Duration.ofSeconds(com.variables.Constant.WAIT_DEFAULT_TIMEOUT));
			GobalVariables.wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='iphone 14']/parent::span")));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean verifyLoginPage() {
		try {
			GobalVariables.wait = new WebDriverWait(GobalVariables.driver,
					Duration.ofSeconds(com.variables.Constant.SHORT_DEFAULT_TIMEOUT));
			GobalVariables.wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//span[text()='Login']//ancestor::div/button")));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
 public boolean verifyFlipkartLogo() {
	 try {
		 GobalVariables.wait = new WebDriverWait(GobalVariables.driver,
					Duration.ofSeconds(com.variables.Constant.SHORT_DEFAULT_TIMEOUT));
			GobalVariables.wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//img[@title='Flipkart']")));
			return true;
		} catch (Exception e) {
			return false;
	 }
 }
	public boolean enterTextandSearchProduct(String searchtext) {
		try {
			// Wait until Search text box is visible

			if (verifyLoginPage() == true) {
				loginclosebutton.click();
			}

			GobalVariables.bStatus = waitForsearchforproductstextbox();
			if (GobalVariables.bStatus == false) {
				return false;
			}
			inputsearchforproducts.click();
			inputsearchforproducts.sendKeys(searchtext);
			inputsearchforproducts.sendKeys(Keys.ENTER);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean getNoofItemsinPage() {
		try {

			GobalVariables.bStatus = waitForsearchforproducts();
			if (GobalVariables.bStatus == false) {
				return false;
			}

			String countlabel = gettextofitemcount.getText();
			String itemcount = pageCount(countlabel);

			System.out.println("Number of items displayed in Page " + itemcount);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean clickonFristDisplayedItem() {
		try {
			
			fristdisplayedelement.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean verifyNavigatePageandTittle() {
		try {
			String firstElementText = fristdisplayedelement.getText();
			String mainWindow = GobalVariables.driver.getWindowHandle();
            Thread.sleep(3000);
			Set<String> set = GobalVariables.driver.getWindowHandles();
			Iterator<String> itr = set.iterator();
			while (itr.hasNext()) {
				String childWindow = itr.next();

				// Compare whether the main windows is not equal to child window. If not equal,
				if (!mainWindow.equals(childWindow)) {
					GobalVariables.driver.switchTo().window(childWindow);
					
					String actualTitle = GobalVariables.driver.getTitle();
				
					String input = actualTitle; 
					boolean isFound = input.indexOf("APPLE iPhone 14") !=-1? true: false;
								
					if(isFound) {
						System.out.println("Successfully navigated to first displayed item page");
					}
					else {
					Assert.fail(actualTitle+" Actual Title is Not Matched with Expected Title "+ApplicationVariables.AutomationPanda.expectedPageTitle);
					}
					
					//Verify Right Page is displayed
					String verifyRightItemDisplayed = "//span[text()='"+firstElementText+"']";
					
					GobalVariables.driver.findElement(By.xpath(verifyRightItemDisplayed)).isDisplayed();
					Thread.sleep(1000);
					//GobalVariables.driver.close();
				}
			}
			// This is to switch to the main window
			Thread.sleep(1000);
			GobalVariables.driver.switchTo().defaultContent();
		
			 
			return true;
		}
		catch (Exception e) {
		e.printStackTrace();
		return false;
		}
	}

	public String pageCount(String searchtext) {
		String noofitems = null;
		try {
			String[] itemsLists = searchtext.split("of");
			String[] parts1 = itemsLists[1].split("results");
			noofitems = parts1[0].strip();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noofitems;
	}

}
