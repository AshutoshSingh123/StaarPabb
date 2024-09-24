package com.staar.objectrepositoryutil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * obj repo of services page
 * @author Ashutosh
 */
public class ServicesPage {
	
	/**
	 * declaration of the elements
	 */
	WebDriver driver;
	
	@FindBy(linkText = "List")
	private WebElement sh_list_link;
	
	/**
	 * initialization of the elements
	 * @param driver
	 */
	public ServicesPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @return the sh_list_link
	 */
	public WebElement getSh_list_link() {
		return sh_list_link;
	}
	
}
