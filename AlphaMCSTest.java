package org.alphind.alphamcs.test;

import org.alphind.alphamcs.base.TestBase;
import org.alphind.alphamcs.pages.MCOCMS1500Page;
import org.alphind.alphamcs.pages.MCOHomePage;
import org.alphind.alphamcs.pages.MCOLoginPage;
import org.alphind.alphamcs.pages.MCOPatientEnrollmentPage;
import org.alphind.alphamcs.util.DBUtil;
import org.alphind.alphamcs.util.ExcelUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class AlphaMCSTest extends TestBase {

	// Log4j configuration
	private static final Logger log = LogManager.getLogger(AlphaMCSTest.class);

	DBUtil dbUtil;
	MCOLoginPage loginPage;
	MCOHomePage homePage;
	MCOPatientEnrollmentPage patientEnrollmentPage;
	MCOCMS1500Page cms1500;

	@Test
	public void newPatientEnrollment() {
		report(LogStatus.INFO, "Test to verify if user can enroll a new patient");

		loginPage = new MCOLoginPage(driver);

		String userName = envConfig.getProperty("userName");
		String password = envConfig.getProperty("password");

		loginPage.selectMCOLogin();

		homePage = loginPage.login(userName, password);

		if (homePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}

		homePage.naviagteToPatientEnrollments();
		patientEnrollmentPage = new MCOPatientEnrollmentPage(driver);
		String patientId = patientEnrollmentPage.createEnrollment();
		report(LogStatus.PASS, "New Patient enrolled, Patient ID - " + patientId);
		// patientEnrollmentPage.filterByPatientId(patientId);

//		patientEnrollmentPage.filterByPatientId("188917");

		// homePage.logout();

		// Assert.assertEquals(expectedProductLabel, actualProductLabel);
	}

	@Test
	public void claimCMS1500() {
		report(LogStatus.INFO, "Test to verify if user can file a CMS 1500 claim");

		loginPage = new MCOLoginPage(driver);

		String userName = envConfig.getProperty("userName");
		String password = envConfig.getProperty("password");

		loginPage.selectMCOLogin();

		homePage = loginPage.login(userName, password);

		if (homePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}

		//homePage.naviagteToCMS1500();
		cms1500 = new MCOCMS1500Page(driver);
		//String claimId = cms1500.createClaim();
		//report(LogStatus.PASS, "New CMS 1500 claim created, Claim ID - " + claimId);
		// patientEnrollmentPage.filterByPatientId(patientId);

//		patientEnrollmentPage.filterByPatientId("188917");

		// homePage.logout();

		// Assert.assertEquals(expectedProductLabel, actualProductLabel);
	}

	// test to demo DB query execution
	@Test
	public void testDB() {
		String conStr = envConfig.getProperty("devDBConnectionString");
		String query = "SELECT * FROM alphamcs_shc.dbo.tb_pat_ins where pat_id = 1";
		dbUtil = new DBUtil();
		System.out.println(dbUtil.executeQuery(conStr, query).get("pat_ins_id"));

		dbUtil.executeSP(conStr, "asp_portal_claims_processing_wrapper");

	}

	@DataProvider(name = "tempTestData")
	public Object[][] tempTestData() throws Exception {

		testData = ExcelUtil.getExcelDataIn2DArray("testData//AlphaPlusTestData.xlsx", "testDataSheet");
		return testData;
	}

	// Test to demo data provider functionality
//	@Test(dataProvider = "tempTestData")
//	public void tempTest(String userId, String pwd) {
//		System.out.println(userId + " - " + pwd);
//	}
//	
//	@Test
//	public void tempTest() {
//		System.out.println(testData[0][0]);
//	}

}
