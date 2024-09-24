package com.staar.objectrepositoryutil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * obj repo of the sales order info page
 * @author Ashutosh
 */
public class SalesOrderInfoPage {
	
	/**
	 * declaration of the elements
	 */
	WebDriver driver;
	
	@FindBy(xpath = "//div[@class='statusref']")
	private WebElement badge;
	
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
	
	@FindBy(linkText = "Shipments")
	private WebElement shipments_topnav;
	
	@FindBy(linkText = "CREATE SHIPMENT")
	private WebElement create_shipment_btn;
	
	/**
	 * initialization of the elements
	 * @param driver
	 */
	public SalesOrderInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * @return the shipments_topnav
	 */
	public WebElement getShipments_topnav() {
		return shipments_topnav;
	}
	
	
	/**
	 * @return the create_shipment_btn
	 */
	public WebElement getCreate_shipment_btn() {
		return create_shipment_btn;
	}
	
	
	/**
	 * @return the badge
	 */
	public WebElement getBadge() {
		return badge;
	}

	/**
	 * method to add a product to sales order with unit price and qty
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
	 * method to add a service to sales order
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
	 * method to validate an order
	 */
	public void validateOrder() {
		validate_button.click();
		validate_yes_btn.click();
	}
	
}
