package com.staar.modules.thirdparty;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.staar.baseutil.BaseClass;
import com.staar.fileutility.ExcelUtilityClass;
import com.staar.javautil.JavaUtilClass;
import com.staar.objectrepositoryutil.CreateNewThirdPartyPage;
import com.staar.objectrepositoryutil.HomePage;
import com.staar.objectrepositoryutil.ModifyThirdPartyPage;
import com.staar.objectrepositoryutil.ThirdPartiesListPage;
import com.staar.objectrepositoryutil.ThirdPartiesPage;
import com.staar.objectrepositoryutil.ThirdPartyInfoPage;

/**
 * test class to modify a third party 
 * @author Ashutosh
 */
public class ModifyThirdPartyTest extends BaseClass {
	
	/**
	 * test method to modify a third party
	 * @throws Throwable 
	 */
	@Test(groups= {"regressionTest"})
	public void modifyThirdPartyTest() throws Throwable {
		/**
		 * get excel data
		 */
		ExcelUtilityClass eLib=new ExcelUtilityClass();
		JavaUtilClass jLib=new JavaUtilClass();
		String third_party_name = eLib.getExcelData("thirdparty", 1, 0)+jLib.getRandom(1000);
		String third_party_type = eLib.getExcelData("thirdparty", 1, 1);
		String third_party_address=eLib.getExcelData("thirdparty", 1, 2);
		
		ThirdPartiesPage tpp=new ThirdPartiesPage(driver);
		
		/**
		 * click on third party tab in home page
		 */
		HomePage hp=new HomePage(driver);
		hp.getThird_party_tab().click();
		Assert.assertTrue(tpp.getThird_parties_leftMenu().isDisplayed());
		/**
		 * nav to create new third party page
		 */
		
		tpp.getNew_third_party_link().click();
		
		/**
		 * creating new third party
		 */
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
		tpp.getList_link().click();
		
		ThirdPartiesListPage tpl=new ThirdPartiesListPage(driver);
		Assert.assertTrue(tpl.getCust_code_filter_searchbox().isEnabled());
		/**
		 * searching for the third party using customer code in filter
		 */
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
		tp_info_pg.getModify_btn().click();
		
		ModifyThirdPartyPage mod_tp_pg=new ModifyThirdPartyPage(driver);
		Assert.assertTrue(mod_tp_pg.getAddress_textbox().isDisplayed());
		/**
		 * modifying the third party address
		 */
		mod_tp_pg.getAddress_textbox().sendKeys(third_party_address);
		mod_tp_pg.getSave_button().click();
		Assert.assertTrue(tp_info_pg.getAddress_block().getText().contains(third_party_address));
	}
}
