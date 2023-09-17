package org.alphind.alphamcs.pages;

import org.alphind.alphamcs.base.CommonFunctions;
import org.alphind.alphamcs.util.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

//Created by Nandhalala
public class MCOSysUsersPage extends CommonFunctions {

	private WebDriver driver;
	
	private static String userLastName;
	
	private static String userFirstName;
	
	private static String userMiddleInitial;
	
	//private static final Logger log = LogManager.getLogger(MCOSysUsersPage.class);
	
	@FindBy(xpath = "//div[contains(text(),'System Users')]")
	private WebElement sysUserPageHeading;
	
	@FindBy(xpath = "//span[contains(text(),'Filter')]/parent::button")
	private WebElement filterButton;
	
	@FindBy(xpath = "//mat-label[contains(text(),'User ID')]"
			+ "/ancestor::label/parent::span/parent::div/input")
	private WebElement filterUserIdTextbox;
	
	@FindBy(xpath = "//span[contains(text(),'Search')]/parent::button")
	private WebElement filterSearchButton;
	
	@FindBy(xpath = "//span[contains(text(),'Create')]/parent::button")
	private WebElement createButton;
	
	@FindBy(xpath = "//span[contains(text(),'Create System User')]")
	private WebElement createSysUserHeading;
	
	@FindBy(xpath = "//mat-label[contains(text(),'User ID')]/parent::label/parent::span/preceding-sibling::input")
	private WebElement userId;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Last Name')]/parent::label/parent::span/preceding-sibling::input")
	private WebElement lastName;
	
	@FindBy(xpath = "//mat-label[contains(text(),'First Name')]/parent::label/parent::span/preceding-sibling::input")
	private WebElement firstName;
	
	@FindBy(xpath = "//mat-label[contains(text(),'First Name')]/parent::label/parent::span/preceding-sibling::input")
	private WebElement middleInitial;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Login')]/parent::label/parent::span/preceding-sibling::input")
	private WebElement login;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Password')]/parent::label/parent::span/preceding-sibling::input")
	private WebElement password;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Department')]/parent::label/parent::span/preceding-sibling::input")
	private WebElement department;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Manager')]/parent::label/parent::span/preceding-sibling::input")
	private WebElement manager;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Title')]/parent::label/parent::span/preceding-sibling::input")
	private WebElement title;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Email')]/parent::label/parent::span/preceding-sibling::input")
	private WebElement email;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Phone 1')]/parent::label/parent::span/preceding-sibling::input")
	private WebElement phone1;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Phone ')]/parent::label/parent::span/preceding-sibling::input")
	private WebElement phone2;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Expiry Date')]/parent::label/parent::span/preceding-sibling::input")
	private WebElement expiryDate;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Expiry Date')]/parent::label/parent::span/parent::div/following-sibling::div/mat-datepicker-toggle/button")
	private WebElement expiryDatePicker;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Out-of-Office Start Time')]/parent::label/parent::span/preceding-sibling::input")
	private WebElement outofOfficeStartTime;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Out-of-Office Start Time')]/parent::label/parent::span/parent::div/following-sibling::div/mat-datepicker-toggle/button")
	private WebElement outofOfficeStartTimeDatePicker;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Out-of-Office End Time')]/parent::label/parent::span/preceding-sibling::input")
	private WebElement outofOfficeEndTime;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Out-of-Office End Time')]/parent::label/parent::span/parent::div/following-sibling::div/mat-datepicker-toggle/button")
	private WebElement outofOfficeEndTimeDatePicker;
	
	@FindBy(xpath = "//mat-label[text()='Comments']/parent::label/parent::span/preceding-sibling::textarea")
	private WebElement comments;
	
	@FindBy(xpath = "//mat-label[text()='Out-of-Office Comments']/parent::label/parent::span/preceding-sibling::textarea")
	private WebElement outofOfficeComments;
	
	@FindBy(xpath = "//span[contains(text(),'Account Locked')]/parent::label/parent::mat-checkbox")
	private WebElement accountLocked;
	
	@FindBy(xpath = "//span[contains(text(),'Show User Change History')]/parent::button")
	private WebElement showuserchangehistoryButton;
	
	@FindBy(xpath = "//span[contains(text(),'Save')]/parent::button")
	private WebElement saveButton;
	
	@FindBy(xpath = "//span[contains(text(),'Cancel')]/parent::button")
	private WebElement cancelButton;
	
	@SuppressWarnings("unused")
	private String table_header_elements = "//thead/tr/th";
	
	@SuppressWarnings("unused")
	private String table_header_data = "//thead/tr/th/div/div[1]";
	
	private String table_body_rows = "//tbody/tr";
	
	private String table_body_data = table_body_rows+"rowindex"+"/td"+"columnindex";
	
	public MCOSysUsersPage(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	
	//Created by Nandhalala
	public boolean isSystemUserPageDisplayed() {
		
		if(sysUserPageHeading.isDisplayed()) {
			if(getText(sysUserPageHeading).equalsIgnoreCase("System Users")) {
				report(LogStatus.PASS,"System Users heading is displayed.");
			} else {
				report(LogStatus.FAIL, "System Users heading is not displayed.");
			}
			return true;
		}
		return false;
	}
	
	//Created by Nandhalala
	private void clickCreate() {
		
		waitUntilClickable(createButton, 30);
		
		waitForLoadingToDisappear();

		putStaticWait(5);
		
		click(createButton, "Create Button");
	}
	
	//Created by Nandhalala
	public String[] createNewSystemUser() {
		
		waitForLoadingToDisappear();
		
		clickCreate();
		
		userLastName = dataMap.get("LastName");
		//System.out.println("Last Name = "+userLastName);
		userFirstName = dataMap.get("FirstName");
		//System.out.println("First Name = "+userFirstName);
		userMiddleInitial = dataMap.get("Middle Initial");
		
		sendKeys(firstName, "User First Name", userFirstName);
		
		sendKeys(lastName, "User Last Name", userLastName);
		
		sendKeys(middleInitial, "Middle Initial", userMiddleInitial);
		
		String newLogin = dataMap.get("Login")+getRandomNumberString();
		
		sendKeys(login, "User Login", newLogin);
		
		//ExcelUtil.updateExcelData("testData//AlphaPlusTestData.xlsx", "testDataSheet", methodName, "generatedLoin", value);

		
		sendKeys(password, "Password", dataMap.get("Password"));
				
		//sendKeys(phone1, "Phone 1", dataMap.get("Phone1"));
		
		sendKeys(department, "Department", dataMap.get("Department"));
		
		waitForLoadingToDisappear();
		
		driver.findElement(By.xpath("//*[@class='mat-option-text']")).click();
		
		//selectDropDown("Department", dataMap.get("Department"));
		
		sendKeys(manager, "Manager", dataMap.get("Manager"));
		
		waitForLoadingToDisappear();
		
		//driver.findElement(By.xpath("//*[@class='mat-option-text']")).click();
		
		//selectDropDown("Manager", dataMap.get("Manager"));
		
		waitForLoadingToDisappear();
		
		waitUntilClickable(saveButton, 30);
		
		click(saveButton, "Save");
		
		waitForLoadingToDisappear();
		
		String userIdXpath = table_body_data.replaceAll("columnindex","[1]");
		
		userIdXpath = userIdXpath.replaceAll("rowindex","[1]");
		
		String newUserId = getText(driver.findElement(By.xpath(userIdXpath)));
		
		waitForLoadingToDisappear();
		
		return new String[] {newUserId,newLogin};
		
	}
	
	public void insertNewUserDetails(String methodName,String newLogin ,String userID) {
		ExcelUtil.updateExcelData("testData//AlphaPlusTestData.xlsx", "testDataSheet", methodName, "userId", userID);
		ExcelUtil.updateExcelData("testData//AlphaPlusTestData.xlsx", "testDataSheet", methodName, "generatedLogin", newLogin);
	}
	
	
	//Created by Nandhalala
	public void filterUserWithId(String userId) {
		
		click(filterButton, "Filter Button");
		
		sendKeys(filterUserIdTextbox, "User ID", userId);
		
		click(filterSearchButton, "Search");
		
	}
	
}
