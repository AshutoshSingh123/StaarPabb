package com.staar.objectrepositoryutil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * obj repo of third parties info page
 * @author Ashutosh
 */
public class ThirdPartyInfoPage {
		
		/**
		 * declaration of the elements
		 */
		WebDriver driver;
		
		@FindBy(id="card")
		private WebElement third_party_card;
		
		@FindBy(xpath = "//span[contains(@class,'badge-status')]")
		private WebElement badge;
		
		@FindBy(xpath = "//div[@class='address inline-block']")
		private WebElement address_block;
		
		@FindBy(xpath = "//td[text()='Customer Code']/following-sibling::td")
		private WebElement cust_code;
		
		@FindBy(xpath = "//a[@class='butAction']")
		private WebElement modify_btn;
		
		/**
		 * initialization
		 * @param driver
		 */
		public ThirdPartyInfoPage(WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}

		/**
		 * @return the third_party_card
		 */
		public WebElement getThird_party_card() {
			return third_party_card;
		}

		/**
		 * @return the badge
		 */
		public WebElement getBadge() {
			return badge;
		}

		/**
		 * @return the cust_code
		 */
		public WebElement getCust_code() {
			return cust_code;
		}

		/**
		 * @return the modify_btn
		 */
		public WebElement getModify_btn() {
			return modify_btn;
		}

		/**
		 * @return the address_block
		 */
		public WebElement getAddress_block() {
			return address_block;
		}
		
		
}
