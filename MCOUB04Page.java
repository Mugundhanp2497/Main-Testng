package org.alphind.alphamcs.pages;

import org.alphind.alphamcs.base.CommonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.codoid.products.fillo.Select;

public class MCOUB04Page extends CommonFunctions {

	WebDriver driver;
	static String patientLastName;
	static String patientFirstName;

	//private static final Logger log = LogManager.getLogger(MCOUB04Page.class);

	@FindBy(xpath = "//span[text()='Create ']")
	WebElement createButton;
	
	@FindBy(xpath="//span[text()=' Provider Search ']")
	WebElement providerSearchBtn;

	@FindBy(xpath="//mat-label[text()='Provider ID']/parent::label/ancestor::span/preceding-sibling::input")
	WebElement serchPrvID;
	
	@FindBy(xpath="//span[text()='Search']")
	WebElement prvSearch;
	
	@FindBy(xpath="(//div/table/tbody)[9]/tr")
	WebElement selectfirstPrv;
	
	@FindBy(xpath="//span[contains(text(),'Select Provider')]/parent::button")
	WebElement selectProvider;
	
	@FindBy(xpath="//mat-select[@id='mat-select-2']")
	WebElement clickSitedrop;
	
	@FindBy(xpath="//span[text()=' 2589 - TERMED- No Bounds Care, Inc. ']")
	WebElement selectSite;
	
	@FindBy(xpath="//span[@class='ml-1 text-gray']")
	WebElement claimnum;
	
	@FindBy(xpath="//input[@ng-reflect-name='Bill_type_04']")
	WebElement billType;
	
	@FindBy(xpath="//input[@formcontrolname='Stmt_prd_frm_06']")
	WebElement fromDate;
	
	@FindBy(xpath="//input[@formcontrolname='Stmt_prd_to_06']")
	WebElement toDate;
	
	@FindBy(xpath="//span[text()='Search ']")
	WebElement searchPatient;
    
	@FindBy(xpath="//mat-label[text()='Patient ID']/parent::label/ancestor::span/preceding-sibling::input")
	WebElement searchPatientId;
	
	@FindBy(xpath="//span[text()='Search']")
	WebElement searchpat;
	
	@FindBy(xpath="(//div/table/tbody)[9]/tr")
	WebElement selectFirstPat;
	
	@FindBy(xpath="//span[text()='Select Patient']")
	WebElement selectPatientbtn;
	
	@FindBy(xpath="//span[text()='Select Patient']")
	WebElement selectPatient;
	
	@FindBy(xpath="//input[@formcontrolname='Admsn_dt_12']")
	WebElement signDate;
	
	@FindBy(xpath="//span[text()=' Add ']")
	WebElement addServcie;
	
	@FindBy(xpath="//input[@formcontrolname='Rev_cd']")
	WebElement revCode;
	
	@FindBy(xpath="//input[@formcontrolname='Units']")
	WebElement units;
	
	@FindBy(xpath="//input[@formcontrolname='Charge']")
	WebElement charges;
	
	@FindBy(xpath="//input[@formcontrolname='Noncoveredcharge']")
	WebElement noncovercharge;
	
	@FindBy(xpath="(//span[text()=' Save ']/parent::button)[2]")
	WebElement saveService;
	
	@FindBy(xpath="//input[@formcontrolname='Charge']")
	WebElement toatlcharge;
	
	@FindBy(xpath="//div[@role='listbox']/child::mat-option[2]")
	WebElement billingNPI;
	
	@FindBy(xpath="//mat-select[@formcontrolname='Bill_prv_npi_56']")
	WebElement billingNPIclick;
	// patient Search elements

	////////////////// Implementations

	public MCOUB04Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String createUB04Claim( ) {
		String claimId="";
		//waitUntilClickable(createButton, 30);
		waitForLoadingToDisappear();
		
		putStaticWait(2);
		click(createButton, "Create button");
		putStaticWait(2);
		click(providerSearchBtn,"Provider search button ");
		putStaticWait(5);
		sendKeys(serchPrvID,"Provider Id","1688");
		putStaticWait(2);
		click(prvSearch,"Provider search");
		putStaticWait(2);
		click(selectfirstPrv,"Select First Provider");
		putStaticWait(2);
		click(selectProvider,"Select Provider");
		putStaticWait(8);
		click(clickSitedrop,"expand site dropdown");
		putStaticWait(2);
		click(selectSite,"Select Site");
		putStaticWait(2);
		System.out.println(getText(claimnum));
		sendKeys(billType,"Bill Type","0112");
		sendKeys(fromDate,"From Datw","08/01/2023");
		sendKeys(toDate,"To Date","08/01/2023");
		click(searchPatient,"Search Patient button");
		putStaticWait(2);
		sendKeys(searchPatientId,"Enter paient ID","6798");
		putStaticWait(2);
		click(searchpat,"search Patient");
		putStaticWait(2);
		click(selectFirstPat,"select First Pat");
		putStaticWait(2);
		click(selectPatient,"Select Patient");
		putStaticWait(2);
		sendKeys(signDate,"sign Date","08/01/2023");
		putStaticWait(2);
		click(addServcie,"add Servcie");
		putStaticWait(2);
		sendKeys(revCode,"rev Code","0100");
		putStaticWait(2);
		sendKeys(toatlcharge,"toatlcharge","10");
		putStaticWait(2);
		sendKeys(units,"units","1");
		putStaticWait(2);
		sendKeys(noncovercharge,"non covered charge","0");
		putStaticWait(2);
		click(saveService,"save Service");
		putStaticWait(2);
		click(billingNPIclick,"billingNPIclick");
		click(billingNPI,"billin NPI");
		
		
		
		

		return claimId;
	}

}
