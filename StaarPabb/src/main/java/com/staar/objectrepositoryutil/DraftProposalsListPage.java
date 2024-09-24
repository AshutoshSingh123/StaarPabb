package com.staar.objectrepositoryutil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * obj repo of draft proposal list page
 * @author Ashutosh
 */
public class DraftProposalsListPage {
	
	/**
	 * declaration of elements
	 */
	WebDriver driver;
	
	@FindBy(name="search_ref")
	private WebElement refID_filter_searchbox;
	
	/**
	 * initialization of elements
	 * @param driver
	 */
	public DraftProposalsListPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	/**utilization of elements
	 * @return the refID_filter_searchbox
	 */
	public WebElement getRefID_filter_searchbox() {
		return refID_filter_searchbox;
	}
	
}
