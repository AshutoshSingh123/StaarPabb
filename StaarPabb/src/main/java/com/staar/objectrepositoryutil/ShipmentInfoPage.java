package com.staar.objectrepositoryutil;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * obj repo of the shipment info page
 * @author Ashutosh
 */
public class ShipmentInfoPage {
	
	/**
	 * declaration of the elements
	 */
	WebDriver driver;
	
	@FindBy(xpath = "//div[@class='statusref']")
	private WebElement badge;
	
	@FindBy(xpath = "//span[@title='SetSendingMethod']")
	private WebElement modify_shipment_method_icon;
	
	@FindBy(xpath = "//span[contains(@id,'shipping_method')]")
	private WebElement ship_method_container;
	
	@FindBy(xpath = "//input[@value='Modify']")
	private WebElement modify_btn;
	
	@FindBy(xpath = "//td[text()='Shipping method']/ancestor::td/following-sibling::td")
	private WebElement shipping_method_info;
	
	@FindBy(xpath = "//div[contains(@class,'refidpadding')]")
	private WebElement shipment_id_info;
	
	@FindBy(linkText = "VALIDATE")
	private WebElement validate_shipm_btn;
	
	@FindBy(xpath = "//div[@class='ui-dialog-buttonset']/button[text()='Yes']")
	private WebElement validate_yes_btn;
	
	@FindBy(linkText ="SEND EMAIL")
	private WebElement send_email_link;
	
	@FindBy(id="sendto")
	private WebElement send_to_field;
	
	@FindBy(id="addfile")
	private WebElement attach_file_btn;
	
	@FindBy(id="message")
	private WebElement message_tbx;
	
	@FindBy(id="sendmail")
	private WebElement send_email_submit_btn;
	
	@FindBy(xpath = "//div[@class='jnotify-message']")
	private WebElement mail_conf_msg;
	
	@FindBy(xpath = "//a[@class='jnotify-close']")
	private WebElement close_mail_notify;
	
	/**
	 * initialization of the elements
	 * @param driver
	 */
	public ShipmentInfoPage(WebDriver driver) {
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
	 * @return the shipment_id_info
	 */
	public WebElement getShipment_id_info() {
		return shipment_id_info;
	}
	
	/**
	 * @return the modify_shipment_method_icon
	 */
	public WebElement getModify_shipment_method_icon() {
		return modify_shipment_method_icon;
	}
	
	/**
	 * @return the shipping_method_info
	 */
	public WebElement getShipping_method_info() {
		return shipping_method_info;
	}
	
	/**
	 * @return the send_email_btn
	 */
	public WebElement getSend_email_link() {
		return send_email_link;
	}

	/**
	 * @return the send_email_submit_btn
	 */
	public WebElement getSend_email_submit_btn() {
		return send_email_submit_btn;
	}

	/**
	 * @return the mail_conf_msg
	 */
	public WebElement getMail_conf_msg() {
		return mail_conf_msg;
	}

	/**
	 * @return the close_mail_notify
	 */
	public WebElement getClose_mail_notify() {
		return close_mail_notify;
	}

	/**
	 * method to validate a shipment
	 */
	public void validateShipment() {
		validate_shipm_btn.click();
		validate_yes_btn.click();
	}
	
	/**
	 * method to modify shipping method
	 * @param shipping_method
	 */
	public void modifyShippingMthd(String shipping_method) {
		modify_shipment_method_icon.click();
		ship_method_container.click();
		driver.switchTo().activeElement().sendKeys(shipping_method+Keys.ENTER);
		modify_btn.click();
	}
	
	public void sendEmail(String msg,String send_to) {
		send_email_link.click();
		send_to_field.sendKeys(send_to+"@dummymail.com");
		attach_file_btn.click();
		message_tbx.sendKeys(msg);
		send_email_submit_btn.click();
	}
}
