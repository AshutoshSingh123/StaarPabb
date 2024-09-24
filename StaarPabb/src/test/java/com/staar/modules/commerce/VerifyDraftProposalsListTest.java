package com.staar.modules.commerce;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.staar.baseutil.BaseClass;
import com.staar.fileutility.ExcelUtilityClass;
import com.staar.javautil.JavaUtilClass;
import com.staar.objectrepositoryutil.CommercePage;
import com.staar.objectrepositoryutil.CreateNewProposalPage;
import com.staar.objectrepositoryutil.CreateNewThirdPartyPage;
import com.staar.objectrepositoryutil.DraftProposalsListPage;
import com.staar.objectrepositoryutil.HomePage;
import com.staar.objectrepositoryutil.ThirdPartiesPage;
import com.staar.objectrepositoryutil.ThirdPartyInfoPage;
import com.staar.objectrepositoryutil.DraftProposalInfoPage;

/**
 * test class to verify a proposal in the drafts
 * @author Ashutosh
 */
public class VerifyDraftProposalsListTest extends BaseClass {
	
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
		ExcelUtilityClass eLib=new ExcelUtilityClass();
		JavaUtilClass jLib=new JavaUtilClass();
		String cust_ref = eLib.getExcelData("proposal", 1, 0)+jLib.getRandom(1000);
		String third_party_name = eLib.getExcelData("proposal", 1, 1)+jLib.getRandom(1000);
		String third_party_type = eLib.getExcelData("proposal", 1, 2);
		
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
		Assert.assertTrue(cp.getNew_prop_link().isDisplayed());
		/**
		 * click on new prop link
		 */
		cp.getNew_prop_link().click();
		Assert.assertTrue(cp.getDraft_prop_link().isDisplayed());
		
		CreateNewProposalPage cnprop=new CreateNewProposalPage(driver);
		Assert.assertTrue(cnprop.getRef_cust_tbx().isDisplayed());
		/**
		 * create new proposal using data
		 */
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
		cp.getDraft_prop_link().click();
		
		DraftProposalsListPage draft_prop_list=new DraftProposalsListPage(driver);
		Assert.assertTrue(draft_prop_list.getRefID_filter_searchbox().isDisplayed());
		/**
		 * filter the list using proosal id
		 */
		draft_prop_list.getRefID_filter_searchbox().sendKeys(prop_id+Keys.ENTER);
		
		/**
		 * dynamically verify the result
		 */
		String search_res = driver.findElement(By.xpath("//tr[@class='oddeven']")).getText();
		Assert.assertTrue(search_res.contains(prop_id));
		
	}
}