package com.jupiter.pages;



import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import com.jupiter.pages.Base;
import com.jupiter.utils.PropertiesUtility;


/*  Function Written By - Ramya Nimmagadda
 *  Purpose - To perform the common actions on web page  
 */


public class Common extends Base {
	protected WebDriver driver;

	public Common(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String strScreenCature = PropertiesUtility.SCREENSHOTS_FOR_STEPS;

	public void screenShotsForSteps() {
		if(strScreenCature.equalsIgnoreCase("Yes")) {
			try {
				test.addScreenCaptureFromPath(reports.captureScreen(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * setting the value in a text field 
	 * 
	 * */
	public void setValue(WebElement webEle, String strValue, String strFieldName) {  

		if (isElementPresent(webEle)) {

			highlighter(webEle);

			try {
				String type = webEle.getAttribute("type");

				if ((type.equalsIgnoreCase("text")) || type.equals("password")|| type.equals("number")
						||type.equals("tel")||type.equals("email") || type.equals("hidden") || type.equals("phone") ) {
					webEle.clear();
					webEle.sendKeys(strValue);
					test.log(Status.INFO, strValue+" entered in "+strFieldName+" text field.");
				
					screenShotsForSteps();

					return;
				}


				if (webEle.getTagName().equalsIgnoreCase("textarea")) {
					webEle.clear();
					webEle.sendKeys(strValue);
					screenShotsForSteps();

					return;
				}

			} catch (Exception e) {
				test.log(Status.FAIL, "Failed to set "+strValue+" in "+strFieldName+" text field. "+e.getMessage());
				try {
					test.addScreenCaptureFromPath(reports.captureScreen(driver));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		else {
			test.log(Status.INFO, "Attribute type mismatch for element "+strFieldName);
		}

	}


	//To set value in drop down
	public void setDropdownValue(WebElement webEle, String strValue, String strFieldName) {
		if (isElementPresent(webEle)) {
			highlighter(webEle);
			try {
				Select selectOption = new Select(webEle);
				selectOption.selectByVisibleText(strValue);
				test.log(Status.INFO, strValue+" selected in "+strFieldName+" dropdown list field.");
				screenShotsForSteps();
			} catch (Exception e) {
				test.log(Status.FAIL, "Failed to set "+strValue+" in "+strFieldName+" dropdown field. "+e.getMessage());
				try {
					test.addScreenCaptureFromPath(reports.captureScreen(driver));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}


	//To highlight the webelement on UI
	
	public void highlighter(WebElement webEle) {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='2px groove yellow'",	webEle);

	}
	
	//To Click on the webelement on UI
	
	public void click(WebElement webEle, String strFieldName) {
		try {
			highlighter(webEle);
			waitForBrowserStability();
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", webEle);
			waitForBrowserStability();
			test.log(Status.INFO, "Clicked on element "+ strFieldName);
			screenShotsForSteps();
		} catch (Exception e  ) {
			test.log(Status.FAIL, "Failed to click on the element "+ strFieldName+
					". " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(reports.captureScreen(driver));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	//To Verify if the webelement is present on UI
	
	public boolean isElementPresent(final WebElement element) {

		boolean result = false;
		String strTime = "30";
		int seconds = Integer.parseInt(strTime);

		try {
			ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver webDriver) {
					if (element != null) {
						return true;
					} else {
						return false;
					}
				}
			};

			Wait<WebDriver> wait = new WebDriverWait(driver, seconds);
			try {
				result = wait.until(expectation);
			} catch (Exception e) {
				e.getMessage();	
			}
		} catch (Exception e) {

		}
		return result;
	}

	//To wait until the browser is loaded
	
	public void waitForBrowserStability() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver wdriver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState"
						).equals("complete");
			}
		});
	}
}
