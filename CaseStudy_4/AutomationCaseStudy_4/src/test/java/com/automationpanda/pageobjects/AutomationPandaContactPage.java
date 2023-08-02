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

public class AutomationPandaContactPage {

	public AutomationPandaContactPage(WebDriver driver) {
		GobalVariables.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//label[text()='Name']/following-sibling::input")
	private  WebElement inputName;
	
	@FindBy(xpath = "//label[text()='Email']/following-sibling::input")
	private  WebElement inputEmail;
	
	@FindBy(xpath = "//label[text()='Message']/following-sibling::textarea")
	private  WebElement inputMessage;
	
	@FindBy(xpath = "//button[@type='submit']/strong[text()='Contact Me']")
	private WebElement buttonContactMe;
	
	@FindBy(xpath = "//h4[text()='Your message has been sent']")
	private WebElement labelMessageSent;
	
	
	public static boolean  verifySendMeMessage() {
		try {
			GobalVariables.wait = new WebDriverWait(GobalVariables.driver, Duration.ofSeconds(com.variables.Constant.WAIT_DEFAULT_TIMEOUT));
			GobalVariables.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Your message has been sent']")));
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	public boolean doFillContactInformation(String name,String email,String message) {
		try {
			inputName.sendKeys(name);
			inputEmail.sendKeys(email);
			inputMessage.sendKeys(message);
			buttonContactMe.click();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
		
	}
	
}
