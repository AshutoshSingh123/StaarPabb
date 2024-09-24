package com.staar.objectrepositoryutil;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * obj repo of create new proposal page
 */
public class CreateNewProposalPage {
	
	/**
	 * decalaration of the elements
	 */
	WebDriver driver;
	
	@FindBy(xpath = "//input[@name='ref_client']")
	private WebElement ref_cust_tbx;
	
	@FindBy(xpath = "//span[@title='Select a third party']")
	private WebElement third_party_selBox;
	
	@FindBy(xpath = "//input[@value='Create draft']")
	private WebElement create_draft_button;
	
	/**
	 * initialization of elements
	 * @param driver
	 */
	public CreateNewProposalPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @return the ref_cust_tbx
	 */
	public WebElement getRef_cust_tbx() {
		return ref_cust_tbx;
	}

	/**
	 * @return the third_party_selBox
	 */
	public WebElement getThird_party_selBox() {
		return third_party_selBox;
	}

	/**
	 * @return the create_draft_button
	 */
	public WebElement getCreate_draft_button() {
		return create_draft_button;
	}
	
	/**
	 * business method to create new proposal
	 * @param ref_cust
	 * @param third_party
	 */
	public void createNewProposal(String ref_cust, String third_party) {
		ref_cust_tbx.sendKeys(ref_cust);
		third_party_selBox.click();
		driver.switchTo().activeElement().sendKeys(third_party+Keys.ENTER);
		create_draft_button.click();
	}
}
