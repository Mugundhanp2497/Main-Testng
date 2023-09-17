package org.alphind.alphamcs.pages;

import org.alphind.alphamcs.base.CommonFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MCOPatientEnrollmentPage extends CommonFunctions {

	WebDriver driver;
	static String patientLastName;
	static String patientFirstName;

	private static final Logger log = LogManager.getLogger(MCOPatientEnrollmentPage.class);

	@FindBy(xpath = "//*[text()='All Enrollments']")
	WebElement allEnrollments;

	@FindBy(xpath = "//*[text()='My Enrollments']")
	WebElement myEnrollments;

	@FindBy(xpath = "//*[text()='Create']")
	WebElement createButton;

	// patient Search elements
	@FindBy(xpath = "//*[@ng-reflect-name='Pat_ln']")
	WebElement patientSearchLastName;

	@FindBy(xpath = "//*[@ng-reflect-name='Pat_fn']")
	WebElement patientSearchFirstName;

	@FindBy(xpath = "//*[@ng-reflect-name='Pat_dob']")
	WebElement patientSearchDOB;

	@FindBy(xpath = "//*[text()='Create New Enrollment']")
	WebElement createNewEnrollmentButton;

	@FindBy(xpath = "(//*[text()='Search'])[2]")
	WebElement patientSearchButton;

	// patient enrollments Patient info

	@FindBy(xpath = "//*[@ng-reflect-name='admsn_dt']")
	WebElement patientAdmissionDate;

	@FindBy(xpath = "//*[@ng-reflect-name='cons_ph_no']")
	WebElement patientPhone;

	@FindBy(xpath = "//*[@ng-reflect-name='gndr']")
	WebElement patientGender;

	@FindBy(xpath = "//*[text()='No SSN']/preceding::span[@class='mat-checkbox-inner-container'][1]")
	WebElement patientNoSSNCheckBox;

	@FindBy(xpath = "//*[@ng-reflect-name='EngProf']")
	WebElement patientEnglishProf;

	@FindBy(xpath = "//*[@ng-reflect-name='EmpStatus']")
	WebElement patientEmployment;

	@FindBy(xpath = "//*[@ng-reflect-name='Education']")
	WebElement patientEducation;

	// patient enrollment address info

	@FindBy(xpath = "//*[@ng-reflect-name='cons_phyaddr']")
	WebElement patientPhysicalAddress;

	@FindBy(xpath = "//*[@ng-reflect-name='cons_phystate']")
	WebElement patientPhysicalState;

	@FindBy(xpath = "//*[@ng-reflect-name='cons_phycit']")
	WebElement patientPhysicalCity;

	@FindBy(xpath = "//*[@ng-reflect-name='cons_phyzip']")
	WebElement patientPhysicalZip;

	@FindBy(xpath = "//*[@ng-reflect-name='cons_phycountyName']")
	WebElement patientPhysicalCountyName;

	@FindBy(xpath = "//*[@ng-reflect-name='srvc_reqr_nm']")
	WebElement patientServiceRequestorName;

	// Patient other info

	@FindBy(xpath = "//*[@ng-reflect-name='num_in_household']")
	WebElement patientNumHousehold;

	@FindBy(xpath = "//*[@ng-reflect-name='hh_ic']")
	WebElement patientAnnualIncome;

	@FindBy(xpath = "//*[text()='Other Sig. Person ']//following::span[@class='mat-radio-outer-circle'][1]")
	WebElement patientInActiveServiceNA;

	@FindBy(xpath = "//*[@ng-reflect-name='Mutual_Aid_Prog']")
	WebElement patientMutualAidprogram;

	@FindBy(xpath = "//*[@ng-reflect-name='Traumatic_Brain_Injury']")
	WebElement patientTraumaticBrainInjury;

	@FindBy(xpath = "//*[@ng-reflect-name='Health_Medical_Insurance']")
	WebElement patientHealthinsurance;

	@FindBy(xpath = "//*[@ng-reflect-name='Opiod_Replacement_Therapy']")
	WebElement patientOpioidRepTherapy;

	@FindBy(xpath = "//*[@ng-reflect-name='P3prob_desc']")
	WebElement patientProblemDesc;

	@FindBy(xpath = "//*[text()='First ']//following::span[text()=' CSA '][1]")
	WebElement patientProblemFirstCSA;

	@FindBy(xpath = "//*[text()='Second ']//following::span[text()=' None '][1]")
	WebElement patientProblemSecondNone;

	@FindBy(xpath = "//*[text()='Third ']//following::span[text()=' None '][1]")
	WebElement patientProblemThirdNone;

	@FindBy(xpath = "//*[contains(text(),'Instability')]//following::span[@class='mat-radio-outer-circle'][5]")
	WebElement patientRiskInstabilityNotScreened;

	@FindBy(xpath = "//*[contains(text(),'Safety Issues')]//following::span[@class='mat-radio-outer-circle'][5]")
	WebElement patientRiskSafetyIssuesNotScreened;

	@FindBy(xpath = "//*[contains(text(),'Aggression')]//following::span[@class='mat-radio-outer-circle'][5]")
	WebElement patientRiskAggressionNotScreened;

	@FindBy(xpath = "//*[contains(text(),'Risk To Self')]//following::span[@class='mat-radio-outer-circle'][5]")
	WebElement patientRiskToSelfNotScreened;

	@FindBy(xpath = "//*[contains(text(),'Risk To Others')]//following::span[@class='mat-radio-outer-circle'][5]")
	WebElement patientRiskToOthersNotScreened;

	@FindBy(xpath = "//*[text()='Recommended Initial Services ']/following::span[@class='mat-checkbox-inner-container'][1]")
	WebElement patientServiceDiagnosis;

	@FindBy(xpath = "//*[@ng-reflect-name='Accomm_Spec_Cons_Needs']")
	WebElement patientSpecialNeeds;

	@FindBy(xpath = "//*[@ng-reflect-name='P3prim_care_med_prv']")
	WebElement patientPCProvider;

	@FindBy(xpath = "//*[@ng-reflect-name='P3spl_argmt']//descendant::span[@class='mat-radio-outer-circle'][4]")
	WebElement patientSpecialArrangementNA;

	@FindBy(xpath = "//*[@ng-reflect-name='P3qp_lname']")
	WebElement patientQPLastname;

	@FindBy(xpath = "//*[@ng-reflect-name='P3qp_fname']")
	WebElement patientQPFirstName;

	@FindBy(xpath = "//*[text()='Staff Qualifications']/following::span[@class='mat-checkbox-inner-container'][1]")
	WebElement patientStaffQualificationMH;

	@FindBy(xpath = "//*[@ng-reflect-name='P3scrn_end_time_hr']")
	WebElement patientQApptEndtimeHr;

	@FindBy(xpath = "//*[@ng-reflect-name='P3scrn_end_time_min']")
	WebElement patientQApptEndtimeMin;

	@FindBy(xpath = "//*[@ng-reflect-name='P3statid']//descendant::span[text()=' Not Reviewed ']")
	WebElement patientEnrollmentActionNotReviewed;

	@FindBy(xpath = "//*[@ng-reflect-name='Compl_staff_phone']")
	WebElement patientStaffphone;

	@FindBy(xpath = "//*[text()='Saved Successfully']")
	WebElement savedSuccessfullyMessage;

	@FindBy(xpath = "//*[text()='Save']")
	WebElement patientSaveButton;

	@FindBy(xpath = "//*[text()='Submit']")
	WebElement patientSubmitButton;

	@FindBy(xpath = "//*[text()='YES, Submit']")
	WebElement patientSubmitConfirmationButton;

	@FindBy(xpath = "//*[text()='Cancel']")
	WebElement patientCancleButton;

	@FindBy(xpath = "//*[text()='Close']")
	WebElement patientCloseButton;

	@FindBy(xpath = "//*[text()='Go To Clinical Page']")
	WebElement patientGoToClinicalPageButton;

	@FindBy(xpath = "//*[text()='Stay on STR Page']")
	WebElement patientStayOnSTRPageButton;

	// Clinical Page Info

	@FindBy(xpath = "(//*[text()='Add '])[1]")
	WebElement patientDiagnosisAddButton;

	@FindBy(xpath = "(//*[text()='Add '])[2]")
	WebElement patientBenefitPlanAddButton;

	@FindBy(xpath = "//*[text()=' Drug of Choice ']/descendant::span[@class='mat-checkbox-inner-container'][1]")
	WebElement patientDrugOfChoiceNA;

	@FindBy(xpath = "//*[text()=' COBs ']/descendant::span[@class='mat-checkbox-inner-container'][1]")
	WebElement patientCOBsNA;

	@FindBy(xpath = "//*[text()=' Medications ']/descendant::span[@class='mat-checkbox-inner-container'][1]")
	WebElement patientMedicationsNA;

	@FindBy(xpath = "//*[@ng-reflect-name='diagnosis']")
	WebElement patientDiagnosis;

	@FindBy(xpath = "//*[@ng-reflect-name='class']")
	WebElement patientClass;

	@FindBy(xpath = "//*[@ng-reflect-name='effectiveDate']")
	WebElement patientEffectiveDate;

	@FindBy(xpath = "//*[@ng-reflect-name='endDate']")
	WebElement patientEndDate;

	@FindBy(xpath = "//*[text()=' Cancel ']")
	WebElement patientDiagnosisCancelButton;

	@FindBy(xpath = "//*[text()=' Cancel ']")
	WebElement clinicalInfoCloseButton;

	@FindBy(xpath = "//*[text()=' Save ']")
	WebElement patientDiagnosisSaveButton;

	@FindBy(xpath = "//*[@ng-reflect-name='ncTracksBenefitPlan']")
	WebElement patientBenefitplan;

	////////////////// Implementations

	public MCOPatientEnrollmentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void searchPatient(String ln, String fn, String dob) {
		waitUntilClickable(patientSearchLastName, 30);
		sendKeys(patientSearchLastName, "Patient Search Last Name", ln);
		sendKeys(patientSearchFirstName, "Patient Search First Name", fn);
		sendKeys(patientSearchDOB, "Patient Search Date of Birth", dob);
		click(patientSearchButton, "Patient Search Button");
	}

	public String createEnrollment() {
		waitUntilClickable(createButton, 30);
		putStaticWait(2);
		click(createButton, "Create button");
		patientLastName = getRandomLastName();
		patientFirstName = getRandomFirstName();
		searchPatient(patientLastName, patientFirstName, dataMap.get("patientDOB"));
		click(createNewEnrollmentButton, "Create New Enrollment Button");
		waitUntilClickable(patientAdmissionDate, 30);
		String currentDate = getCurrentDate();
		sendKeys(patientAdmissionDate, "Admission Date", currentDate);
		sendKeys(patientPhone, "Consumer Phone", dataMap.get("consumerPhone"));
		selectDropDown(patientGender, "Patient Gender", " Male ");
		clickCheckbox(patientNoSSNCheckBox, "No SSN");
		selectDropDown(patientEnglishProf, "English Proficiency", " Yes ");
		selectDropDown(patientEmployment, "Employment", " Employed Full Time ");
		selectDropDown(patientEducation, "Education", " Baccalaureate degree ");

		sendKeys(patientPhysicalAddress, "Physical Address", "10681 Tigerton Ln");
		selectState("NC");
		sendKeys(patientPhysicalCity, "Physical City", "Charlotte");
		sendKeys(patientPhysicalZip, "Physical Zip", "28269");
		selectCounty("Mecklenburg");

		sendKeys(patientServiceRequestorName, "Service Requestor Name", "Test ABC");
		sendKeys(patientNumHousehold, "In Household", "3");
		sendKeys(patientAnnualIncome, "Household Annual Income", "90000");

		selectDropDown(patientMutualAidprogram, "Mutual Aid Pragram", " 01 No attendance in mo. prior to admission ");
		selectDropDown(patientTraumaticBrainInjury, "Traumatic Brain Injury", " No ");
		selectDropDown(patientHealthinsurance, "Primary Medical Insurance", " Private ");
		selectDropDown(patientOpioidRepTherapy, "Opioid Replacement Therapy", " No ");
		sendKeys(patientProblemDesc, "Problem Description", "Test");

		click(patientProblemFirstCSA, "Presenting Problem First - CSA");
		click(patientProblemSecondNone, "Presenting Problem Second - None");
		click(patientProblemThirdNone, "Presenting Problem Third - None");
//		click(patientRiskInstabilityNotScreened, "Risk Instability - Not Screened");
//		click(patientRiskSafetyIssuesNotScreened, "Risk Safety Issue - Not Screened");
//		click(patientRiskAggressionNotScreened, "Risk Aggression - Not Screened");
//		click(patientRiskToSelfNotScreened, "Risk To Self - Not Screened");
//		click(patientRiskToOthersNotScreened, "Risk To Others - Not Screened");

		clickCheckbox(patientServiceDiagnosis, "Initial Services - Diagnosis");
		selectDropDown(patientSpecialNeeds, "Special Needs", "No");
		sendKeys(patientPCProvider, "Primary Care Provider", "Test XYZ");
//		click(patientSpecialArrangementNA, "Special Arrangements - NA");
		sendKeys(patientQPLastname, "QP Last Name", "TestL");
		sendKeys(patientQPFirstName, "QP First Name", "TestF");

		clickCheckbox(patientStaffQualificationMH, "Staff Qualifications - MH");
		selectDropDown(patientQApptEndtimeHr, "Appointment End Time", " 20 ");
		selectDropDown(patientQApptEndtimeMin, "Appointment End Time", " 00 ");
		click(patientEnrollmentActionNotReviewed, "Enrollment Action - Not Reviewed");
		sendKeys(patientStaffphone, "Completing Staff Phone", "704-555-5555");

		click(patientSaveButton, "Save Button");
		waitUntilClickable(patientGoToClinicalPageButton, 20);
		log.info(savedSuccessfullyMessage.getText().toString());
		click(patientGoToClinicalPageButton, "Go To Clinical Page");
		addDiagnosis("Amphetamine Delusion Disorder");
		putStaticWait(3);
		addBenefitPlans("Adult with Developmental Disability");
		putStaticWait(3);
		driver.findElement(By.xpath("//*[text()=' Close ']")).click();

		click(patientSubmitButton, "Submit Button");
		click(patientSubmitConfirmationButton, "Submit Confirmation Button");
		putStaticWait(2);
		click(allEnrollments, "All Enrollments Button");
		putStaticWait(3);
		return getPatientIdFirstRowEnrollments();
	}

	private String getPatientIdFirstRowEnrollments() {
		return patientIdFirstRowEnrollments.getText();
	}

	private void selectState(String state) {
		sendKeys(patientPhysicalState, "Physical State", state);
		driver.findElement(By.xpath("//*[@class='mat-option-text']")).click();
	}

	private void selectCounty(String county) {
		putStaticWait(3);
		patientPhysicalCountyName.click();
		// sendKeys(patientPhysicalCountyName, "Physical County", county);
		driver.findElement(By.xpath("//*[@class='mat-option-text']")).click();
	}

	private void addDiagnosis(String diagnosis) {
		waitUntilClickable(patientDiagnosisAddButton, 20);
		click(patientDiagnosisAddButton, "Add Diagnosis Button");
		putStaticWait(1);
		sendKeys(patientDiagnosis, "Diagnosis", diagnosis);
		putStaticWait(1);
		driver.findElement(By.xpath("//*[@class='mat-option-text' and contains(text(),'" + diagnosis + "')]")).click();
		selectDropDown(patientClass, "Diagnosis Class", "Principal ");
		sendKeys(patientEffectiveDate, "Effective Date", getCurrentDate());
		click(patientDiagnosisSaveButton, "Save Diagnosis Button");
	}

	private void addBenefitPlans(String benefitPlan) {
		waitUntilClickable(patientBenefitPlanAddButton, 20);
		click(patientBenefitPlanAddButton, "Add Benefit Plan Button");
		putStaticWait(1);
		sendKeys(patientBenefitplan, "Benefit Plan", benefitPlan);
		putStaticWait(1);
//		driver.findElement(By.xpath("//*[@class='mat-option-text' and contains(text(),'" + benefitPlan + "')]"))
//				.click();
		sendKeys(patientEffectiveDate, "Effective Date", getCurrentDate());
		sendKeys(patientEndDate, "End Date", getFutureDate());
		click(patientDiagnosisSaveButton, "Save Benefit Plan Button");
	}

	@FindBy(xpath = "//*[text()='Filter']")
	WebElement filterButton;

	@FindBy(xpath = "//*[@ng-reflect-name='Filter_patId']")
	WebElement patientIdTextBox;

	@FindBy(xpath = "//*[text()='Search']")
	WebElement searchBtnEnrollFilterPg;

	@FindBy(xpath = "(//*[text()=' Enrollment ID ']//following::tr)[1]")
	WebElement firstRowEnrollments;

	@FindBy(xpath = "(//*[text()=' Enrollment ID ']//following::tr)[1]/td[3]")
	WebElement patientIdFirstRowEnrollments;

	public void filterByPatientId(String patientId) {
		waitUntilClickable(filterButton, 20);
		click(filterButton, "Filter Button");
		waitUntilClickable(patientIdTextBox, 20);
		sendKeys(patientIdTextBox, "Patient ID field", patientId);
		click(searchBtnEnrollFilterPg, "Search button in Enrollments Filter page");
		putStaticWait(1);
		click(firstRowEnrollments, "First row of filtered result");
		putStaticWait(10);
	}
}
