package org.alphind.alphamcs.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

/**
 * The TestBase class is the base class to fetch environment specific
 * configuration parameters from Jenkins/Maven. Based on the parameters, it
 * performs the browser setup and tear-down functions.
 * 
 * @author Abhishek.K
 */

public class CommonFunctions extends TestBase {

	@SuppressWarnings("unused")
	private static final Logger log = LogManager.getLogger(CommonFunctions.class);

	public void tabAndEnter() {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.TAB).sendKeys(Keys.ENTER);
		actions.build().perform();
	}

	public void clickUsingJS(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void click(WebElement element, String name) {
		try {
			element.click();
			report(LogStatus.INFO, "Clicked on - " + name);
		} catch (Exception e) {
			report(LogStatus.FAIL, "failed to click on - " + name);
			/*
			if (MCOHomePage.errorMessage.isDisplayed()) {
				report(LogStatus.FAIL, MCOHomePage.errorMessage.getText());
			}
			*/
			e.printStackTrace();
		}
	}

	public void scrollToElement(WebElement element, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}
	
	public void jsScrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public void sendKeys(WebElement element, String name, String value) {
		element.sendKeys(value);
		if(!name.equalsIgnoreCase("PASSWORD"))
		report(LogStatus.INFO, "Entered '" + value + "' in " + name);
	}

	public void selectDropDown(WebElement element, String name, String value) {
		element.click();
		putStaticWait(1);
		driver.findElement(By.xpath("//*[text()='" + value + "' and @class='mat-option-text']")).click();
		report(LogStatus.INFO, "Selected '" + value.trim() + "' in the dropdown " + name);
	}

	public void selectDropDown(String name, String value) {
		//element.click();
		putStaticWait(1);
		driver.findElement(By.xpath("//*[text()='" + value + "' and @class='mat-option-text']")).click();
		//driver.findElement(By.xpath("//*[text()='" + value + "' and @class='mat-option-text']")).click();
		report(LogStatus.INFO, "Selected '" + value.trim() + "' in the dropdown " + name);
	}
	
	public void selectAutoPopulateDropDown(WebElement element, String name, String value) {
		element.click();
		putStaticWait(1);
		sendKeys(element, name, value);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(driver.findElement(By.xpath("//div[contains(text(),'Loading')]"))
				.isDisplayed()) {
			waitUntilElementInvisible(By.xpath("//div[contains(text(),'Loading')]"), 
					30);
		}
		
		WebElement ele = driver.findElement(By.xpath("//*[text()='" + value + "' and @class='mat-option-text']"));//.click();
		waitUntilClickable(element, 30);
		ele.click();
		report(LogStatus.INFO, "Selected '" + value.trim() + "' in the dropdown " + name);
	}
	
	public void clickCheckbox(WebElement element, String name) {
		element.click();
		report(LogStatus.INFO, "Clicked on checkbox- " + name);
	}

	public static String getRandomNumberString() {
		Random rnd = new Random();
		int number = rnd.nextInt(99999999);
		return Integer.toString(number);
	}

	public String getRandomLastName() {
		return "TestLastName" + getRandomNumberString();
	}

	public String getRandomFirstName() {
		return "TestFirstName" + getRandomNumberString();
	}

	public void putStaticWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitUntilClickable(WebElement ele, int seconds) {
		new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void waitUntilElementInvisible(By elexpath, int seconds) {
		new WebDriverWait(driver, seconds).until(ExpectedConditions.invisibilityOfElementLocated(elexpath));
	}

	public void waitUntilElementVisible(By elexpath, int seconds) {
		new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOfElementLocated(elexpath));
	}
	
	public String getCurrentDate() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return df.format(new Date());
	}

	public String getFutureDate() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.YEAR, 1);
		dt = c.getTime();
		return df.format(dt);
	}
	
	//Created by nandhalala
	//To get text value from web element
	public String getText(WebElement ele) {
		return ele.getText();
	}
	
	//created by nandhalala
	//To get attribute value from element
	public String getAttribute(WebElement ele, String attributeName) {
		return ele.getAttribute(attributeName);
	}
	
	//created by nandhalala
	public void waitForLoadingToDisappear() {
		if(driver.findElement(By.xpath("//div[contains(text(),'Loading')]"))
				.isDisplayed()) {
			waitUntilElementInvisible(By.xpath("//div[contains(text(),'Loading')]"), 
					30);
		}
	}
	
}
