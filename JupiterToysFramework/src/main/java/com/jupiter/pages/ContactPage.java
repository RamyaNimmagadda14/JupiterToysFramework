package com.jupiter.pages;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.Assert;

/*  Function Written By - Ramya Nimmagadda
 *  Purpose - Object Repository and Methods related to Contact Page 
 */

public class ContactPage extends Common{


	public ContactPage(WebDriver driver) {
		super(driver);
	}

	public String strActualText="";
	public String strExpectedText="";

	@FindBy(xpath = "//input[@id='forename']")	private WebElement txtForename;
	@FindBy(xpath = "//input[@id='email']")	private WebElement txtEmail;
	@FindBy(xpath = "//textarea[@id='message']")	private WebElement txtMessage;
	@FindBy(xpath = "//a[text()='Submit']")	private WebElement btnSubmit;

	@FindBy(xpath = "//span[@id='forename-err']")	private WebElement lblForenameErr;
	@FindBy(xpath = "//span[@id='email-err']")	private WebElement lblEmailErr;
	@FindBy(xpath = "//span[@id='message-err']")	private WebElement lblMessageErr;

	@FindBy(xpath = "(/html/body/div[2]/div/div/strong)")	private WebElement lblSuccessMessage;
	@FindBy(xpath="//*[@id=\"header-message\"]") private WebElement txtheadermessage;


	// To populate mandatory fields
	public void setMandatoryDetails(String strForename, String strEmail, String strMessage) {
		setValue(txtForename, strForename, "Forename");
		setValue(txtEmail, strEmail, "Email");
		setValue(txtMessage, strMessage, "Message");
	}

	public void clickSubmit() {
		click(btnSubmit, "Submit");
		waitForBrowserStability();
	}

	public String validateFormSubmission(String strForename) throws IOException{
		
		strActualText = lblSuccessMessage.getText();
		click(lblSuccessMessage, strActualText);
		return strActualText;
	
	}
	
	public void validateForenameField() {
		if(driver.getPageSource().contains("is required")) {
			if(isElementPresent(lblForenameErr)) {
				strActualText = lblForenameErr.getText();
				test.log(Status.PASS, strActualText);
			}
			
			
		}
	}

	public void validateEmailField() {
		if(driver.getPageSource().contains("is required") ||
				driver.getPageSource().contains("valid email")	) {
			if(isElementPresent(lblEmailErr)) {
				strActualText = lblEmailErr.getText();
				test.log(Status.PASS, strActualText);
			}
			
		} 
	}


	public void validateMessageField() {
		if(driver.getPageSource().contains("is required")) {
			if(isElementPresent(lblMessageErr)) {
				strActualText = lblMessageErr.getText();
				test.log(Status.PASS, strActualText);
			}
			
		}
	}
	
	
	public void validateErrorsOnPage() throws IOException{
		validateForenameField();
		validateEmailField();
		validateMessageField();
	}

	public String validateNoErrorsOnPage() {
		     strActualText = txtheadermessage.getText();
		     return strActualText;
	}
			
		
		
	}

		



