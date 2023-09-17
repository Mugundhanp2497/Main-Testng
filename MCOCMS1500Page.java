package org.alphind.alphamcs.pages;

import static org.testng.Assert.assertEquals;
import org.alphind.alphamcs.base.CommonFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MCOCMS1500Page extends CommonFunctions {

	WebDriver driver;
	static String patientLastName;
	static String patientFirstName;

	@SuppressWarnings("unused")
	private static final Logger log = LogManager.getLogger(MCOCMS1500Page.class);

	@FindBy(xpath = "//div[contains(text(),\"CMS 1500\")]")
	private WebElement cms1500heading;
	
	@FindBy(xpath = "//span[contains(text(),'Create')]/parent::button")
	WebElement createButton;

	@FindBy(xpath = "//mat-label[text()='a. EMPLOYMENT (current or previous)']/parent::div/div/mat-radio-group/mat-radio-button[3]")
	private WebElement employmentStatusUnknown;
	
	@FindBy(xpath = "//mat-label[text()='b. AUTO ACCIDENT']/parent::div/div/mat-radio-group/mat-radio-button[3]")
	private WebElement autoAccientUnknown;
	
	@FindBy(xpath = "//mat-label[text()='c. OTHER ACCIDENT']/parent::div/div/mat-radio-group/mat-radio-button[3]")
	private WebElement otherAccientUnknown;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Pat_sign_dt')]")
	private WebElement patientSignDate;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Diag_1_')]")
	private WebElement diagnosis1;
	
	//patient search elements
	@FindBy(xpath = "(//span[contains(text(),'Search ')]/parent::button)[1]")
	private WebElement patientSearchButton;
	
	@FindBy(xpath = "//mat-label[text()='Patient ID']/parent::label/parent::span/preceding-sibling::input")
	private WebElement patientID;
	
	@FindBy(xpath = "//span[text() = 'Patient Search']")
	private WebElement headingPatientSearch;
	
	@FindBy(xpath = "//span[text() = 'Search']/parent::button")
	private WebElement searchPatient;
	
	@FindBy(xpath = "//span[text()='Select Patient']/parent::button")
	private WebElement selectPatientButton;

	
	//provider search components
	@FindBy(xpath = "//span[contains(text(),'Provider Search')]/parent::button")
	private WebElement providerSearchButton;
	
	@FindBy(xpath = "(//mat-label[text()='Provider ID'])[2]/parent::label/ancestor::span/preceding-sibling::input")
	private WebElement providerId;
	
	@FindBy(xpath = "(//span[contains(text(),'Search')]/parent::button)[3]")
	private WebElement searchProvider;
	
	@FindBy(xpath = "//span[contains(text(),'Select Provider')]/parent::button")
	private WebElement selectProvider;
	
	
	//Add service components
	@FindBy(xpath = "//span[contains(text(),'Add')]/parent::button")
	private WebElement addService;
	
	@FindBy(xpath = "(//mat-label[text()='From'])[3]/parent::label/parent::span/preceding-sibling::input")
	private WebElement fromDate;
	
	@FindBy(xpath = "(//mat-label[text()='To'])[3]/parent::label/parent::span/preceding-sibling::input")
	private WebElement toDate;
	
	@FindBy(xpath = "//mat-select[contains(@formcontrolname,'Place')]")
	private WebElement selectPOS;
	
	@FindBy(xpath = "//input[contains(@placeholder,'Search')]")
	private WebElement inputPOS;
	
	@FindBy(xpath = "//mat-label[text()='Service Code']/parent::label/parent::span/preceding-sibling::input")
	private WebElement serviceCode;
	
	@FindBy(xpath = "//mat-label[text()='Charges']/parent::label/parent::span/preceding-sibling::input")
	private WebElement charges;
	
	@FindBy(xpath = "//mat-label[text()='Days/Units']/parent::label/parent::span/preceding-sibling::input" )
	private WebElement daysPerUnits;
	
	@FindBy(xpath = "//mat-label[text()='Diag. Pointer']/parent::label/parent::span/preceding-sibling::input")
	private WebElement diagpointer;
	
	@FindBy(xpath = "//mat-label[text()='Taxonomy Code']/parent::label/parent::span/preceding-sibling::input")
	private WebElement renderingTaxonomy;
	
	@FindBy(xpath = "(//span[contains(text(),'Save')])[2]/parent::button")
	private WebElement saveService;
	
	
	
	@FindBy(xpath = "(//mat-label[text()='Date']/following::mat-form-field/div/div/div/input)[1]")
	private WebElement physicianSignDate;
	
	@FindBy(xpath = "//mat-checkbox[contains(@formcontrolname,'Phy_sign_31')]")
	private WebElement physicianSignCheckBox;
	
	@FindBy(xpath = "(//mat-label[text()='Phone']/following::mat-form-field/div/div/div/input)[1]")
	private WebElement sitePhoneNumber;
	
	@FindBy(xpath = "(//mat-label[text()='b. Taxonomy Code']/following::mat-form-field/div/div/div/input)[1]")
	private WebElement billingTaxonomy;
	
	@FindBy(xpath = "//span[contains(text(),'Submit')]/parent::button")
	private WebElement submitClaim;
	
	@FindBy(xpath = "(//div[contains(text(),'My MCS Claim #')])[2]")
	private WebElement mymcsclaim;
	
	@FindBy(xpath="//span[text()='Filter ']")
	private WebElement cmsFilter;
	
	@FindBy(xpath="//input[@id='mat-input-28']")
	private WebElement filterclmno;
	
	  @FindBy(xpath = "//span[contains(text(),'Filter')]/parent::button")
	    private WebElement filterButton;

	    @FindBy(xpath = "//input[@formcontrolname='clmno']")
	    private WebElement filtermyMCSClaimNO;

	    @FindBy(xpath = "//span[contains(text(),'Search')]/parent::button")
	    private WebElement filterSearchButton;
	    
	    @FindBy(xpath="(//table/tbody/tr)[3]/td")
	    private WebElement firstServiceLine;
	    
	    @FindBy(xpath="//span[text()=' Modify ']")
	    private WebElement lineModify;
	
	    @FindBy(xpath="//span[text()='Update']/parent::button")
	    private WebElement updateCMS;
	    
	    @FindBy(xpath="//span[text()='11  Office']")
	    private WebElement getPOS;
	    
	    @FindBy(xpath="//span[text()=' Save ']/parent::button")
	    private WebElement saveCMS;
	    
	    @FindBy(xpath="//td[text()=' 250619 ']/ancestor::tbody")
	    private WebElement expandCMS;
	    
	    @FindBy(xpath = "//mat-select[contains(@formcontrolname,'Place')]/div/div/span/span")
	    private WebElement modifyPOS;
	    
	    @FindBy(xpath="(//table/tbody)[1]/tr[1]")
	    private WebElement expandFirstCMSRecord;
	    
	////////////////// Implementations

	public MCOCMS1500Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Created by Nandhalala
	public boolean isCMS1500PageDisplayed() {
		waitForLoadingToDisappear();
		if(cms1500heading.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	//Created by Nandhalala
	public String createAndSubmitClaim() {
		
		String patientid = dataMap.get("patientid"); 
		String patientSigndate = dataMap.get("patientSigndate");
		String diagnosisCode = dataMap.get("diagnosisCode");
		String providerID = dataMap.get("providerID");
		String serviceFromDate = dataMap.get("serviceFromDate");
		String serviceToDate = dataMap.get("serviceToDate"); 
		String POS = dataMap.get("POS");
		String procCode = dataMap.get("procCode");
		String serviceAmt = dataMap.get("serviceAmt");
		String daysUnits = dataMap.get("dayunits");
		String diagnosispointer = dataMap.get("diagnosispointer");
		String renderingtaxonomyCode = dataMap.get("renderingtaxonomyCode");
		String physiciansigndate = dataMap.get("physiciansigndate");
		String sitephno = dataMap.get("sitephno");
		String billingTaxonomyCode = dataMap.get("billingTaxonomyCode");

		String MyMCSNumber = "";
		waitUntilClickable(createButton, 30);
		putStaticWait(2);
		click(createButton, "Create button");
		waitForLoadingToDisappear();
		waitUntilClickable(patientSearchButton, 30);
		putStaticWait(2);
		click(patientSearchButton, "Patient Search button");
		if(patientID.isDisplayed()) {
			sendKeys(patientID, "Patient ID", patientid);
			String actualpatid = getAttribute(patientID, "ng-reflect-model");
			assertEquals(actualpatid, patientid, "Patient Id from text field is "+actualpatid+ 
					" not "+patientid);
		}
		click(searchPatient, "Search");
		waitForLoadingToDisappear();
		click(driver.findElement(By.xpath("//tbody/tr/td[1]")), 
				"Patient ID : "+patientid);
		
		click(selectPatientButton, "Select Patient");
		waitForLoadingToDisappear();
		putStaticWait(5);
		
		click(employmentStatusUnknown, "Unknown");
		
		click(autoAccientUnknown, "Unknown");
		
		click(otherAccientUnknown, "Unknown");
		
		sendKeys(patientSignDate, "Patient Sign Date", patientSigndate);
		putStaticWait(2);
		
		String actualPatientSignDate = getAttribute(patientSignDate, "value");
		assertEquals(actualPatientSignDate, patientSigndate,"Patient Sign Date from field is "
		+actualPatientSignDate+" not equals "+ patientSigndate);
		
		sendKeys(diagnosis1, "Diagnosis 1", diagnosisCode);
		waitForLoadingToDisappear();
		
		String actualDiagnosis1 = getAttribute(diagnosis1, "value");
		
		assertEquals(actualDiagnosis1, diagnosisCode, "Diagnosis 1 from field is "+actualDiagnosis1+
				" not equals "+diagnosisCode);
		
		putStaticWait(2);
		click(providerSearchButton, "Provider Search");
		waitForLoadingToDisappear();
		putStaticWait(1);
		sendKeys(providerId, "Provider ID", providerID);
		click(searchProvider, "Search");
		waitForLoadingToDisappear();
		putStaticWait(2);
		driver.findElement(By.xpath("//td[contains(text(),'"+providerID+"')]")).click();
		waitForLoadingToDisappear();
		putStaticWait(2);
		click(selectProvider, "Select Provider");
		waitForLoadingToDisappear();
		putStaticWait(1);
		click(addService, "Add service");
		waitForLoadingToDisappear();
		putStaticWait(1);
		sendKeys(fromDate, "From Date", serviceFromDate);
		sendKeys(toDate, "To Date", serviceToDate);
		click(selectPOS, "Place Of Service");
		sendKeys(inputPOS, "Place Of service", POS);
		putStaticWait(2);
		driver.findElement(By.xpath("//span[contains(text(),'"+POS+"')]")).click();
		sendKeys(serviceCode, "Service Code", procCode);
		sendKeys(charges, "Charges", String.valueOf(serviceAmt));
		waitForLoadingToDisappear();
		sendKeys(daysPerUnits, "Days/Units", String.valueOf(daysUnits));
		sendKeys(diagpointer, "Diagnosis Pointer", diagnosispointer);
		sendKeys(renderingTaxonomy, "Taxonomy code", renderingtaxonomyCode);
		waitForLoadingToDisappear();
		putStaticWait(2);
		WebElement renderingtaxonomy = driver.findElement(By.xpath("//span[contains(text(),'"+renderingtaxonomyCode+"')]"));
		click(renderingtaxonomy, renderingtaxonomyCode);
		putStaticWait(2);
		click(saveService, "Save Service");
		putStaticWait(2);
		click(physicianSignCheckBox, "Physician Sign Check Box");
		sendKeys(physicianSignDate, "Physician Sign Date", physiciansigndate);
		sendKeys(sitePhoneNumber, "Site Phone Number", sitephno);
		sendKeys(billingTaxonomy, "Taxonomy code", billingTaxonomyCode);
		waitForLoadingToDisappear();
		WebElement billingtaxonmy = driver.findElement(By.xpath("//span[contains(text(),'"+billingTaxonomyCode+"')]"));
		click(billingtaxonmy, billingTaxonomyCode);
		waitForLoadingToDisappear();
		MyMCSNumber = getText(mymcsclaim);
		click(submitClaim, "Submit button");
		
		//click(cmsFilter,"CMS Filter");
		//sendKeys(filterclmno,"Claim number","250619");
		
		
		return MyMCSNumber;
	
	}
	
	//created by Mugundhan

    public String updateCMS1500ClaimandSave (String myMCSNumber)

    {

        //String MyMCSNumber = "250619";

        String patientSigndate = dataMap.get("patientSigndate");

        String diagnosisCode = dataMap.get("diagnosisCode");

        //String providerID = dataMap.get("providerID");

        String serviceFromDate = dataMap.get("serviceFromDate");

        String serviceToDate = dataMap.get("serviceToDate");

        String POS = dataMap.get("POS");

        String procCode = dataMap.get("procCode");

        String serviceAmt = dataMap.get("serviceAmt");

        String daysUnits = dataMap.get("dayunits");

        String diagnosispointer = dataMap.get("diagnosispointer");

        String renderingtaxonomyCode = dataMap.get("renderingtaxonomyCode");

        String physiciansigndate = dataMap.get("physiciansigndate");

        String sitephno = dataMap.get("sitephno");

        String billingTaxonomyCode = dataMap.get("billingTaxonomyCode");

        click(filterButton, "Filter Button");

        waitUntilClickable(filtermyMCSClaimNO, 30);

        sendKeys(filtermyMCSClaimNO, "My MCS Claim # ", "250636");

        putStaticWait(2);

        click(filterSearchButton, "Filter Search");

        putStaticWait(2);

        click(expandFirstCMSRecord,"EXPAND CMS");

        putStaticWait(2);

        click(updateCMS,"Update CMS");

        putStaticWait(2);

        String updatepatsigndate=getAttribute(physicianSignDate, "value");

        System.out.println(updatepatsigndate);

        System.out.println(dataMap);

        System.out.println(patientSigndate);

        if(!updatepatsigndate.equals(patientSigndate))

        {

            sendKeys(patientSignDate, "Patient Sign Date", patientSigndate);    

        }

        String updatediagnosisCode=getAttribute(diagnosis1, "value");

        System.out.println(updatediagnosisCode);

        if(!diagnosisCode.contains(updatediagnosisCode))

        {

            sendKeys(diagnosis1, "diagnosis code", diagnosisCode);    

        }

        System.out.println("jk");

        waitUntilClickable(firstServiceLine, 120);

        click(firstServiceLine,"First service line");

        putStaticWait(2);

        click(lineModify,"service line Modify");

        putStaticWait(2);

        String updatefromdate=getAttribute(fromDate,"value");

        System.out.println(updatefromdate);

        if(!updatefromdate.equals(serviceFromDate))

        {

            sendKeys(fromDate, "From Date", serviceFromDate);    

        }

        String updatetodate=getAttribute(toDate,"value");

        if(!updatetodate.equals(serviceToDate))

        {

            sendKeys(toDate, "To Date", serviceToDate);    

        }

        String updatePOS=getText(modifyPOS);

        System.out.println(updatePOS);

        System.out.println(POS);

        if(!updatePOS.contains(POS))

        {
            waitUntilClickable(selectPOS, 120);

            click(selectPOS, "Place Of Service");

            sendKeys(inputPOS, "Place Of service", POS);

            putStaticWait(30);

            driver.findElement(By.xpath("//span[contains(text(),'"+POS+"')]")).click();

        }

        String updateProc=getAttribute(serviceCode,"value");
        
        if(!updateProc.equals(procCode))

        {
            sendKeys(serviceCode, "Service Code", procCode);
        }

        String updatecharges=getAttribute(charges,"value");



        if(!updatecharges.equals(serviceAmt))



        {

            charges.clear();

            sendKeys(charges, "Charges", String.valueOf(serviceAmt));    

        }

        String updateunit=getAttribute(daysPerUnits,"value");

        if(!updateunit.equals(daysUnits))

        {

            sendKeys(daysPerUnits, "Days/Units", String.valueOf(daysUnits));

        }

        String updatediagPointer=getAttribute(diagpointer,"value");

        if(!updatediagPointer.equals(diagnosispointer))

        {

        sendKeys(diagpointer, "Diagnosis Pointer", diagnosispointer);

        }

        String updatetaxonomy=getAttribute(renderingTaxonomy,"value");

        if(!updatetaxonomy.contains(renderingtaxonomyCode))

        {

        sendKeys(renderingTaxonomy, "Taxonomy code", renderingtaxonomyCode);

        waitForLoadingToDisappear();

         putStaticWait(2);

        WebElement renderingtaxonomy = driver.findElement(By.xpath("//span[contains(text(),'"+renderingtaxonomyCode+"')]"));

        click(renderingtaxonomy, renderingtaxonomyCode);

        }

        putStaticWait(2);

        click(saveService, "Save Service");

        String updatephySign = getAttribute(physicianSignDate,"value");

        if(!updatephySign.equals(physiciansigndate))

        {

        sendKeys(physicianSignDate, "Physician Sign Date", physiciansigndate);

        }

        String updatephoneno =getAttribute(sitePhoneNumber,"value");

        if(!updatephoneno.equals(sitephno))

        {

        sendKeys(sitePhoneNumber, "Site Phone Number", sitephno);

        }

        String updatebilltax =getAttribute(billingTaxonomy,"value");

        if(!updatebilltax.contains(billingTaxonomyCode))

        {

        sendKeys(billingTaxonomy, "Taxonomy code", billingTaxonomyCode);

        }

        click(saveCMS,"Save Claim");

        return myMCSNumber;

    }

  //created by Mugundhan

    public String updateCMS1500ClaimandSubmit (String myMCSNumber)

    {
    	
    	 click(filterButton, "Filter Button");

         waitUntilClickable(filtermyMCSClaimNO, 30);

         sendKeys(filtermyMCSClaimNO, "My MCS Claim # ", "250624");

         putStaticWait(2);

         click(filterSearchButton, "Filter Search");

         putStaticWait(2);

         click(expandFirstCMSRecord,"EXPAND CMS");

         putStaticWait(2);

         click(updateCMS,"Update CMS");

         putStaticWait(2);
         
     	click(submitClaim, "Submit button");
		return myMCSNumber;
    	
    }
}

