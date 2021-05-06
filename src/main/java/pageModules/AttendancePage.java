package pageModules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import basePage.PageBase;
import utils.TestUtil;

public class AttendancePage extends PageBase {
	
	@FindBy(xpath = "//button[@id='punchButton']")
	WebElement PunchBtn;
	
	@FindBy(xpath = "//input[@id='time']")
	WebElement Time;
	
	@FindBy(xpath = "//textarea[@id='note']")
	WebElement Note;
	
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	WebElement Save;
	
	
	public AttendancePage(WebDriver driver) {
			super(driver);
	}

	
	public void punchSystem(String Status,int time) {
		PunchBtn.click();
		if(Status.contains("IN")) {
			Note.clear();
			Note.sendKeys(Status);
			Save.click();
		}else {
			Time.clear();
			Time.sendKeys(TestUtil.fTimestamp(time));
			Note.clear();
			Note.sendKeys(Status);
			Save.click();
		}
		
		
	}
	
	
	
	
	
}
