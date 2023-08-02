package com.casestudy.testsuit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationpanda.pageobjects.AutomationPandaContactPage;
import com.automationpanda.pageobjects.AutomationPandaHomePage;
import com.variables.ApplicationVariables;
import com.variables.GobalVariables;

public class AutomationPanda_Testcase extends BaseCase {

	@Test(priority = 1)
	public void verifyTitleandClickonContactLink() {
		
		//Launched a below URL and Verify the Page Title
		String actualTitle = driver.getTitle();
		if(ApplicationVariables.AutomationPanda.expectedPageTitle.contentEquals(actualTitle)) {
			System.out.println("Successfully Verified Launched Page Title");
		}
		else {
		Assert.fail(actualTitle+" Actual Title is Not Matched with Expected Title "+ApplicationVariables.AutomationPanda.expectedPageTitle);
		}
		
		AutomationPandaHomePage homepage = new AutomationPandaHomePage(driver);
		GobalVariables.bStatus=homepage.clickonContactLink();
		if (GobalVariables.bStatus == false) {
			Assert.fail("Unable to click on Contact Link");
		}
		System.out.println("Contact Link  is clicked Successfully and Navigated to Contact Page");
	}
	
	@Test(priority =2)
	public void fillcontactinformationandverify() {
		
		/*
		 * Click on Contact and verify the title of the Page. And fill the below
		 * information and click on Submit 1. Name 2. Email 3. Comment
		 */
		AutomationPandaContactPage contactpage = new AutomationPandaContactPage(driver);
		GobalVariables.bStatus=contactpage.doFillContactInformation("Priyusha","test@gmsil.com","Test Contact Info");
		if (GobalVariables.bStatus == false) {
			Assert.fail("Unable to fill Contact information");
		}
		System.out.println("Contact information filled Successfully ");
		
		//After clicking on the Submit Please verify the “Message Sent” Message
		GobalVariables.bStatus=AutomationPandaContactPage.verifySendMeMessage();
		if (GobalVariables.bStatus == false) {
			Assert.fail("Message Sent is not visible after submit of Contact information ");
		}
		System.out.println("Successfully verified Message Sent text after submission of Contact information");
	}
}
