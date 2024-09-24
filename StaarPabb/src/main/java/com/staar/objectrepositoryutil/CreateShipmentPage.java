package com.staar.objectrepositoryutil;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * obj repo of create shipments page
 * @author Ashutosh
 */
public class CreateShipmentPage {
	
	/**
	 * declaration of elements
	 */
	WebDriver driver;

	@FindBy(xpath = "//td[text()='Delivery method']/following-sibling::td//span[@role='combobox']")
	private WebElement shipping_method_select_box;

	@FindBy(xpath = "//input[contains(@id,'qtyasked')]/parent::td")
	private List<WebElement> qty_ordered_value;

	@FindBy(xpath = "//input[@class='qtyl right']")
	private List<WebElement> qty_to_ship_tbx;

	@FindBy(xpath = "//input[@class='button button-add ']")
	private WebElement create_shipment_button;

	@FindBy(xpath = "//tr[@class='oddeven']//span[contains(@class,'fas')]")
	private List<WebElement> prod_or_ser_icon;
	
	/**
	 * initialization of elements
	 * @param driver
	 */
	public CreateShipmentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

/**
	 * @return the shipping_method_select_box
	 */
	public WebElement getShipping_method_select_box() {
		return shipping_method_select_box;
	}



	//	int size_qty_to_ship_ele=qty_to_ship_tbx.size();
	int j = 0;
	
	/**
	 * business method to create a new shipment
	 * @param shipping_method
	 */
	public void createShipment(String shipping_method) {
		shipping_method_select_box.click();
		driver.switchTo().activeElement().sendKeys(shipping_method+Keys.ENTER);
		
		  for(int i=0;i<qty_ordered_value.size();i++) {
			  String qty = qty_ordered_value.get(i).getText();
			  if(prod_or_ser_icon.get(i).getAttribute("title").equals("Product") /*&& j<size_qty_to_ship_ele*/){ 
				  qty_to_ship_tbx.get(j).clear();
				  qty_to_ship_tbx.get(j++).sendKeys(qty); 
				  } 
			  }
		create_shipment_button.click();
	}
}
