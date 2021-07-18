package com.jupiter.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.Status;


/*  Function Written By - Ramya Nimmagadda
 *  Purpose - Object Repository and Methods related to CartPage 
 */


public class CartPage extends Common{


	public CartPage(WebDriver driver) {
		
		super(driver);
	}
	
	public boolean getIteminTheCart(String item) {
		return driver.findElement(By.xpath("//td[contains(.,'" + item + "')]")).isDisplayed();
	}

	public String getCartUpdate() {
		String count =  driver.findElement(By.xpath("//a[@href='#/cart']")).getText();
		return count;
	}
	
     public double getPrice(String toyName){
		  String toyPrice = driver.findElement(By.xpath("//td[@class = 'ng-binding'][text()='" + toyName + "']//following::td[1]")).getText().replace("$","");
		  return Double.parseDouble(toyPrice);	
	}
	  
	 public double getSubtotal(String toyName){ 
		  String toySubTotal = driver.findElement(By.xpath("//td[@class = 'ng-binding'][text()='" + toyName + "']//following::td[3]")).getText().replace("$","");
		  return Double.parseDouble(toySubTotal);
		}
	  
	  public int getQuantity(String toyName){
		  String toyQty = driver.findElement(By.xpath("//td[@class = 'ng-binding'][text()='" + toyName + "']//following::td[2]/input")).getAttribute("value");
		  return Integer.parseInt(toyQty);	
		}
	  
	 public double getTotal() {
		String total = 	driver.findElement(By.xpath("//strong[@class = 'total ng-binding']")).getText().replace("Total:","");
		return Double.parseDouble(total);
		}
		

/*
//To verify the Product Subtotal for the products added to Cart
	public void VerifyProductSubtotal() {
		for(int i=0; i <= toyName.length-1; i++) {
		double	price = getPrice(toyName[i]);
		int quant = getQuantity(toyName[i]);
		double subTotal = (price * quant) ;
		    Assert.assertEquals(subTotal, getSubtotal(toyName[i]));
			test.log(Status.PASS, "SubTotal for the product Matches");
			test.log(Status.INFO, "Sub Total: "+subTotal+"  -->  Toy Name: "+toyName[i]);
	    }
		
	}

//To verify the Total Price for the products added to Cart
	
   public void VerifyTotal() {
		double  expectTotal = 0.00;  
		for(int i=0; i <= toyName.length-1; i++) {
			double subTotal = getSubtotal(toyName[i]);
			expectTotal = (subTotal+expectTotal);
			System.out.println(expectTotal);
		}
		Assert.assertEquals(expectTotal, getTotal());
		test.log(Status.PASS, "Total price for the products in the cart Matching");
		
	}
	*/
}
