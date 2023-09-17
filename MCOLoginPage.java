package org.alphind.alphamcs.pages;

import org.alphind.alphamcs.base.CommonFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MCOLoginPage extends CommonFunctions {

	WebDriver driver;

	private static final Logger log = LogManager.getLogger(MCOLoginPage.class);

	@FindBy(xpath = "//input[@ng-reflect-name='userName'] | //input[@ng-reflect-name='UserName']")
	WebElement username;

	@FindBy(xpath = "//input[@ng-reflect-name='password'] | //input[@ng-reflect-name='Password']")
	WebElement password;

	@FindBy(xpath = "//*[@type='submit']")
	WebElement loginButton;

	@FindBy(xpath = "(//a[text()='Click here'])[2]")
	WebElement portalLoginLink;

	@FindBy(xpath = "(//a[text()='Click here'])[1]")
	WebElement mcoLoginLink;

	public MCOLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setUsername(String uname) {
		sendKeys(username, "User Name", uname);
	}

	public void setPassword(String pwd) {
		sendKeys(password, "Password", pwd);
	}

	public void clickLoginButton() {
		tabAndEnter();
	}

	public MCOHomePage login(String username, String password) {
		log.info("Logging with username - " + username);
		setUsername(username);
		setPassword(password);
		putStaticWait(1);
		clickLoginButton();
		return new MCOHomePage(driver);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void selectPortalLogin() {
		click(portalLoginLink, "Portal Login Link");
	}

	public void selectMCOLogin() {
		click(mcoLoginLink, "MCO Login Link");
	}

}
