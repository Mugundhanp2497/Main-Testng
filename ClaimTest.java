package org.alphind.alphamcs.test;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import org.alphind.alphamcs.base.TestBase;
import org.alphind.alphamcs.pages.MCOCMS1500Page;
import org.alphind.alphamcs.pages.MCOClaimMaintenancePage;
import org.alphind.alphamcs.pages.MCOHomePage;
import org.alphind.alphamcs.pages.MCOLoginPage;
import org.alphind.alphamcs.util.DBUtil;
import org.alphind.alphamcs.util.ExcelUtil;
import org.alphind.alphamcs.util.FileUtil;
import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;

public class ClaimTest extends TestBase {

	private static final Object[] String = null;
	DBUtil dbUtil;
	MCOLoginPage loginPage;
	MCOHomePage homePage;
	MCOClaimMaintenancePage claimMaintenancepage;
	MCOCMS1500Page cms1500page;
	FileUtil fileUtil;

	String userName;
	String passWord;
	
	String networkUserName;
	String networkPassWord;

	@Test
	public void viewClaim() {

		report(LogStatus.INFO, "Verify whether able to view claim status.");

		loginPage = new MCOLoginPage(driver);

		loginPage.selectMCOLogin();

		homePage = loginPage.login(userName, passWord);

		if (homePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}

		claimMaintenancepage = homePage.navigateToClaimMaintenance();

		claimMaintenancepage.isClaimMaintenancePageDisplayed();

		claimMaintenancepage.clickFilter();

		claimMaintenancepage.searchWithHeaderAndViewClaim("6557017");

		// claimMaintenancepage.searchWithMCSNumberAndViewClaim("250600");

	}

	@Test
	public void createCMS1500Claim() {

		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
		report(LogStatus.INFO, "Verify whether able to create a new CMS 1500 claim.");

		loginPage = new MCOLoginPage(driver);

		loginPage.selectMCOLogin();

		homePage = loginPage.login(userName, passWord);

		if (homePage.isLoginSuccessful()) 
		{
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} 
		else
		{
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}

		cms1500page = homePage.navigateToCMS1500Page();

		if (cms1500page.isCMS1500PageDisplayed()) {
			report(LogStatus.PASS, "CMS 1500 page is displayed");
		} else {
			report(LogStatus.FAIL, "CMS 1500 page is not displayed");
		}

		String claimnumber = cms1500page.createAndSubmitClaim();

		String mcsnumber = claimnumber.replaceAll("[^0-9]", "");

		report(LogStatus.INFO, "My MCS Claim # for new created CMS 1500 claim is : " + mcsnumber);

		String conStr = envConfig.getProperty("devDBConnectionString");

		dbUtil = new DBUtil();

		String wrapper = "asp_portal_claims_processing_wrapper";

		dbUtil.executeSP(conStr, wrapper);

		claimMaintenancepage = homePage.navigateToClaimMaintenance();

		claimMaintenancepage.isClaimMaintenancePageDisplayed();

		claimMaintenancepage.clickFilter();

		claimMaintenancepage.searchWithMCSNumberAndViewClaim(mcsnumber);

	}

	@Test
	public void EDIincomingFile837PTest() {

		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
		networkUserName = envConfig.getProperty("networkUserName");
		networkPassWord = envConfig.getProperty("networkPassWord");

		report(LogStatus.INFO, "Verify end to end processing for incoming837p File");

		fileUtil = new FileUtil();
		dbUtil = new DBUtil();
		String incoming837pFilePath = "testData\\EDI_Files\\" + dataMap.get("EDIfileName");
		
		System.out.println(incoming837pFilePath);
		
		String conStr = envConfig.getProperty("devDBConnectionString");

		String testSFTPFilePath = envConfig.getProperty("testSFTPFilePath");
		String testSFTPArchiveFolderPath = envConfig.getProperty("testSFTPArchiveFolderPath");
		String sandhillsAcceptFolderPath = envConfig.getProperty("sandhillsAcceptFolderPath");
		String encDevSandhillsAcceptFolderPath = envConfig.getProperty("encDevSandhillsAcceptFolderPath");

		//Copy file from test data to \\10.0.82.211->sftp->Test_Folders->MCS_Folders->1006->in->test folder
		fileUtil.copyFile(incoming837pFilePath, testSFTPFilePath + "\\SingleClaimIncoming837p.jci");
		report(LogStatus.PASS, "Incoming 837 file placed at folder -" + testSFTPFilePath);

		//Verify file processed from \\10.0.82.211->sftp->Test_Folders->MCS_Folders->1006->in->test folder
		if (fileUtil.isFileProcessed("SingleClaimIncoming837p", testSFTPFilePath, 20)) {
			report(LogStatus.PASS, "File processed from folder -" + testSFTPFilePath);

			//Verify processed file present \\10.0.82.211->sftp->Test_Folders->MCS_Folders->1006->in->test->1006->in->test->archive folders
			if (!(fileUtil.findFile("SingleClaimIncoming837p", testSFTPArchiveFolderPath).equals("File Not Found"))) {
				report(LogStatus.PASS, "File found in folder -" + testSFTPArchiveFolderPath);

				String acceptedXMLFileName = fileUtil.findFile("SingleClaimIncoming837p", sandhillsAcceptFolderPath, 5);

				//Verify xml file generated in \\10.0.82.211->sftp->Test_Folders->MCS_Folders->SANDHILLS_ACCEPT_TEST folder
				if (!(acceptedXMLFileName.equals("File Not Found"))) {
					report(LogStatus.PASS, "Accepted xml file  -" + acceptedXMLFileName);

					String acceptedXMLFilePath = sandhillsAcceptFolderPath + "\\" + acceptedXMLFileName;
					String acceptedXMLFilePathDest = encDevSandhillsAcceptFolderPath + "\\" + acceptedXMLFileName;

					//Copy xml file to \\192.168.10.93->d$->sftp->sandhills_accept
					fileUtil.copyFile(acceptedXMLFilePath, acceptedXMLFilePathDest);
					fileUtil.findFile(acceptedXMLFileName, encDevSandhillsAcceptFolderPath, 1);
					report(LogStatus.PASS, "Incoming 837 file placed at folder -" + encDevSandhillsAcceptFolderPath);

					dbUtil.executeSP(conStr, "asp_837_process_wrapper");
					report(LogStatus.PASS, "Executed wrapper - asp_837_process_wrapper");
					
					//Verify file processed from \\192.168.10.93->d$->sftp->sandhills_accept
					if (fileUtil.isFileProcessed(acceptedXMLFileName, encDevSandhillsAcceptFolderPath, 30)) {
						report(LogStatus.PASS, "File processed from folder -" + encDevSandhillsAcceptFolderPath);

						String batNum = (dbUtil.executeQuery(conStr,
								"select top 1 bat_num from tb_ic_837P_processing_det h order by h.bat_num desc"))
								.get("bat_num");
						String claimHeaderId = (dbUtil.executeQuery(conStr,
								"select clm_hdr_id from tb_ic_837P_processing_det h where bat_num = " + batNum))
								.get("clm_hdr_id");

						if (claimHeaderId != null) {
							report(LogStatus.PASS, "Claim header ID = " + claimHeaderId);

							loginPage = new MCOLoginPage(driver);

							loginPage.selectMCOLogin();

							homePage = loginPage.login(userName, passWord);

							if (homePage.isLoginSuccessful()) {
								report(LogStatus.PASS, "Login successful with user - " + userName);
							} else {
								report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
							}

							claimMaintenancepage = homePage.navigateToClaimMaintenance();

							claimMaintenancepage.isClaimMaintenancePageDisplayed();

							claimMaintenancepage.clickFilter();

							claimMaintenancepage.searchWithHeaderAndViewClaim(claimHeaderId);
						} else {
							report(LogStatus.FAIL, "Unable to get Claim header ID from database");
						}

					} else {
						report(LogStatus.FAIL, "File processed failed from folder -" + encDevSandhillsAcceptFolderPath);
					}

				} else {
					report(LogStatus.FAIL, "Accepted xml file not found in folder -" + sandhillsAcceptFolderPath);
				}

			} else {
				report(LogStatus.FAIL, "File not found in folder -" + testSFTPArchiveFolderPath);
			}
		} else {
			report(LogStatus.FAIL, "File process failed from folder -" + testSFTPFilePath);
		}

	}

	@Test
	public void updateCMS1500()
	{
		
		System.out.println(
				ExcelUtil.
				getTestCaseDataInMap("testData//AlphaPlusTestData.xlsx", 
						"testDataSheet", "updateCMS1500"));
//		
//		try {
//		userName = envConfig.getProperty("userName");
//		passWord = envConfig.getProperty("password");
//		
//		report(LogStatus.INFO, "Verify whether able to create a new CMS 1500 claim.");
//
//		loginPage = new MCOLoginPage(driver);
//
//		loginPage.selectMCOLogin();
//
//		homePage = loginPage.login(userName, passWord);
//
//		if (homePage.isLoginSuccessful()) {
//			report(LogStatus.PASS, "Login successful with user - " + userName);
//		} else {
//			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
//		}
//
//		cms1500page = homePage.navigateToCMS1500Page();
//
//		if (cms1500page.isCMS1500PageDisplayed()) {
//			report(LogStatus.PASS, "CMS 1500 page is displayed");
//		} else {
//			report(LogStatus.FAIL, "CMS 1500 page is not displayed");
//		}
//
//		cms1500page. updateCMS1500Claim("250619");
//		
//	
//		
//		
//		
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
		
		
		
	}
	
	@Test
	public void EDIincomingFileMulticlaim837PTest()
	{
		
//		userName = envConfig.getProperty("userName");
//		passWord = envConfig.getProperty("password");
//		
//		networkUserName = envConfig.getProperty("networkUserName");
//		networkPassWord = envConfig.getProperty("networkPassWord");
//
//		report(LogStatus.INFO, "Verify end to end processing for incoming837p File");
//
//		fileUtil = new FileUtil();
		dbUtil = new DBUtil();
//		String incoming837pFilePath = "testData\\EDI_Files\\" + dataMap.get("EDIfileName");
//		
//		System.out.println(incoming837pFilePath);
//		
		String conStr = envConfig.getProperty("devDBConnectionString");
//
//		String testSFTPFilePath = envConfig.getProperty("testSFTPFilePath");
//		String testSFTPArchiveFolderPath = envConfig.getProperty("testSFTPArchiveFolderPath");
//		String sandhillsAcceptFolderPath = envConfig.getProperty("sandhillsAcceptFolderPath");
//		String encDevSandhillsAcceptFolderPath = envConfig.getProperty("encDevSandhillsAcceptFolderPath");
//
//		//Copy file from test data to \\10.0.82.211->sftp->Test_Folders->MCS_Folders->1006->in->test folder
//		fileUtil.copyFile(incoming837pFilePath, testSFTPFilePath + "\\SingleClaimIncoming837p.jci");
//		report(LogStatus.PASS, "Incoming 837 file placed at folder -" + testSFTPFilePath);
//
//		//Verify file processed from \\10.0.82.211->sftp->Test_Folders->MCS_Folders->1006->in->test folder
//		if (fileUtil.isFileProcessed("SingleClaimIncoming837p", testSFTPFilePath, 20)) {
//			report(LogStatus.PASS, "File processed from folder -" + testSFTPFilePath);
//
//			//Verify processed file present \\10.0.82.211->sftp->Test_Folders->MCS_Folders->1006->in->test->1006->in->test->archive folders
//			if (!(fileUtil.findFile("SingleClaimIncoming837p", testSFTPArchiveFolderPath).equals("File Not Found"))) {
//				report(LogStatus.PASS, "File found in folder -" + testSFTPArchiveFolderPath);
//
//				String acceptedXMLFileName = fileUtil.findFile("SingleClaimIncoming837p", sandhillsAcceptFolderPath, 5);
//
//				//Verify xml file generated in \\10.0.82.211->sftp->Test_Folders->MCS_Folders->SANDHILLS_ACCEPT_TEST folder
//				if (!(acceptedXMLFileName.equals("File Not Found"))) {
//					report(LogStatus.PASS, "Accepted xml file  -" + acceptedXMLFileName);
//
//					String acceptedXMLFilePath = sandhillsAcceptFolderPath + "\\" + acceptedXMLFileName;
//					String acceptedXMLFilePathDest = encDevSandhillsAcceptFolderPath + "\\" + acceptedXMLFileName;
//
//					//Copy xml file to \\192.168.10.93->d$->sftp->sandhills_accept
//					fileUtil.copyFile(acceptedXMLFilePath, acceptedXMLFilePathDest);
//					fileUtil.findFile(acceptedXMLFileName, encDevSandhillsAcceptFolderPath, 1);
//					report(LogStatus.PASS, "Incoming 837 file placed at folder -" + encDevSandhillsAcceptFolderPath);
//
//					dbUtil.executeSP(conStr, "asp_837_process_wrapper");
//					report(LogStatus.PASS, "Executed wrapper - asp_837_process_wrapper");
//					
//					//Verify file processed from \\192.168.10.93->d$->sftp->sandhills_accept
//					if (fileUtil.isFileProcessed(acceptedXMLFileName, encDevSandhillsAcceptFolderPath, 30)) {
//						report(LogStatus.PASS, "File processed from folder -" + encDevSandhillsAcceptFolderPath);

						String batNum = (dbUtil.executeQuery(conStr,
								"select top 1 bat_num from tb_ic_837P_processing_det h order by h.bat_num desc"))
								.get("bat_num");
						 
					
						Map<String,String> map = dbUtil.executeQuery(conStr,
								"select distinct clm_hdr_id from tb_ic_837P_processing_det h where bat_num = 1005" );
						
						
						
						System.out.println(map);
						
						List<String> li= dbUtil.executeQueryHeader(conStr, "select distinct clm_hdr_id from tb_ic_837P_processing_det h where bat_num = 1005");
				
						String claimHeads= (dbUtil.executeQuery(conStr,
								"select clm_hdr_id from tb_ic_837P_processing_det h where bat_num = " + batNum))
								.get("clm_hdr_id");
						 
						
						
						if (claimHeads != null) {
							report(LogStatus.PASS, "Claim header ID = " + claimHeads);

							loginPage = new MCOLoginPage(driver);

							loginPage.selectMCOLogin();

							homePage = loginPage.login(userName, passWord);

							if (homePage.isLoginSuccessful()) {
								report(LogStatus.PASS, "Login successful with user - " + userName);
							} else {
								report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
							}

							claimMaintenancepage = homePage.navigateToClaimMaintenance();

							claimMaintenancepage.isClaimMaintenancePageDisplayed();

							claimMaintenancepage.clickFilter();

							claimMaintenancepage.searchWithHeaderAndViewClaim(claimHeads);
						} else {
							report(LogStatus.FAIL, "Unable to get Claim header ID from database");
						}

//					} else {
//						report(LogStatus.FAIL, "File processed failed from folder -" + encDevSandhillsAcceptFolderPath);
//					}
//
//				} else {
//					report(LogStatus.FAIL, "Accepted xml file not found in folder -" + sandhillsAcceptFolderPath);
//				}
//
//			} else {
//				report(LogStatus.FAIL, "File not found in folder -" + testSFTPArchiveFolderPath);
//			}
//		} else {
//			report(LogStatus.FAIL, "File process failed from folder -" + testSFTPFilePath);
//		}

	}

	
}
	
	
