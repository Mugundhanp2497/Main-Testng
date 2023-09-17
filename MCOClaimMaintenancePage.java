package org.alphind.alphamcs.pages;

import org.alphind.alphamcs.base.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

//Created by Nandhalala
public class MCOClaimMaintenancePage extends CommonFunctions {

	private WebDriver driver;
	
	@FindBy(xpath = "//div[text()='Claim Maintenance']")
	private WebElement claimMaintenanceHeading;
	
	@FindBy(xpath = "//span[text()='Filter ']/parent::button")
	private WebElement filterButton;
	
	@FindBy(xpath = "//mat-label[text()='Claim Header ID']/parent::label/parent::span/preceding-sibling::input")
	private WebElement filterClaimHeaderIdInputBox;
	
	@FindBy(xpath = "//mat-label[text()='My MCS Claim #']/parent::label/parent::span/preceding-sibling::input")
	private WebElement filterMyMCSClaimNumber;
	
	@FindBy(xpath = "//span[text()=' Search ']/parent::button")
	private WebElement filterSearchButton;
	
	@FindBy(xpath = "//tbody/tr[1]")
	private WebElement firstRowRecord;
	
	@FindBy(xpath = "//label[text()=\"Status:\"]/following-sibling::span")
	private WebElement claimStatus;
	
	@FindBy(xpath = "//label[text()=\"Adjustment Reasons:\"]/following-sibling::span")
	private WebElement adjustmentReason;
	
	public MCOClaimMaintenancePage(WebDriver driver){
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	//Created by Nandhalala
	public boolean isClaimMaintenancePageDisplayed() {
		waitForLoadingToDisappear();
		
		putStaticWait(5);
		
		if(claimMaintenanceHeading.isDisplayed()) {
			if(getText(claimMaintenanceHeading).equalsIgnoreCase("Claim Maintenance")) {
				report(LogStatus.PASS,"Claim Maintenance heading is displayed.");
			}else {
				report(LogStatus.FAIL,"Claim Maintenance Heading is not displayed.");
			}
			return true;
		}
		return false;
		
	}
	
	//Created by Nandhalala
	public void clickFilter() {
		
		waitForLoadingToDisappear();
		putStaticWait(2);
		waitUntilClickable(filterButton, 30);
		click(filterButton, "Filter");
		
	}
	
	//Created by Nandhalala
	public void searchWithHeaderAndViewClaim(String claimHeaderId) {
		
		sendKeys(filterClaimHeaderIdInputBox, "Claim Header ID", claimHeaderId);
		putStaticWait(2);
		waitUntilClickable(filterSearchButton, 30);
		click(filterSearchButton, "Search");
		waitForLoadingToDisappear();
		putStaticWait(2);
		click(firstRowRecord, claimHeaderId);
		waitForLoadingToDisappear();
		putStaticWait(2);
		driver.findElement(By.xpath("//span[text()=' Quick View ']/parent::button")).click();
		waitForLoadingToDisappear();
		putStaticWait(2);
		String status = getText(claimStatus);
		putStaticWait(2);
		String reason = getText(adjustmentReason);
		System.out.println(status + "\n" + reason);
		report(LogStatus.INFO,"The status of claim Header "+claimHeaderId+" is "+status+
				" with reason : "+reason);
	}
	
	
	//Created by Nandhalala
	public void searchWithMCSNumberAndViewClaim(String mcsnumber) {
		
		putStaticWait(2);
		sendKeys(filterMyMCSClaimNumber, "My MCS Claim Number", mcsnumber);
		putStaticWait(2);
		waitUntilClickable(filterSearchButton, 30);
		click(filterSearchButton, "Search");
		waitForLoadingToDisappear();
		putStaticWait(2);
		click(firstRowRecord, mcsnumber);
		waitForLoadingToDisappear();
		putStaticWait(2);
		driver.findElement(By.xpath("//span[text()=' Quick View ']/parent::button")).click();
		waitForLoadingToDisappear();
		putStaticWait(2);
		String status = getText(claimStatus);
		putStaticWait(2);
		String reason = getText(adjustmentReason);
		System.out.println(status + "\n" + reason);
		report(LogStatus.INFO,"The status of claim Header "+mcsnumber+" is "+status+
				" with reason : "+reason);
	}
	
}
