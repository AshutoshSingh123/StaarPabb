package com.staar.objectrepositoryutil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * obj repo of draft proposal info page
 * @author Ashutosh
 */
public class DraftProposalInfoPage {
	
	/**
	 * declaration of elements
	 */
	WebDriver driver;
	
	@FindBy(xpath = "//div[contains(@class,'refidpadding')]")
	private WebElement proposal_draft_id;
	
	@FindBy(xpath = "//span[@id='select2-select_type-container']")
	private WebElement type_selection_container;	
	
	@FindBy(xpath = "//li[text()='Product']")
	private WebElement product_select_opt;
	
	@FindBy(xpath = "//li[text()='Service']")
	private WebElement service_select_opt;
	
	@FindBy(id="dp_desc")
	private WebElement prod_or_service_desc_tbx;
	
	@FindBy(id="price_ht")
	private WebElement unit_price_tbx;
	
	@FindBy(id="qty")
	private WebElement qty_tbx;
	
	@FindBy(id="addline")
	private WebElement add_prod_button;
	
	@FindBy(linkText = "VALIDATE")
	private WebElement validate_button;
	
	@FindBy(xpath = "//div[@class='ui-dialog-buttonset']/button[text()='Yes']")
	private WebElement validate_yes_btn;
	
	/**
	 * initialization of elements
	 * @param driver
	 */
	public DraftProposalInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @return the proposal_draft_id
	 */
	public WebElement getProposal_draft_id() {
		return proposal_draft_id;
	}

	/**
	 * @return the type_selection_container
	 */
	public WebElement getType_selection_container() {
		return type_selection_container;
	}

	/**
	 * @return the product_select_opt
	 */
	public WebElement getProduct_select_opt() {
		return product_select_opt;
	}

	/**
	 * @return the service_select_opt
	 */
	public WebElement getService_select_opt() {
		return service_select_opt;
	}
	
	
	
	/**
	 * @return the prod_or_service_desc_tbx
	 */
	public WebElement getProd_or_service_desc_tbx() {
		return prod_or_service_desc_tbx;
	}

	/**
	 * @return the unit_price_tbx
	 */
	public WebElement getUnit_price_tbx() {
		return unit_price_tbx;
	}

	/**
	 * @return the qty_tbx
	 */
	public WebElement getQty_tbx() {
		return qty_tbx;
	}

	/**
	 * @return the add_prod_button
	 */
	public WebElement getAdd_prod_button() {
		return add_prod_button;
	}
	
	/**
	 * @return the validate_button
	 */
	public WebElement getValidate_button() {
		return validate_button;
	}
	
	/**
	 * business method to add product to proposal
	 * @param product
	 * @param unit_price
	 * @param qty
	 */
	public void addProduct(String product, String unit_price,String qty) {
		type_selection_container.click();
		product_select_opt.click();
		prod_or_service_desc_tbx.sendKeys(product);
		unit_price_tbx.sendKeys(unit_price);
		qty_tbx.clear();qty_tbx.sendKeys(qty);
		add_prod_button.click();
	}
	
	/**
	 * business method to add service to a proposal
	 * @param service
	 * @param unit_price
	 * @param qty
	 */
	public void addService(String service, String unit_price,String qty) {
		type_selection_container.click();
		service_select_opt.click();
		prod_or_service_desc_tbx.sendKeys(service);
		unit_price_tbx.sendKeys(unit_price);
		qty_tbx.clear();qty_tbx.sendKeys(qty);
		add_prod_button.click();
	}
	
	/**
	 * method to validate a proposal
	 */
	public void validateProposal() {
		validate_button.click();
		validate_yes_btn.click();
	}
}
