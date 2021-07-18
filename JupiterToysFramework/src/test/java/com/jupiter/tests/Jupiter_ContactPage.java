package com.jupiter.tests;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.jupiter.pages.CartPage;
import com.jupiter.pages.ContactPage;
import com.jupiter.pages.Base;
import com.jupiter.pages.HomePage;
import com.jupiter.pages.ShopPage;

import java.io.IOException;

/*  Function Written By - Ramya Nimmagadda
 *  Purpose - Script to validate the tests  
 */

public class Jupiter_ContactPage extends Base{

	public void getScreenshot() throws IOException {
		test.addScreenCaptureFromPath(reports.captureScreen(driver));
	}


/* 
	1 .From the home page go to contact page
	2.	Click submit button
	3.	Validate errors
	4.	Populate mandatory fields
	5.	Validate errors are gone

 */
	@Test(dataProvider="data", testName="TC_01_ValidateFieldsOnContactPage")
	public void TC_01_ValidateFieldsOnContactPage(String strForename, String strEmail, 
			String strMessage) throws IOException{

		test.assignCategory("SanitySuite");
		String	strExpectedText = "We welcome your feedback - tell it how it is.";

		try {
			test.log(Status.INFO, "Validating mandatory fields on Contact page");
			HomePage homePage = new HomePage(driver);
			homePage.gotoContactPage();
			ContactPage contactPage = new ContactPage(driver);
			contactPage.clickSubmit();
			contactPage.validateErrorsOnPage();
			contactPage.setMandatoryDetails(strForename, strEmail, strMessage);
			//contactPage.validateNoErrorsOnPage();
			Assert.assertEquals(contactPage.validateNoErrorsOnPage(), strExpectedText);
			test.log(Status.PASS, "Mandatory error Messages are not displayed");	

		} catch (Exception e) {
			test.log(Status.FAIL, "unable to validate fields on Contact page"+e.getMessage());
			getScreenshot();
		}
	}
	
/* 
	1.	From the home page go to contact page
	2.	Populate mandatory fields
	3.	Click submit button
	4.	Validate successful submission message

 */
	
	@Test(invocationCount=5, dataProvider="data", testName="TC_02_VerifyFeedbackSubmission")
	public void TC_02_VerifyFeedbackSubmission(String strForename, String strEmail, 
			String strMessage) throws IOException, AssertionError{

		test.assignCategory("SanitySuite");
		String strExpectedText = "Thanks "+strForename;
		try {
			test.log(Status.INFO, "Verify feedback form submission");
			HomePage homePage = new HomePage(driver);
			homePage.gotoContactPage();
			ContactPage contactPage = new ContactPage(driver);
			contactPage.setMandatoryDetails(strForename, strEmail, strMessage);
			contactPage.clickSubmit();
			Assert.assertEquals(contactPage.validateFormSubmission(strForename), strExpectedText);
			test.log(Status.PASS, "Feedback submitted successfully"); 
			test.addScreenCaptureFromPath(reports.captureScreen(driver));
			
		}  catch (AssertionError ae) {
			test.log(Status.FAIL, "Assertion Error: "+ae.getMessage());
			getScreenshot();
			Assert.fail();
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Unsuccessful feedback submission"+e.getMessage());
			getScreenshot();
		}
	}
	


}

