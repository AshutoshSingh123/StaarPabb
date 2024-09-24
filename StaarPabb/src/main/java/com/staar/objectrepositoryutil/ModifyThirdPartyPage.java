package com.staar.objectrepositoryutil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * obj repo of the modify third-party page
 * @@author Ashutosh
 */
public class ModifyThirdPartyPage {
	
	/**
	 * declaration of the elements
	 */
	WebDriver driver;
	
	@FindBy(id="address")
	private WebElement address_textbox;
	
	@FindBy(xpath = "//input[@class='button button-save ']")
	private WebElement save_button;
	
	/**
	 * initialization of the elements
	 * @param driver
	 */
	public ModifyThirdPartyPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @return the address_textbox
	 */
	public WebElement getAddress_textbox() {
		return address_textbox;
	}

	/**
	 * @return the save_button
	 */
	public WebElement getSave_button() {
		return save_button;
	}
	
	
}
