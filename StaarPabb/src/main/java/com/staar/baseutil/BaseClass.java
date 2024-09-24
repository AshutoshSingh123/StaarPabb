package com.staar.baseutil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.staar.fileutility.PropertyFileUtil;
import com.staar.objectrepositoryutil.HomePage;
import com.staar.objectrepositoryutil.LoginPage;

/**
 * Base Class contains configuration annotations and their methods
 * @author Ashutosh
 * 
 */
public class BaseClass {
	
	/**
	 * declaration of webdriver ref var and creating ref var of necessary utility classes
	 */
	public WebDriver driver;
	public static WebDriver sdriver;
	PropertyFileUtil pLib=new PropertyFileUtil();
	
	/**executes before execution of a suite
	 * @author Ashutosh
	 * has @BeforeSuite Annotation and group keys
	 */
	@BeforeSuite(groups= {"regressionTest","smokeTest"})
	public void beforeSuite() {
		
	}
	/**executes after execution of a suite
	 * @author Ashutosh
	 *  has @AfterSuite Annotation and group keys
	 */
	@AfterSuite(groups= {"regressionTest","smokeTest"})
	public void afterSuite() {
		
	}
	
	/**executes before execution of a test class
	 * @author Ashutosh
	 *  has @BeforeClass Annotation and group keys
	 * @throws Throwable 
	 */
	//@Parameters("BROWSER")
	@BeforeClass(groups= {"regressionTest","smokeTest"})
	public void beforeClass(/*String browser*/) throws Throwable {
		
		/**
		 * opening the browser 
		 */
		String BROWSER=/*browser;*/
				System.getProperty("browser", pLib.getDataFromProperty("browser"));
		switch(BROWSER) {
		case "chrome": driver=new ChromeDriver();break;
		case "firefox": driver=new FirefoxDriver();break;
		case "edge": driver=new EdgeDriver();break;
		case "safari": driver=new SafariDriver();break;
		default:driver=new ChromeDriver();
		}
		sdriver=driver;
	}
	
	/**executes after execution of a test class
	 * @author Ashutosh
	 *  has @AfterClass Annotation and group keys
	 */
	@AfterClass(groups= {"regressionTest","smokeTest"})
	public void afterClass() {
		driver.quit();
	}
	
	/**executes before execution of a test method
	 * used for logging in to app
	 * @author Ashutosh
	 *  has @BeforeMethod Annotation and group keys
	 * @throws Throwable 
	 */
	@BeforeMethod(groups= {"regressionTest","smokeTest"})
	public void beforeMethod() throws Throwable {
		/**
		 * extracting config data from property file
		 */
		String URL= pLib.getDataFromProperty("url");
		String USERNAME= pLib.getDataFromProperty("username");
		String PASSWORD= pLib.getDataFromProperty("password");
		/**
		 * creating object of LoginPage
		 * and calling logintoApp() method to login to app
		 */
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(URL,USERNAME,PASSWORD);
	}
	
	/**executes after execution of a test method
	 * used for logout
	 * @author Ashutosh
	 *  has @AfterMethod Annotation and group keys
	 */
	@AfterMethod(groups= {"regressionTest","smokeTest"})
	public void afterMethod() {
		/**
		 * creating object of HomePage class
		 * and calling logout() method
		 */
		HomePage hp=new HomePage(driver);
		hp.logout();
	}
}
