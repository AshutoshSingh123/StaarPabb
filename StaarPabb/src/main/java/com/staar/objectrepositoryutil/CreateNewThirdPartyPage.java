package com.staar.objectrepositoryutil;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * this class is the object repository of Create new third party page in the application
 *@author Ashutosh
 */
public class CreateNewThirdPartyPage {

	WebDriver driver;
	
	/**
	 * third party name field
	 */
	@FindBy(xpath = "//input[@name='name']")
	private WebElement third_party_name_tbx;
	
	/**
	 * customer/prospect select box
	 */
	@FindBy(xpath = "//span[@aria-labelledby='select2-customerprospect-container']")
	private WebElement cust_prospect_selection_box;

	/**
	 * create third party button
	 */
	@FindBy(xpath = "//input[@value='Create third party']")
	private WebElement create_third_party_button;

	/**
	 * prospect option in selection box
	 */
	@FindBy(xpath = "//li[text()='Prospect']")
	private WebElement prospect_opt;

	/**
	 * prospect/customer option in selection box
	 */
	@FindBy(xpath = "//li[text()='Prospect / Customer']")
	private WebElement prospect_or_customer_opt;

	/**
	 * customer option in selection box
	 */
	@FindBy(xpath = "//li[text()='Customer']")
	private WebElement customer_opt;

	/**
	 * not prospect/nor customer option in selection box
	 */
	@FindBy(xpath = "//li[text()='Not prospect, nor customer']")
	private WebElement notProspect_norCustomer_opt;

	/**constructor
	 * initialization of elements happens here
	 * @param driver
	 */
	public CreateNewThirdPartyPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @return the third_party_name_tbx
	 */
	public WebElement getThird_party_name_tbx() {
		return third_party_name_tbx;
	}

	/**
	 * @return the cust_prospect_selection_box
	 */
	public WebElement getCust_prospect_selection_box() {
		return cust_prospect_selection_box;
	}

	/**
	 * @return the create_third_party_button
	 */
	public WebElement getCreate_third_party_button() {
		return create_third_party_button;
	}

	/**
	 * @return the prospect_opt
	 */
	public WebElement getProspect_opt() {
		return prospect_opt;
	}

	/**
	 * @return the prospect_or_customer_opt
	 */
	public WebElement getProspect_or_customer_opt() {
		return prospect_or_customer_opt;
	}

	/**
	 * @return the customer_opt
	 */
	public WebElement getCustomer_opt() {
		return customer_opt;
	}

	/**
	 * @return the norProspect_norCustomer_opt
	 */
	public WebElement getNotProspect_norCustomer_opt() {
		return notProspect_norCustomer_opt;
	}
	
	/**
	 * this business method creates a new third party with given third party name and third party type
	 * @author Ashutosh
	 * @param third_party_name
	 * @param third_party_type
	 */
	public void createNewThirdParty(String third_party_name,String third_party_type) {
		third_party_name_tbx.sendKeys(third_party_name);
		cust_prospect_selection_box.click();
		switch(third_party_type) {
		case "Customer":customer_opt.click(); break;
		case "Prospect":prospect_opt.click(); break;
		case "Prospect / Customer":prospect_or_customer_opt.click(); break;
		case "Not prospect, nor customer":notProspect_norCustomer_opt.click(); break;
		}
		//		driver.switchTo().activeElement().sendKeys(third_party_type+Keys.ENTER);
		create_third_party_button.click();
	}
}
