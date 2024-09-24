package com.staar.objectrepositoryutil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * obj repo of shipments list page
 * @author Ashutosh
 */
public class ShipmentsListPage {
	
	/**
	 * declaration of the elements
	 */
	WebDriver driver;
	
	@FindBy(name="search_ref_exp")
	private WebElement sh_ref_filter_tbx;
	
	@FindBy(xpath = "//td[@class='nowraponall']/descendant::a")
	private WebElement refId_filter_result;
	
	/**
	 * initialization of the elements
	 * @param driver
	 */
	public ShipmentsListPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @return the sh_ref_filter_tbx
	 */
	public WebElement getSh_ref_filter_tbx() {
		return sh_ref_filter_tbx;
	}

	/**
	 * @return the refId_filter_result
	 */
	public WebElement getRefId_filter_result() {
		return refId_filter_result;
	}
	
}
