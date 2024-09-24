package com.staar.modules.services;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.staar.baseutil.BaseClass;
import com.staar.fileutility.ExcelUtilityClass;
import com.staar.javautil.JavaUtilClass;
import com.staar.objectrepositoryutil.CommercePage;
import com.staar.objectrepositoryutil.CreateNewOrderPage;
import com.staar.objectrepositoryutil.CreateNewThirdPartyPage;
import com.staar.objectrepositoryutil.CreateShipmentPage;
import com.staar.objectrepositoryutil.HomePage;
import com.staar.objectrepositoryutil.SalesOrderInfoPage;
import com.staar.objectrepositoryutil.ServicesPage;
import com.staar.objectrepositoryutil.ShipmentInfoPage;
import com.staar.objectrepositoryutil.ShipmentsListPage;
import com.staar.objectrepositoryutil.ThirdPartiesPage;
import com.staar.objectrepositoryutil.ThirdPartyInfoPage;
import com.staar.webdriverutility.UtilityClassObject;

/**
 * test class to modify a shipment and send a mail
 */
@Listeners(com.staar.listenerutil.ListenerImpClass.class)
public class ModifyShipmentAndSendMailTest extends BaseClass{
	
	/**
	 * test method to modify a shipment and send a mail
	 * @throws Throwable 
	 */
	@Test(groups= {"regressionTest"})
	public void modifyShipmentAndSendMailTest() throws Throwable{
		
		/**
		 * get excel data 
		 * declaration of utility files
		 */
		UtilityClassObject.getTest().log(Status.INFO, "extract execl data");
		ExcelUtilityClass eLib=new ExcelUtilityClass();
		JavaUtilClass jLib=new JavaUtilClass();
		String cust_ref = eLib.getExcelData("proposal", 1, 0)+jLib.getRandom(1000);
		String third_party_name = eLib.getExcelData("proposal", 1, 1)+jLib.getRandom(1000);
		String third_party_type = eLib.getExcelData("proposal", 1, 2);
		String prod=eLib.getExcelData("product", 1, 0)+jLib.getRandom(50);
		String prod_price=eLib.getExcelData("product", 1, 1)+jLib.getRandom(50);
		String prod_qty=eLib.getExcelData("product", 1, 2)+jLib.getRandom(10);
		String serv1=eLib.getExcelData("service", 1, 0)+jLib.getRandom(50);
		String serv1_price=eLib.getExcelData("service", 1, 1)+jLib.getRandom(50);
		String serv1_qty=eLib.getExcelData("service", 1, 2)+jLib.getRandom(10);
		String prod2=eLib.getExcelData("product", 1, 0)+jLib.getRandom(50);
		String prod2_price=eLib.getExcelData("product", 1, 1)+jLib.getRandom(50);
		String prod2_qty=eLib.getExcelData("product", 1, 2)+jLib.getRandom(10);
		String shipment_type=eLib.getExcelData("shipments", 1, 0);
		String shipment_type_to_modify=eLib.getExcelData("shipments", 2, 0);
		String email_message=eLib.getExcelData("email", 1, 1);
		
		/**
		 * Creating object of "HomePage" class
		 * clicking on third party major tab
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to third party page");
		HomePage hp=new HomePage(driver);
		hp.getThird_party_tab().click();
		
		Assert.assertEquals(hp.getLogin_dd().isDisplayed(), true);
		
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
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create third party page");
		new_th_prty_pg_obj.createNewThirdParty(third_party_name, third_party_type);
		Assert.assertEquals(new ThirdPartyInfoPage(driver).getBadge().isDisplayed(), true);
		
		/**
		 * navigate to commerce page
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to commerce page");
		hp.getCommerce_tab().click();
		
		CommercePage cp=new CommercePage(driver);
		
		/**
		 * click on create new order link
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to new order page");
		cp.getNew_order_link().click();
		
		CreateNewOrderPage cnop=new CreateNewOrderPage(driver);
		/**
		 * create a new order
		 */
		UtilityClassObject.getTest().log(Status.INFO, "create new order");
		cnop.createOrder(third_party_name,cust_ref);
		
		SalesOrderInfoPage soip=new SalesOrderInfoPage(driver);
		Assert.assertTrue(soip.getBadge().isDisplayed());
		/**
		 * add products to the order
		 */
		UtilityClassObject.getTest().log(Status.INFO, "add prod/ser to sales order");
		soip.addProduct(prod, prod_price, prod_qty);
		soip.addProduct(serv1, serv1_price, serv1_qty);
		soip.addService(prod2, prod2_price, prod2_qty);
		
		/**
		 * validate the order
		 */
		UtilityClassObject.getTest().log(Status.INFO, "validate");
		soip.validateOrder();
		Assert.assertTrue(soip.getBadge().getText().equals("Validated"));
		
		/**
		 * click on shipments in top nav row
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to shipments top nav");
		soip.getShipments_topnav().click();
		Assert.assertTrue(soip.getCreate_shipment_btn().isDisplayed());
		/**
		 * create a shipment for that order
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create shipment page");
		soip.getCreate_shipment_btn().click();
		
		CreateShipmentPage csp=new CreateShipmentPage(driver);
		Assert.assertTrue(csp.getShipping_method_select_box().isDisplayed());
		UtilityClassObject.getTest().log(Status.INFO, "create new shipment");
		csp.createShipment(shipment_type);
		
		/**
		 * validate the created shipment
		 */
		ShipmentInfoPage sh_info_pg=new ShipmentInfoPage(driver);
		Assert.assertTrue(sh_info_pg.getBadge().isDisplayed());
		UtilityClassObject.getTest().log(Status.INFO, "validate shipment");
		sh_info_pg.validateShipment();
		Assert.assertTrue(sh_info_pg.getBadge().getText().contains("Validated"));
		/**
		 * navigate to services major tab
		 */
		String shipmentID = sh_info_pg.getShipment_id_info().getText().substring(0, sh_info_pg.getShipment_id_info().getText().indexOf("Ref"));
		UtilityClassObject.getTest().log(Status.INFO, "nav to services tab");
		hp.getServices_tab().click();
		ServicesPage sp=new ServicesPage(driver);
		Assert.assertTrue(sp.getSh_list_link().isDisplayed());
		/**
		 * click on shipment list link
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to shipment list page");
		sp.getSh_list_link().click();
		
		/**
		 * search for the shipment in the filter
		 */
		ShipmentsListPage sh_list_pg=new ShipmentsListPage(driver);
		Assert.assertTrue(sh_list_pg.getSh_ref_filter_tbx().isDisplayed());
		UtilityClassObject.getTest().log(Status.INFO, "search shipment");
		sh_list_pg.getSh_ref_filter_tbx().sendKeys(shipmentID+Keys.ENTER);
		
		/**
		 * click on the filter result
		 */
		sh_list_pg.getRefId_filter_result().click();
		Assert.assertTrue(sh_info_pg.getBadge().isDisplayed());
		
		/**
		 * modify the shipment method from
		 */
		UtilityClassObject.getTest().log(Status.INFO, "modify shipment");
		sh_info_pg.modifyShippingMthd(shipment_type_to_modify);
		Assert.assertTrue(sh_info_pg.getShipping_method_info().getText().contains(shipment_type_to_modify));
		/**
		 * send the email to the party with the mail message
		 */
		UtilityClassObject.getTest().log(Status.INFO, "send mail");
		sh_info_pg.sendEmail(email_message,third_party_name);
		Assert.assertFalse(sh_info_pg.getMail_conf_msg().isDisplayed());
		sh_info_pg.getClose_mail_notify().click();
	}
}
