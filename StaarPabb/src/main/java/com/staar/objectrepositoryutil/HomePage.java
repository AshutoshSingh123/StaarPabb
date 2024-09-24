package com.staar.objectrepositoryutil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * obj repo of home page
 * @author Ashutosh
 */
public class HomePage {
	
	/**
	 * declaration of the elements
	 */
	WebDriver driver;
	
	@FindBy(id="mainmenutd_companies")
	private WebElement third_party_tab;
	
	@FindBy(id="mainmenutd_products")
	private WebElement services_tab;
	
	@FindBy(id="mainmenutd_commercial")
	private WebElement commerce_tab;
	
	@FindBy(id="topmenu-login-dropdown")
	private WebElement login_dd;
	
	@FindBy(xpath = "//span[text()='Logout']")
	private WebElement logout_button;
	
	/**
	 * initialization of the elements
	 * @param driver
	 */
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @return the third_party_tab
	 */
	public WebElement getThird_party_tab() {
		return third_party_tab;
	}

	/**
	 * @return the services_tab
	 */
	public WebElement getServices_tab() {
		return services_tab;
	}

	/**
	 * @return the commerce_tab
	 */
	public WebElement getCommerce_tab() {
		return commerce_tab;
	}

	/**
	 * @return the login_dd
	 */
	public WebElement getLogin_dd() {
		return login_dd;
	}

	/**
	 * @return the logout_button
	 */
	public WebElement getLogout_button() {
		return logout_button;
	}
	
	/**
	 * method to logout from application
	 */
	public void logout() {
		getLogin_dd().click();
		getLogout_button().click();
	}
}
