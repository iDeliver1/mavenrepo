package pageModules;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import basePage.PageBase;
import utils.TestUtil;


public class LeavePage extends PageBase {

	@FindBy(xpath = "//button[contains(text(),'Apply Leave')]")
	WebElement ApplyLeavebtn;
	
	@FindBy(xpath = "//select[@id='leave_type']")
	 WebElement SelectLeave;
	
	
	@FindBy(xpath = "//input[@id='date_start']")
	WebElement StartDate;
	
	
	@FindBy(xpath = "//input[@id='date_end']")
	WebElement EndDate;
	
	
	@FindBy(xpath = "//textarea[@id='details']")
	WebElement LeaveDetails;

	
	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	WebElement Continuebtn;
	
	@FindBy(xpath = "//tbody[@id='leave_days_table_body']//child::td")
	WebElement CheckLeaveDate;
	
	
	@FindBy(xpath = "//select[@class='days']")
	WebElement LeaveFormat;
	
	@FindBy(xpath = "//div[contains(@class,'controls')]//button[contains(@class,'btn')][contains(text(),'Apply')]")
	WebElement Applybtn;
	

	@FindBy(xpath = "//p[@id='messageModelBody']")
	WebElement Msgbody;
	
	@FindBy(xpath = "//button[contains(text(),'Ok')]")
	WebElement Okbtn;

	public LeavePage(WebDriver driver) {
		super(driver);
	}
	
	
	public void applyForLeave(String Leave) throws InterruptedException {
		Thread.sleep(5000);
		waitForElementToAppear(ApplyLeavebtn);
		ApplyLeavebtn.click();

		try {
			waitForElementToAppear(SelectLeave);
			SelectLeave.click();
			TestUtil.SelectItem(SelectLeave, 2);
		}catch(Exception e  ) {
			driver.get(driver.getCurrentUrl());
			waitDriver();
			ApplyLeavebtn.click();
			
			TestUtil.SelectItem(SelectLeave, 2);
		}
		
		StartDate.sendKeys(TestUtil.fGetCurrentDate(0));
		EndDate.sendKeys(TestUtil.fGetCurrentDate(1));
		Continuebtn.click();
		TestUtil.SelectItem(LeaveFormat, 1);
		
		Applybtn.click();
		
		waitForElementToAppear(Msgbody);
		Msgbody.click();
		Okbtn.click();
	}

}
