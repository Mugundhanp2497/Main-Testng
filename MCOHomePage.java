package org.alphind.alphamcs.pages;

import org.alphind.alphamcs.base.CommonFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MCOHomePage extends CommonFunctions {

	WebDriver driver;

	@SuppressWarnings("unused")
	private static final Logger log = LogManager.getLogger(MCOHomePage.class);

	@FindBy(xpath = "//span[contains(text(),'Logout')]")
	WebElement logout;
	
	@FindBy(xpath = "//div[contains(text(),'Loading')]")
	private WebElement loading;

	@FindBy(xpath = "//*[@role='alertdialog']")
	public static WebElement errorMessage;

	@FindBy(xpath = "//*[@href='#navbarDropdownPatient']")
	WebElement patientLinkNavbar;

	@FindBy(xpath = "//*[text()='Enrollments']")
	WebElement patientEnrollmentsLink;

	@FindBy(xpath = "//*[contains(text(),'Create')]")
	WebElement createButton;

	@FindBy(xpath = "//*[text()='Finance']")
	WebElement financeLink;

	@FindBy(xpath = "//*[text()='CMS 1500']")
	WebElement CMS1500Link;

	@FindBy(xpath = "//*[text()='UB-04']")
	WebElement UB04Link;

	@FindBy(xpath="//a[contains(text(),'User Maintenance')]")
	private WebElement userMaintenance;
	
	@FindBy(xpath = "//span[contains(text(),'Master')]/preceding-sibling::i")
	private WebElement master;
	
	@FindBy(xpath = "//a[contains(text(),'System Users')]")
	private WebElement systemUsers;
	
	@FindBy(xpath = "//a[text()=' Claims ']")
	private WebElement claims;
	
	@FindBy(xpath = "//a[text()='Claim Maintenance']")
	private WebElement claimMaintenance;
	
	public MCOHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isLoginSuccessful() {
		waitForLoadingToDisappear();
		if (logout.isDisplayed()) {
			return true;
		} else {
			return false;
		}

	}

	public void logout() {
		click(logout, "Logout");
	}

	public void naviagteToPatientEnrollments() {
		click(patientLinkNavbar, "Patient Link");
		click(patientEnrollmentsLink, "Patient enrollments");
		waitUntilClickable(createButton, 30);
	}

//	public void naviagteToCMS1500() {
//		click(financeLink, "Finance Link");
//		click(CMS1500Link, "CMS 1500");
//		waitUntilClickable(createButton, 30);
//	}

	public MCOUB04Page naviagteToUB04() {
		
		if(!CMS1500Link.isDisplayed()) {
			clickFinance();
		}
	 
		click(UB04Link, "UB-04");
		waitUntilClickable(createButton, 30);
		return new MCOUB04Page(driver);
	}

	//Created by Nandhalala
	//To click the master link
	private void clickMaster() {
		
		waitUntilElementInvisible(By.xpath(("//div[contains(text(),'Loading')]")), 10);
		waitUntilClickable(master, 30);
		click(master, "Master");
		
	}
	
	//Created by Nandhalala
	//To click the user maintenance
	private void clickUserMaintenance() {
		
		if(!userMaintenance.isDisplayed()) {
			clickMaster();
		}
		waitUntilClickable(userMaintenance, 30);
		click(userMaintenance, "User Maintenance");
		
	}
	
	//Created by Nandhalala
	//To click the system users
	public MCOSysUsersPage navigateToSystemUsers() {
		
		if(!systemUsers.isDisplayed()) {
			clickUserMaintenance();
		}
		waitUntilClickable(systemUsers, 30);
		click(systemUsers, "System Users");
		return new MCOSysUsersPage(driver);
		
	}
	
	//Created by Nandhalala
	//To click the finance link
	private void clickFinance() {
		
		waitUntilElementInvisible(By.xpath(("//div[contains(text(),'Loading')]")), 10);
		waitUntilClickable(financeLink, 30);
		click(financeLink, "Finance");
		
	}
	
	private void clickClaims() {
		
		if(!claims.isDisplayed()) {
			clickFinance();
		}
		waitUntilClickable(claims, 30);
		click(claims, "Claims");
		
	}
	
	public MCOClaimMaintenancePage navigateToClaimMaintenance() {
		if(!claimMaintenance.isDisplayed()) {
			clickClaims();
		}
		waitForLoadingToDisappear();
		waitUntilClickable(claimMaintenance, 30);
		click(claimMaintenance, "Claim Maintenance");
		waitForLoadingToDisappear();
		return new MCOClaimMaintenancePage(driver);
		
	}
	
	public MCOCMS1500Page navigateToCMS1500Page() {
		
		if(!CMS1500Link.isDisplayed()) {
			clickFinance();
		}
		waitForLoadingToDisappear();
		waitUntilClickable(CMS1500Link, 30);
		click(CMS1500Link, "CMS 1500");
		waitForLoadingToDisappear();
		return new MCOCMS1500Page(driver);
		
	}
	
}
