package com.staar.modules.commerce;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.staar.baseutil.BaseClass;
import com.staar.fileutility.ExcelUtilityClass;
import com.staar.javautil.JavaUtilClass;
import com.staar.listenerutil.ListenerImpClass;
import com.staar.objectrepositoryutil.CommercePage;
import com.staar.objectrepositoryutil.CreateNewOrderPage;
import com.staar.objectrepositoryutil.CreateNewThirdPartyPage;
import com.staar.objectrepositoryutil.HomePage;
import com.staar.objectrepositoryutil.SalesOrderInfoPage;
import com.staar.objectrepositoryutil.ThirdPartiesPage;
import com.staar.objectrepositoryutil.ThirdPartyInfoPage;

/**
 * test class to create sales order and validate
 * @author Ashutosh
 */
@Listeners(com.staar.listenerutil.ListenerImpClass.class)
public class CreateSalesOrderAndValidateTest extends BaseClass{
	
	/**
	 * test method to create a sales order and validate
	 * @author Ashutosh
	 * @throws Throwable 
	 */
	@Test(groups= {"regressionTest"})
	public void createSalesOrderAndValidateTest() throws Throwable {
		/**
		 * get excel data 
		 * declaration of utility files
		 */
		ExcelUtilityClass eLib=new ExcelUtilityClass();
		JavaUtilClass jLib=new JavaUtilClass();
		String cust_ref = eLib.getExcelData("proposal", 1, 0)+jLib.getRandom(1000);
		String third_party_name = eLib.getExcelData("proposal", 1, 1)+jLib.getRandom(1000);
		String third_party_type = eLib.getExcelData("proposal", 1, 2);
		String prod=eLib.getExcelData("product", 1, 0)+jLib.getRandom(50);
		String prod_price=eLib.getExcelData("product", 1, 1)+jLib.getRandom(50);
		String prod_qty=eLib.getExcelData("product", 1, 2)+jLib.getRandom(10);
		/**
		 * Creating object of "HomePage" class
		 * clicking on third party major tab
		 */
		HomePage hp=new HomePage(driver);
		hp.getThird_party_tab().click();
		
		Assert.assertEquals(hp.getLogin_dd().isDisplayed(), true);
		/**creating object of "ThirdPartiesPage" class and
		 * clicking on "New Third Party Link"
		 */
		ThirdPartiesPage th_parties_pg_obj=new ThirdPartiesPage(driver);
		th_parties_pg_obj.getNew_third_party_link().click();
		Assert.assertEquals(th_parties_pg_obj.getThird_parties_leftMenu().isDisplayed(), true);
		
		/**creating an object of "CreateNewThirdPartyPage"
		 * and creating a third party as customer type, by calling "createNewThirdParty("third_party_name","third_party_type")" method
		 */
		CreateNewThirdPartyPage new_th_prty_pg_obj=new CreateNewThirdPartyPage(driver);
		
		new_th_prty_pg_obj.createNewThirdParty(third_party_name, third_party_type);
		Assert.assertEquals(new ThirdPartyInfoPage(driver).getBadge().isDisplayed(), true);
		
		/**
		 * navigate to commerce page
		 */
		hp.getCommerce_tab().click();
		
		CommercePage cp=new CommercePage(driver);
		
		/**
		 * click on create new order link
		 */
		cp.getNew_order_link().click();
		
		CreateNewOrderPage cnop=new CreateNewOrderPage(driver);
		/**
		 * create a new order
		 */
		cnop.createOrder(third_party_name,cust_ref);
		
		SalesOrderInfoPage soip=new SalesOrderInfoPage(driver);
		/**
		 * add products to the order
		 */
		soip.addProduct(prod, prod_price, prod_qty);
		/**
		 * validate the order
		 */
		soip.validateOrder();
		Assert.assertTrue(soip.getBadge().getText().equals("Validated"));
	}
}
