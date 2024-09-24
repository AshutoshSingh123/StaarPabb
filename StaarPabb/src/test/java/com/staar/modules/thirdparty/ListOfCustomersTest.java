package com.staar.modules.thirdparty;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.staar.baseutil.BaseClass;
import com.staar.objectrepositoryutil.HomePage;
import com.staar.objectrepositoryutil.ThirdPartiesPage;

/**this test class has test methods relating to list of customers
 * @author Ashutosh
 * 
 */
public class ListOfCustomersTest extends BaseClass {
	
	/**
	 * this test method is used to get the list of customers
	 */
	@Test(groups= {"smokeTest","regressionTest"})
	public void ListOfCustomers() {
		/**
		 * create the object of homepage
		 * and click on third party major tab
		 */
		HomePage hp=new HomePage(driver);
		hp.getThird_party_tab().click();
		
		ThirdPartiesPage tpp=new ThirdPartiesPage(driver);
		Assert.assertEquals(tpp.getList_link().isDisplayed(), true);
		
		/**
		 * click on third parties link and open the third parties page
		 * click on list of customers link
		 */
		tpp.getList_of_customers_link().click();
		Assert.assertEquals(tpp.getList_of_customers_link().isEnabled(), true);
	}
}
