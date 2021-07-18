package com.jupiter.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/*  Function Written By - Ramya Nimmagadda
 *  Purpose - Object Repository and Methods related to Shop Page 
 */

public class ShopPage extends Common{


	public ShopPage(WebDriver driver) {
		super(driver);
	}

	public String strActualText="";
	public String strExpectedText="";

	@FindBy(xpath = "(//div/h4[text()='Funny Cow']/ following :: p/a[text()='Buy'])[1]")	private WebElement btnFunnyCow_Buy;
	@FindBy(xpath = "(//div/h4[text()='Fluffy Bunny'] / following :: p/a[text()='Buy'])[1]")	private WebElement btnFluffyBunny_Buy;
	@FindBy(xpath = "(//div/h4[text()='Stuffed Frog'] / following :: p/a[text()='Buy'])[1]")	private WebElement btnStuffedFrog_Buy;
	@FindBy(xpath = "(//div/h4[text()='Valentine Bear'] / following :: p/a[text()='Buy'])[1]")	private WebElement btnValentineBear_Buy;
	@FindBy(xpath="//a[contains(text(), 'Cart')]") 	private WebElement lnkCart;
	

	
	public void buyFunnyCow() {
		click(btnFunnyCow_Buy, "Funny Cow - Buy");
		waitForBrowserStability();
	}

	public void buyFluffyBunny() {
		click(btnFluffyBunny_Buy, "Fluffy Bunny - Buy");
		waitForBrowserStability();
	}
	
	public void gotoCart() {
		click(lnkCart, "Cart");
		waitForBrowserStability();
	}
	
	public void buyStuffedFrog() {
		click(btnStuffedFrog_Buy, "Stuffed Frog - Buy");
		waitForBrowserStability();
	}
	
	public void buyValentinesBear() {
		click(btnValentineBear_Buy, "Valentine Bear - Buy");
		waitForBrowserStability();
	}


}
