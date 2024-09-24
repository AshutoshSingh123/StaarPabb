package com.staar.objectrepositoryutil;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * this class is the object repository of the login page in the application
 * @author Ashutosh
 */
public class LoginPage {
	
	/**
	 * initialization of the elements
	 */
	WebDriver driver;
	
	/**
	 * username textbox element
	 */
	@FindBy(id="username")
	private WebElement usernameTbx;
	
	/**
	 * password textbox element
	 */
	@FindBy(id="password")
	private WebElement pwdTbx;
	
	/**
	 * loginbutton element
	 */
	@FindBy(xpath = "//div[@id='login-submit-wrapper']/input")
	private WebElement loginBtn;
	
	/**
	 * 
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @return the usernameTbx
	 */
	public WebElement getUsernameTbx() {
		return usernameTbx;
	}

	/**
	 * @return the pwdTbx
	 */
	public WebElement getPwdTbx() {
		return pwdTbx;
	}

	/**
	 * @return the loginBtn
	 */
	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	/**
	 * this method helps to login into the application
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToApp(String url, String username, String password) {
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		usernameTbx.sendKeys(username);
		pwdTbx.sendKeys(password);
		loginBtn.click();
	}
}
