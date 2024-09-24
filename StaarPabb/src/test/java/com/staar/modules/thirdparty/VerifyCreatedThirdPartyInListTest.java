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
import com.staar.objectrepositoryutil.ThirdPartiesListPage;
import com.staar.objectrepositoryutil.ThirdPartiesPage;
import com.staar.objectrepositoryutil.ThirdPartyInfoPage;

/**
 * test method to verify a created third party in list
 * @author Ashutosh
 */
public class VerifyCreatedThirdPartyInListTest extends BaseClass {
	
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
		ExcelUtilityClass eLib=new ExcelUtilityClass();
		JavaUtilClass jLib=new JavaUtilClass();
		String third_party_name = eLib.getExcelData("thirdparty", 1, 0)+jLib.getRandom(1000);
		String third_party_type = eLib.getExcelData("thirdparty", 1, 1);
		
		/**
		 * home page
		 */
		HomePage hp=new HomePage(driver);
		Assert.assertTrue(hp.getLogin_dd().isDisplayed());
		
		/**
		 * clicking on third parties major tab
		 */
		hp.getThird_party_tab().click();
		
		ThirdPartiesPage tpp=new ThirdPartiesPage(driver);
		Assert.assertTrue(tpp.getThird_parties_leftMenu().isDisplayed());
		
		/**
		 * clicking on new third party link
		 */
		tpp.getNew_third_party_link().click();
		CreateNewThirdPartyPage cntp=new CreateNewThirdPartyPage(driver);
		Assert.assertTrue(cntp.getThird_party_name_tbx().isDisplayed());
		
		/**
		 * creating a new third party by calling a business method
		 */
		cntp.createNewThirdParty(third_party_name, third_party_type);
		Assert.assertTrue(new ThirdPartyInfoPage(driver).getBadge().isDisplayed());
		
		/**
		 * clicking on List link
		 */
		ThirdPartiesListPage tpl=new ThirdPartiesListPage(driver);
		tpp.getList_link().click();
		Assert.assertTrue(tpl.getCust_list_table().isDisplayed());
		
		/**
		 * searching for the created third party
		 */
		tpl.getThird_party_name_filter_searchbox().sendKeys(third_party_name+Keys.ENTER);
		Assert.assertEquals(driver.findElement(By.xpath("//td[@class='tdoverflowmax200']/a")).getText(),third_party_name);
	}
}
