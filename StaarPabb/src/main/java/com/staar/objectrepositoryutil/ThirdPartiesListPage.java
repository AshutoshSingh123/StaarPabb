package com.staar.objectrepositoryutil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * obj repo of the third parties list page
 * @author Ashutosh
 */
public class ThirdPartiesListPage {
	
	/**
	 * declaration of the elements
	 */
	WebDriver driver;
	
	@FindBy(name="search_nom")
	private WebElement third_party_name_filter_searchbox;
	
	@FindBy(name="search_customer_code")
	private WebElement cust_code_filter_searchbox;
	
	@FindBy(xpath = "//table[contains(@class,'tagtable')]")
	private WebElement cust_list_table;
	
	/**
	 * initialization of the elements
	 * @param driver
	 */
	public ThirdPartiesListPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @return the third_party_name_filter_searchbox
	 */
	public WebElement getThird_party_name_filter_searchbox() {
		return third_party_name_filter_searchbox;
	}

	/**
	 * @return the cust_code_filter_searchbox
	 */
	public WebElement getCust_code_filter_searchbox() {
		return cust_code_filter_searchbox;
	}

	/**
	 * @return the cust_list_table
	 */
	public WebElement getCust_list_table() {
		return cust_list_table;
	}
	
	
}
