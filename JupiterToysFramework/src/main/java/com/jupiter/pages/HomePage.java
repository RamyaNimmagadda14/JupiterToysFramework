package com.jupiter.pages;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


/*  Function Written By - Ramya Nimmagadda
 *  Purpose - Object Repository and Methods related to Home Page 
 */


public class HomePage extends Common{


	public HomePage( WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//a[contains(text(), 'Contact')]") 	private WebElement lnkContact;
	@FindBy(xpath="//a[@class='brand']") 	private WebElement imgBrand;
	@FindBy(xpath="//a[contains(text(), 'Shop')]") 	private WebElement lnkShop;

	// go to Contact page
	public void gotoContactPage() {
		click(imgBrand, "Jupiter Toys logo");
		click(lnkContact, "Contact");
		waitForBrowserStability();
	}
	
	// go to Shop page
		public void gotoShopPage() {
			click(lnkShop, "Shop");
			waitForBrowserStability();
		}

}
