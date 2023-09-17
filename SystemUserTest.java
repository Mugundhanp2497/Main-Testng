package org.alphind.alphamcs.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.alphind.alphamcs.base.TestBase;
import org.alphind.alphamcs.pages.MCOHomePage;
import org.alphind.alphamcs.pages.MCOLoginPage;
import org.alphind.alphamcs.pages.MCOSysUsersPage;
import org.alphind.alphamcs.util.DBUtil;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.LogStatus;

public class SystemUserTest extends TestBase {

	//private static final Logger log = LogManager.getLogger(AlphaMCSTest.class);

	DBUtil dbUtil;
	MCOLoginPage loginPage;
	MCOHomePage homePage;
	MCOSysUsersPage SystemUsersPage;
	
	String userName = "shc/shctest";
	String passWord = "Letmein@12345";
	
//	public static void main(String[] args) throws IOException {
//		InputStream configFile = new FileInputStream(
//				System.getProperty("user.dir") + "\\config\\" + "dev" + ".properties");
//		Properties envConfig;
//		envConfig = new Properties();
//		envConfig.load(configFile);
//		
//		String conStr = envConfig.getProperty("devDBConnectionString");
//	String query = "SELECT * FROM alphamcs_shc.dbo.tb_sys_users where usr_login like 'tLgn19287214'";
//	DBUtil dbUtil = new DBUtil();
//
//	System.out.println(dbUtil.executeQuery(conStr, query));
//		
//	}
	
	@Test
	public void createNewSystemUser() {
		
		report(LogStatus.INFO, "Verify whether able to create a new system user.");
		
		loginPage = new MCOLoginPage(driver);
		
		loginPage.selectMCOLogin();
		
		homePage = loginPage.login(userName, passWord);
		
		if (homePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}
		
		SystemUsersPage = homePage.navigateToSystemUsers();
		
		if(SystemUsersPage.isSystemUserPageDisplayed()) {
			report(LogStatus.PASS,"System User Page is isplayed");
		}else {
			report(LogStatus.FAIL,"System User Page is not displayed");
		}
		
		String[] userDetails = SystemUsersPage.createNewSystemUser();
		
		SystemUsersPage.insertNewUserDetails("createNewSystemUser", userDetails[1], userDetails[0]);
		
		SystemUsersPage.filterUserWithId(userDetails[1]);
		
		String conStr = envConfig.getProperty("devDBConnectionString");
		String query = "SELECT * FROM alphamcs_shc.dbo.tb_sys_users where usr_login = '"
					+userDetails[1]+"'";
		dbUtil = new DBUtil();
		
		Map<String, String> sysUserData = dbUtil.executeQuery(conStr, query);
		
		String expectedlogin = sysUserData.get("usr_login");
		
		if(expectedlogin.equalsIgnoreCase(userDetails[1])) {
			report(LogStatus.PASS,"System User login is " + userDetails[1]);
		}
		else {
			report(LogStatus.FAIL,"System User login is " + userDetails[1] + " .Expected value is "
						+expectedlogin);
		}
	}
	
	
}
