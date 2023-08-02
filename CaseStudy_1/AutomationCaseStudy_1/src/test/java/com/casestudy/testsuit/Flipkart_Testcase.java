package com.casestudy.testsuit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.flipkart.pageobjects.FlippkartHomePage;
import com.variables.ApplicationVariables;
import com.variables.GobalVariables;

public class Flipkart_Testcase extends BaseCase {

	@Test(priority = 1)
	public void flipkart_test() {
		
		
		FlippkartHomePage homepage = new FlippkartHomePage(driver);
		
		// Verify the Flipkart is present on left side of the top
		GobalVariables.bStatus=homepage.verifyFlipkartLogo();
		if (GobalVariables.bStatus == false) {
			Assert.fail("Unable to verify Flipkart logo");
		}
		System.out.println("Flipkart logo is verified Successfully");
		
		GobalVariables.bStatus=homepage.enterTextandSearchProduct(ApplicationVariables.Flipkart.searchProduct);
		if (GobalVariables.bStatus == false) {
			Assert.fail("Unable to Search Product");
		}
		System.out.println(ApplicationVariables.Flipkart.searchProduct+" -Search Results fetched Successfully");
		
		//Find the number of items displayed
		GobalVariables.bStatus=homepage.getNoofItemsinPage();
		if (GobalVariables.bStatus == false) {
			Assert.fail("Unable to Find the number of items displayed in page");
		}
		
		//click on first displayed Search Item
		GobalVariables.bStatus=homepage.clickonFristDisplayedItem();
		if (GobalVariables.bStatus == false) {
			Assert.fail("Unable to click on first displayed Search Item");
		}
		System.out.println("Successfully clicked on first displayed Search Item");
		
		GobalVariables.bStatus=homepage.verifyNavigatePageandTittle();
		if (GobalVariables.bStatus == false) {
			Assert.fail("Unable to Verify that navigated to the right page and title of the Page");
		}
		
		System.out.println("Successfully Verified title of the Page");
	}
	

}
