package com.staar.objectrepositoryutil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * this class is the obj repo of the commerce page
 * @author Ashutosh
 */
public class CommercePage {
	
	/**
	 *decalaration of the elements inside the commerce page
	 */
	WebDriver driver;
	
	@FindBy(linkText = "New proposal")
	private WebElement new_prop_link;
	
	@FindBy(xpath="//a[@title='Commercial proposals']/../following-sibling::div/a[text()='List']")
	private WebElement prop_list_link;
	
	@FindBy(linkText = "Drafts")
	private WebElement draft_prop_link;
	
	@FindBy(linkText ="Sales Orders")
	private WebElement sales_orders_link;
	
	@FindBy(linkText = "New order")
	private WebElement new_order_link;
	
	@FindBy(xpath = "//a[@title='Sales Orders']/../following-sibling::div/a[text()='List']")
	private WebElement order_list_link;
	
	/**
	 * declaration of the elements, inside the constructor
	 * @param driver
	 */
	public CommercePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	/**
	 * @return the new_prop_link
	 */
	public WebElement getNew_prop_link() {
		return new_prop_link;
	}


	/**
	 * @return the list_link
	 */
	public WebElement getProp_list_link() {
		return prop_list_link;
	}


	/**
	 * @return the draft_prop_link
	 */
	public WebElement getDraft_prop_link() {
		return draft_prop_link;
	}


	/**
	 * @return the sales_orders_link
	 */
	public WebElement getSales_orders_link() {
		return sales_orders_link;
	}


	/**
	 * @return the new_order_link
	 */
	public WebElement getNew_order_link() {
		return new_order_link;
	}


	/**
	 * @return the order_list_link
	 */
	public WebElement getOrder_list_link() {
		return order_list_link;
	}

}
