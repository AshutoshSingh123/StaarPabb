package com.staar.modules.thirdparty;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.staar.baseutil.BaseClass;
import com.staar.fileutility.ExcelUtilityClass;
import com.staar.javautil.JavaUtilClass;
import com.staar.objectrepositoryutil.CreateNewThirdPartyPage;
import com.staar.objectrepositoryutil.HomePage;
import com.staar.objectrepositoryutil.ModifyThirdPartyPage;
import com.staar.objectrepositoryutil.ThirdPartiesListPage;
import com.staar.objectrepositoryutil.ThirdPartiesPage;
import com.staar.objectrepositoryutil.ThirdPartyInfoPage;
import com.staar.webdriverutility.UtilityClassObject;

/**
 * this test class contains test cases/methods relating to ThirdParty Module
 * @author Ashutosh
 */
@Listeners(com.staar.listenerutil.ListenerImpClass.class)
public class ThirdPartyTest extends BaseClass{
	
	/**this test methods creates a third party
	 * @author Ashutosh
	 * @throws Throwable 
	 * 
	 */
	  @Test(groups= {"smokeTest","regressionTest"})
	  void createThirdParty() throws Throwable {
		/**
		 * create object of utility class
		 * get excel data 
		 * declaration of utility files
		 */
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		ExcelUtilityClass eLib=new ExcelUtilityClass();
		JavaUtilClass jLib=new JavaUtilClass();
		String third_party_name = eLib.getExcelData("thirdparty", 1, 0)+jLib.getRandom(2000);
		String third_party_type = eLib.getExcelData("thirdparty", 1, 1);
		
		/**
		 * Creating object of "HomePage" class
		 * clicking on third party major tab
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to homepage");
		HomePage hp=new HomePage(driver);
		UtilityClassObject.getTest().log(Status.INFO, "navigate to third party page");
		hp.getThird_party_tab().click();
		
		Assert.assertEquals(hp.getLogin_dd().isDisplayed(), true);
		UtilityClassObject.getTest().log(Status.INFO, "third party page displayed");
		/**creating object of "ThirdPartiesPage" class and
		 * clicking on "New Third Party Link"
		 */
		ThirdPartiesPage th_parties_pg_obj=new ThirdPartiesPage(driver);
		UtilityClassObject.getTest().log(Status.INFO, "navigate to new third party page");
		th_parties_pg_obj.getNew_third_party_link().click();
		Assert.assertEquals(th_parties_pg_obj.getThird_parties_leftMenu().isDisplayed(), true);
		
		/**creating an object of "CreateNewThirdPartyPage"
		 * and creating a third party as customer type, by calling "createNewThirdParty("third_party_name","third_party_type")" method
		 */
		CreateNewThirdPartyPage new_th_prty_pg_obj=new CreateNewThirdPartyPage(driver);
		UtilityClassObject.getTest().log(Status.INFO, "creating new third party");
		new_th_prty_pg_obj.createNewThirdParty(third_party_name, third_party_type);
		UtilityClassObject.getTest().log(Status.INFO, "third party created");
		Assert.assertEquals(new ThirdPartyInfoPage(driver).getBadge().isDisplayed(), true);
	}
	
	
	
	/**
	 * test method to verify a created third party in the list
	 * @throws Throwable 
	 */
	@Test(groups= {"regressionTest"})
	public void verifyCreatedThirdPartyInListTest() throws Throwable {
		
		/**
		 * get excel data 
		 * declaration of utility files
		 */
		UtilityClassObject.getTest().log(Status.INFO, "fethc data from excel");
		ExcelUtilityClass eLib=new ExcelUtilityClass();
		JavaUtilClass jLib=new JavaUtilClass();
		String third_party_name = eLib.getExcelData("thirdparty", 1, 0)+jLib.getRandom(3000);
		String third_party_type = eLib.getExcelData("thirdparty", 1, 1);
		
		/**
		 * home page
		 */
		HomePage hp=new HomePage(driver);
		Assert.assertTrue(hp.getLogin_dd().isDisplayed());
		UtilityClassObject.getTest().log(Status.INFO, "navigate to home page");
		/**
		 * clicking on third parties major tab
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to third party page");
		hp.getThird_party_tab().click();
		
		ThirdPartiesPage tpp=new ThirdPartiesPage(driver);
		Assert.assertTrue(tpp.getThird_parties_leftMenu().isDisplayed());
		
		/**
		 * clicking on new third party link
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to new third party page");
		tpp.getNew_third_party_link().click();
		CreateNewThirdPartyPage cntp=new CreateNewThirdPartyPage(driver);
		Assert.assertTrue(cntp.getThird_party_name_tbx().isDisplayed());
		
		/**
		 * creating a new third party by calling a business method
		 */
		UtilityClassObject.getTest().log(Status.INFO, "create new third party");
		cntp.createNewThirdParty(third_party_name, third_party_type);
		Assert.assertTrue(new ThirdPartyInfoPage(driver).getBadge().isDisplayed());
		
		/**
		 * clicking on List link
		 */
		UtilityClassObject.getTest().log(Status.INFO, "click on list");
		ThirdPartiesListPage tpl=new ThirdPartiesListPage(driver);
		tpp.getList_link().click();
		Assert.assertTrue(tpl.getCust_list_table().isDisplayed());
		
		/**
		 * searching for the created third party
		 */
		UtilityClassObject.getTest().log(Status.INFO, "search third party");
		tpl.getThird_party_name_filter_searchbox().sendKeys(third_party_name+Keys.ENTER);
		Assert.assertEquals(driver.findElement(By.xpath("//td[@class='tdoverflowmax200']/a")).getText(),third_party_name);
	}
	
	
	/**
	 * this test method is used to get the list of customers
	 */
	@Test(groups= {"smokeTest","regressionTest"})
	public void ListOfCustomers() {
		/**
		 * create the object of homepage
		 * and click on third party major tab
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to home page");
		HomePage hp=new HomePage(driver);
		hp.getThird_party_tab().click();
		
		ThirdPartiesPage tpp=new ThirdPartiesPage(driver);
		Assert.assertEquals(tpp.getList_link().isDisplayed(), true);
		
		/**
		 * click on third parties link and open the third parties page
		 * click on list of customers link
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to cust list page");
		tpp.getList_of_customers_link().click();
		Assert.assertEquals(tpp.getList_of_customers_link().isEnabled(), true);
	}
	
	
	/**
	 * test method to modify a third party
	 * @throws Throwable 
	 */
	@Test(groups= {"regressionTest"})
	public void modifyThirdPartyTest() throws Throwable {
		/**
		 * get excel data
		 */
		UtilityClassObject.getTest().log(Status.INFO, "extract excel data");
		ExcelUtilityClass eLib=new ExcelUtilityClass();
		JavaUtilClass jLib=new JavaUtilClass();
		String third_party_name = eLib.getExcelData("thirdparty", 1, 0)+jLib.getRandom(4000);
		String third_party_type = eLib.getExcelData("thirdparty", 1, 1);
		String third_party_address=eLib.getExcelData("thirdparty", 1, 2);
		
		ThirdPartiesPage tpp=new ThirdPartiesPage(driver);
		
		/**
		 * click on third party tab in home page
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to third party page");
		HomePage hp=new HomePage(driver);
		hp.getThird_party_tab().click();
		Assert.assertTrue(tpp.getThird_parties_leftMenu().isDisplayed());
		/**
		 * nav to create new third party page
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to new third party page");
		tpp.getNew_third_party_link().click();
		
		/**
		 * creating new third party
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create new third party page");
		CreateNewThirdPartyPage cntp=new CreateNewThirdPartyPage(driver);
		cntp.createNewThirdParty(third_party_name, third_party_type);
		
		/**
		 * extracting customer code
		 */
		
		ThirdPartyInfoPage tp_info_pg=new ThirdPartyInfoPage(driver);
		String cust_code= tp_info_pg.getCust_code().getText();
		Assert.assertTrue(tp_info_pg.getBadge().isDisplayed());
		/**
		 * opening the list of third parties
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to third party list page");
		tpp.getList_link().click();
		
		ThirdPartiesListPage tpl=new ThirdPartiesListPage(driver);
		Assert.assertTrue(tpl.getCust_code_filter_searchbox().isEnabled());
		/**
		 * searching for the third party using customer code in filter
		 */
		UtilityClassObject.getTest().log(Status.INFO, "search for cust");
		tpl.getCust_code_filter_searchbox().sendKeys(cust_code+Keys.ENTER);
		Assert.assertTrue(tpl.getCust_list_table().isDisplayed());
		/**
		 * dynamic data extraction from third party list and opening the third party
		 * 
		 */
		driver.findElement(By.xpath("//td[@data-key='ref']/a")).click();
		Assert.assertTrue(tp_info_pg.getBadge().isDisplayed());
		/**
		 * clicking on modify third party button
		 */
		UtilityClassObject.getTest().log(Status.INFO, "modify the cust");
		tp_info_pg.getModify_btn().click();
		
		ModifyThirdPartyPage mod_tp_pg=new ModifyThirdPartyPage(driver);
		Assert.assertTrue(mod_tp_pg.getAddress_textbox().isDisplayed());
		/**
		 * modifying the third party address
		 */
		UtilityClassObject.getTest().log(Status.INFO, "modify the cust address");
		mod_tp_pg.getAddress_textbox().sendKeys(third_party_address);
		mod_tp_pg.getSave_button().click();
		Assert.assertTrue(tp_info_pg.getAddress_block().getText().contains(third_party_address));
	}

}
