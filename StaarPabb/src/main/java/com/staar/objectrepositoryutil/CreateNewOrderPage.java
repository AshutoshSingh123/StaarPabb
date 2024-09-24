package com.staar.objectrepositoryutil;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * obj repo of the create new order page
 */
public class CreateNewOrderPage {
	
	/**
	 * declaration of the elemets
	 */
	WebDriver driver;
	
	@FindBy(name="ref_client")
	private WebElement ref_cust_tbx;
	
	@FindBy(xpath = "//span[text()='Select a third party']")
	private WebElement third_party_selection_container;
	
	@FindBy(xpath = "//input[@value='Create draft']")
	private WebElement create_draft_button;
	
	/**
	 * initialization of the elements
	 * @param driver
	 */
	public CreateNewOrderPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * business method for creating an order
	 * @param third_party
	 * @param ref_cust
	 */
	public void createOrder(String third_party, String ref_cust) {
		ref_cust_tbx.sendKeys(ref_cust);
		third_party_selection_container.click();
		driver.switchTo().activeElement().sendKeys(third_party+Keys.ENTER);
		create_draft_button.click();
	}
}
