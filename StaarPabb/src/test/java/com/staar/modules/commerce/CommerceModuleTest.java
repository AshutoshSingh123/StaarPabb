package com.staar.modules.commerce;

import org.openqa.selenium.By;
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
import com.staar.objectrepositoryutil.CreateNewProposalPage;
import com.staar.objectrepositoryutil.CreateNewThirdPartyPage;
import com.staar.objectrepositoryutil.DraftProposalInfoPage;
import com.staar.objectrepositoryutil.DraftProposalsListPage;
import com.staar.objectrepositoryutil.HomePage;
import com.staar.objectrepositoryutil.SalesOrderInfoPage;
import com.staar.objectrepositoryutil.ThirdPartiesPage;
import com.staar.objectrepositoryutil.ThirdPartyInfoPage;
import com.staar.objectrepositoryutil.ValidatedProposalInfoPage;
import com.staar.webdriverutility.UtilityClassObject;

/**
 * test class to create a proposal and reject
 * @author Ashutosh
 */
@Listeners(com.staar.listenerutil.ListenerImpClass.class)
public class CommerceModuleTest extends BaseClass{
	
	/**
	 * test method to create a proposal and reject
	 * @author Ashutosh
	 * @throws Throwable 
	 */
	@Test(groups= {"regressionTest"})
	public void createPropAndReject() throws Throwable {
		/**
		 * get excel data 
		 * declaration of utility files
		 */
		UtilityClassObject.getTest().log(Status.INFO, "get execl data");
		ExcelUtilityClass eLib=new ExcelUtilityClass();
		JavaUtilClass jLib=new JavaUtilClass();
		String cust_ref = eLib.getExcelData("proposal", 1, 0)+jLib.getRandom(4000);
		String third_party_name = eLib.getExcelData("proposal", 1, 1)+jLib.getRandom(5000);
		String third_party_type = eLib.getExcelData("proposal", 1, 2);
		String prod=eLib.getExcelData("product", 1, 0)+jLib.getRandom(50);
		String prod_price=eLib.getExcelData("product", 1, 1)+jLib.getRandom(50);
		String prod_qty=eLib.getExcelData("product", 1, 2)+jLib.getRandom(10);
		String service=eLib.getExcelData("service", 1, 0)+jLib.getRandom(50);
		String ser_price=eLib.getExcelData("service", 1, 1)+jLib.getRandom(50);
		String ser_qty=eLib.getExcelData("service", 1, 2)+jLib.getRandom(10);
		
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
		UtilityClassObject.getTest().log(Status.INFO, "navigate to new third party page");
		ThirdPartiesPage th_parties_pg_obj=new ThirdPartiesPage(driver);
		th_parties_pg_obj.getNew_third_party_link().click();
		Assert.assertEquals(th_parties_pg_obj.getThird_parties_leftMenu().isDisplayed(), true);
		
		/**creating an object of "CreateNewThirdPartyPage"
		 * and creating a third party as customer type, by calling "createNewThirdParty("third_party_name","third_party_type")" method
		 */
		CreateNewThirdPartyPage new_th_prty_pg_obj=new CreateNewThirdPartyPage(driver);
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create new third party page");
		new_th_prty_pg_obj.createNewThirdParty(third_party_name, third_party_type);
		Assert.assertEquals(new ThirdPartyInfoPage(driver).getBadge().isDisplayed(), true);
		
		/**
		 * navigate to commerce page
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to commerce page");
		hp.getCommerce_tab().click();
		
		CommercePage cp=new CommercePage(driver);
		Assert.assertTrue(cp.getNew_prop_link().isDisplayed());
		/**
		 * click on new prop link
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to new prop page");
		cp.getNew_prop_link().click();
		Assert.assertTrue(cp.getDraft_prop_link().isDisplayed());
		
		CreateNewProposalPage cnprop=new CreateNewProposalPage(driver);
		Assert.assertTrue(cnprop.getRef_cust_tbx().isDisplayed());
		/**
		 * create new proposal using data
		 */
		UtilityClassObject.getTest().log(Status.INFO, "cerate new prop");
		cnprop.createNewProposal(cust_ref, third_party_name);
		
		/**
		 * add products and service to the proposal
		 */
		DraftProposalInfoPage draft_prop_info_pg=new DraftProposalInfoPage(driver);
		draft_prop_info_pg.addProduct(prod, prod_price, prod_qty);
		draft_prop_info_pg.addService(service, ser_price, ser_qty);
		/**
		 * validate the proposal
		 */
		UtilityClassObject.getTest().log(Status.INFO, "validate prop");
		draft_prop_info_pg.validateProposal();
		
		/**
		 * reject proposal
		 */
		UtilityClassObject.getTest().log(Status.INFO, "reject prop");
		ValidatedProposalInfoPage validated_pro_info=new ValidatedProposalInfoPage(driver);
		validated_pro_info.rejectProposal();
		
		Assert.assertTrue(validated_pro_info.getBadge().getText().contains("closed"));
	}
	
	
	/**
	 * test method to verify a proposal in drafts list
	 * @author Ashutosh
	 * @throws Throwable 
	 */
	@Test(groups= {"regressionTest"})
	public void verifyDraftProposalInList() throws Throwable {
		
		/**
		 * get excel data 
		 * declaration of utility files
		 */
		UtilityClassObject.getTest().log(Status.INFO, "get execl data");
		ExcelUtilityClass eLib=new ExcelUtilityClass();
		JavaUtilClass jLib=new JavaUtilClass();
		String cust_ref = eLib.getExcelData("proposal", 1, 0)+jLib.getRandom(500);
		String third_party_name = eLib.getExcelData("proposal", 1, 1)+jLib.getRandom(2500);
		String third_party_type = eLib.getExcelData("proposal", 1, 2);
		
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
		UtilityClassObject.getTest().log(Status.INFO, "navigate to new third party page");
		ThirdPartiesPage th_parties_pg_obj=new ThirdPartiesPage(driver);
		th_parties_pg_obj.getNew_third_party_link().click();
		Assert.assertEquals(th_parties_pg_obj.getThird_parties_leftMenu().isDisplayed(), true);
		
		/**creating an object of "CreateNewThirdPartyPage"
		 * and creating a third party as customer type, by calling "createNewThirdParty("third_party_name","third_party_type")" method
		 */
		CreateNewThirdPartyPage new_th_prty_pg_obj=new CreateNewThirdPartyPage(driver);
		UtilityClassObject.getTest().log(Status.INFO, "create new third party");
		new_th_prty_pg_obj.createNewThirdParty(third_party_name, third_party_type);
		Assert.assertEquals(new ThirdPartyInfoPage(driver).getBadge().isDisplayed(), true);
		
		/**
		 * navigate to commerce page
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to commerce page");
		hp.getCommerce_tab().click();
		
		CommercePage cp=new CommercePage(driver);
		Assert.assertTrue(cp.getNew_prop_link().isDisplayed());
		/**
		 * click on new prop link
		 */
		cp.getNew_prop_link().click();
		UtilityClassObject.getTest().log(Status.INFO, "navigate to new prop page");
		Assert.assertTrue(cp.getDraft_prop_link().isDisplayed());
		
		CreateNewProposalPage cnprop=new CreateNewProposalPage(driver);
		Assert.assertTrue(cnprop.getRef_cust_tbx().isDisplayed());
		/**
		 * create new proposal using data
		 */
		UtilityClassObject.getTest().log(Status.INFO, "create new prop");
		cnprop.createNewProposal(cust_ref, third_party_name);
		
		DraftProposalInfoPage pinfo=new DraftProposalInfoPage(driver);
		Assert.assertTrue(pinfo.getProposal_draft_id().isDisplayed());
		/**
		 * extracting proposal id
		 */
		String prop_id = pinfo.getProposal_draft_id().getText().substring(1,pinfo.getProposal_draft_id().getText().indexOf(")"));
		
		/**
		 * click on list of draft proposals
		 */
		UtilityClassObject.getTest().log(Status.INFO, "navigate to draft prop list page");
		cp.getDraft_prop_link().click();
		
		DraftProposalsListPage draft_prop_list=new DraftProposalsListPage(driver);
		Assert.assertTrue(draft_prop_list.getRefID_filter_searchbox().isDisplayed());
		/**
		 * filter the list using proosal id
		 */
		UtilityClassObject.getTest().log(Status.INFO, "search prop");
		draft_prop_list.getRefID_filter_searchbox().sendKeys(prop_id+Keys.ENTER);
		
		/**
		 * dynamically verify the result
		 */
		String search_res = driver.findElement(By.xpath("//tr[@class='oddeven']")).getText();
		Assert.assertTrue(search_res.contains(prop_id));	
	}
	
	
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
		UtilityClassObject.getTest().log(Status.INFO, "get execl data");
		ExcelUtilityClass eLib=new ExcelUtilityClass();
		JavaUtilClass jLib=new JavaUtilClass();
		String cust_ref = eLib.getExcelData("proposal", 1, 0)+jLib.getRandom(3500);
		String third_party_name = eLib.getExcelData("proposal", 1, 1)+jLib.getRandom(1000);
		String third_party_type = eLib.getExcelData("proposal", 1, 2);
		String prod=eLib.getExcelData("product", 1, 0)+jLib.getRandom(50);
		String prod_price=eLib.getExcelData("product", 1, 1)+jLib.getRandom(50);
		String prod_qty=eLib.getExcelData("product", 1, 2)+jLib.getRandom(10);
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
		UtilityClassObject.getTest().log(Status.INFO, "navigate to  new third party page");
		ThirdPartiesPage th_parties_pg_obj=new ThirdPartiesPage(driver);
		th_parties_pg_obj.getNew_third_party_link().click();
		Assert.assertEquals(th_parties_pg_obj.getThird_parties_leftMenu().isDisplayed(), true);
		
		/**creating an object of "CreateNewThirdPartyPage"
		 * and creating a third party as customer type, by calling "createNewThirdParty("third_party_name","third_party_type")" method
		 */
		CreateNewThirdPartyPage new_th_prty_pg_obj=new CreateNewThirdPartyPage(driver);
		UtilityClassObject.getTest().log(Status.INFO, "create new third party");
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
		UtilityClassObject.getTest().log(Status.INFO, "navigate to new sales order link");
		cp.getNew_order_link().click();
		
		CreateNewOrderPage cnop=new CreateNewOrderPage(driver);
		/**
		 * create a new order
		 */
		UtilityClassObject.getTest().log(Status.INFO, "create new order");
		cnop.createOrder(third_party_name,cust_ref);
		
		SalesOrderInfoPage soip=new SalesOrderInfoPage(driver);
		/**
		 * add products to the order
		 */
		soip.addProduct(prod, prod_price, prod_qty);
		/**
		 * validate the order
		 */
		UtilityClassObject.getTest().log(Status.INFO, "validate order");
		soip.validateOrder();
		Assert.assertTrue(soip.getBadge().getText().equals("Validated"));
	}
}
