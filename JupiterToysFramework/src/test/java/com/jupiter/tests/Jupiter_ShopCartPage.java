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

public class Jupiter_ShopCartPage extends Base{

	public void getScreenshot() throws IOException {
		test.addScreenCaptureFromPath(reports.captureScreen(driver));
	}

/* 
	1.	From the home page go to shop page
	2.	Click buy button 2 times on “Funny Cow”
	3.	Click buy button 1 time on “Fluffy Bunny”
	4.	Click the cart menu
	5.	Verify the items are in the cart

 */
	
	@Test(testName="TC_03_VerifyTheShoppingCartData")
	public void TC_03_VerifyTheShoppingCartData() throws IOException{

		test.assignCategory("SanitySuite");

		try {
			test.log(Status.INFO, "Verify the shopping cart data");
			HomePage homePage = new HomePage(driver);
			homePage.gotoShopPage();
			ShopPage shopPage = new ShopPage(driver);
			shopPage.buyFunnyCow();
			shopPage.buyFunnyCow();
			shopPage.buyFluffyBunny();
			shopPage.gotoCart();
			CartPage cartPage = new CartPage(driver);
			String cartCount = cartPage.getCartUpdate();
			Assert.assertTrue(cartCount.contains("3"));		
			test.log(Status.PASS, "Count of Cart items verified successfully");
			Assert.assertTrue(cartPage.getIteminTheCart("Bunny"));
			Assert.assertTrue(cartPage.getIteminTheCart("Funny Cow"));
			test.log(Status.PASS, "Cart items verified successfully");
		
			
		} catch (Exception e) {
			test.log(Status.FAIL, "unable to  verify Shopping cart data"+e.getMessage());
			getScreenshot();
		}
	}
	
/*
	1.	Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear
	2.	Go to the cart page
	3.	Verify the price for each product
	4.	Verify that each product’s sub total = product price * quantity
	5.	Verify that total = sum(sub totals)
*/

 
	
	@Test(testName="TC_04_VerifyPriceDetailsOfCartData")
	public void TC_04_VerifyPriceDetailsOfCartData() throws IOException{

		test.assignCategory("SanitySuite");	
		String toyName[] =  {" Stuffed Frog"," Fluffy Bunny"," Valentine Bear"};
		//int noOfProducts[] =  {2,5,3};
		double toyPrice[] = {10.99,9.99,14.99};
		double  expectTotal = 0.00;
		
		try {
			test.log(Status.INFO, "Verify the shopping cart data");
			
			HomePage homePage = new HomePage(driver);
			driver.navigate().refresh();
			homePage.gotoShopPage();
			ShopPage shopPage = new ShopPage(driver);
			shopPage.buyStuffedFrog();
			shopPage.buyStuffedFrog();
			shopPage.buyFluffyBunny();
			shopPage.buyFluffyBunny();
			shopPage.buyFluffyBunny();
			shopPage.buyFluffyBunny();
			shopPage.buyFluffyBunny();
			shopPage.buyValentinesBear();
			shopPage.buyValentinesBear();
			shopPage.buyValentinesBear();
			shopPage.gotoCart();
			CartPage cartPage = new CartPage(driver);
		  
			//To verify the price, sub total and total of the products added to Cart  
		    for(int i=0; i <= toyName.length-1; i++) {
		    	double	price = cartPage.getPrice(toyName[i]);
				int quant = cartPage.getQuantity(toyName[i]);
				double subTotal = (price * quant) ;
				Assert.assertEquals(toyPrice[i], cartPage.getPrice(toyName[i]));
				test.log(Status.INFO, "Toy Price: "+toyPrice[i]+"  -->  Toy Name: "+toyName[i]);
				test.log(Status.PASS, "Price of the Product Matches");
				Assert.assertEquals(subTotal, cartPage.getSubtotal(toyName[i]));
				test.log(Status.INFO, "Sub Total: "+subTotal+"  -->  Toy Name: "+toyName[i]);
				test.log(Status.PASS, "SubTotal for the product Matches");
				expectTotal = (subTotal+expectTotal);
				}
		    Assert.assertEquals(expectTotal, cartPage.getTotal());
			test.log(Status.PASS, "Total price for the products in the cart Matching");


			
		} catch (Exception e) {
			test.log(Status.FAIL, "unable to  verify Shopping cart data"+e.getMessage());
			getScreenshot();
		}
	}

}

