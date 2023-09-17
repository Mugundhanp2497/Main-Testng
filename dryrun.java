package org.alphind.alphamcs.test;

import java.util.Map;

import org.alphind.alphamcs.base.TestBase;
import org.alphind.alphamcs.pages.MCOCMS1500Page;
import org.alphind.alphamcs.pages.MCOClaimMaintenancePage;
import org.alphind.alphamcs.pages.MCOHomePage;
import org.alphind.alphamcs.pages.MCOLoginPage;
import org.alphind.alphamcs.pages.MCOUB04Page;
import org.alphind.alphamcs.util.DBUtil;
import org.alphind.alphamcs.util.FileUtil;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class dryrun extends TestBase {

	DBUtil dbUtil;
	MCOLoginPage loginPage;
	MCOHomePage homePage;
	MCOClaimMaintenancePage claimMaintenancepage;
	MCOCMS1500Page cms1500page;
	FileUtil fileUtil;
    MCOUB04Page ub04page;
	
	String userName;
	String passWord;
	
	String networkUserName;
	String networkPassWord;

	
	
	@Test(invocationCount=5)
	public void sample()
	{
		try {
		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
		report(LogStatus.INFO, "Verify whether able to create a new CMS 1500 claim.");

		loginPage = new MCOLoginPage(driver);

		loginPage.selectMCOLogin();

		homePage = loginPage.login(userName, passWord);

		if (homePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}

		/*cms1500page = homePage.navigateToCMS1500Page();

		if (cms1500page.isCMS1500PageDisplayed()) {
			report(LogStatus.PASS, "CMS 1500 page is displayed");
		} else {
			report(LogStatus.FAIL, "CMS 1500 page is not displayed");
		}*/

		ub04page = homePage.naviagteToUB04();
		
		ub04page.createUB04Claim();
		
		//cms1500page. updateCMS1500ClaimandSave("250636");
		//cms1500page.updateCMS1500ClaimandSubmit("250624");
		
		
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/*@Test()
	public void db() {
		
		String conStr = envConfig.getProperty("devDBConnectionString");
		
		DBUtil db = new DBUtil();
		System.out.println( db.executeQuery(conStr, "select * from tb_cms1500s where clm_num=250626"));
		Map<String, String> map = db.executeQuery(conStr, "select * from tb_cms1500s where clm_num=250626");
		
		String patlastName=map.get("pat_ln_02");
		String RendNPI=map.get("srvc_ren_npi_32");
		String patFirstName=map.get("pat_fn_02");
		
		String patSignDate=map.get("pat_sign_dt_12");
	    String diagnosisCode=map.get("diag_1_21");
	    String phySignDate=map.get("phy_sign_dt_31");
	    String phoneNo=map.get("srvc_ren_ph_32");
	    String billPrvTaxon=map.get("Bd_prv_taxonomy_id_33b");
	}
	*/
	
}
