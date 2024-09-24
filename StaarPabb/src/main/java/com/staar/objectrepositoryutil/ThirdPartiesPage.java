package com.staar.objectrepositoryutil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * obj repo of the third parties page
 * @author Ashutosh
 */
public class ThirdPartiesPage {
	
	/**
	 * declaration of the elements
	 */
	WebDriver driver;
	
	@FindBy(linkText = "Third-party")
	private WebElement third_parties_leftMenu;
	
	@FindBy(linkText = "New Third Party")
	private WebElement new_third_party_link;
	
	@FindBy(linkText = "List")
	private WebElement list_link;
	
	@FindBy(linkText = "List of Customers")
	private WebElement list_of_customers_link;
	
	/**
	 * initialization of the elements
	 * @param driver
	 */
	public ThirdPartiesPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @return the third_parties_leftMenu
	 */
	public WebElement getThird_parties_leftMenu() {
		return third_parties_leftMenu;
	}

	/**
	 * @return the new_third_party_link
	 */
	public WebElement getNew_third_party_link() {
		return new_third_party_link;
	}

	/**
	 * @return the list_link
	 */
	public WebElement getList_link() {
		return list_link;
	}

	/**
	 * @return the list_of_customers_link
	 */
	public WebElement getList_of_customers_link() {
		return list_of_customers_link;
	}
	
	
}
