package com.staar.objectrepositoryutil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * obj repo of the proposal info page, post validation
 */
public class ValidatedProposalInfoPage {
	
	/**
	 * declaration of the elements
	 */
	WebDriver driver;
	
	@FindBy(xpath = "//div[@class='statusref']")
	private WebElement badge;
	
	@FindBy(linkText = "SET ACCEPTED/REFUSED")
	private WebElement accept_refuse_prop_button;
	
	@FindBy(id="select2-statut-container")
	private WebElement status_container;
	
	@FindBy(xpath = "//li[text()='Signed']")
	private WebElement select_signed;
	
	@FindBy(xpath = "//li[text()='Not signed']")
	private WebElement select_not_signed;
	
	@FindBy(xpath = "//div[@class='ui-dialog-buttonset']/button[text()='Yes']")
	private WebElement accept_refuse_prop_confirm_yes_btn;
	
	@FindBy(xpath = "//div[@class='ui-dialog-buttonset']/button[text()='No']")
	private WebElement accept_refuse_prop_confirm_no_btn;
	
	/**
	 * initialization of the elements
	 * @param driver
	 */
	public ValidatedProposalInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * @return the badge
	 */
	public WebElement getBadge() {
		return badge;
	}



	/**
	 * method to accept a proposal
	 */
	public void acceptProposal() {
		accept_refuse_prop_button.click();
		status_container.click();
		select_signed.click();
		accept_refuse_prop_confirm_yes_btn.click();
	}
	
	/**
	 * method to reject a proposal
	 */
	public void rejectProposal() {
		accept_refuse_prop_button.click();
		status_container.click();
		select_not_signed.click();
		accept_refuse_prop_confirm_yes_btn.click();
	}
}
