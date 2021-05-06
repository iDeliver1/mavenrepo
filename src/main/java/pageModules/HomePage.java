package pageModules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import basePage.PageBase;

public class HomePage extends PageBase {
	@FindBy(xpath = "//a[@id='atteandanceLink']")
	WebElement AttendanceTab;
	
	@FindBy(xpath = "//a[@class='logo']")
	public WebElement HomeBtn;
	
	@FindBy(xpath = "//a[@id='leaveLink']")
	public WebElement adminLeaveTab;
	
	@FindBy(xpath = "//a[@id='leavesLink']")
	WebElement LeaveTab;
	
	@FindBy(xpath = "//a[@id='travelLink']")
	WebElement TravelTab;
	
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	
	public Object clickOnTab(String TabName) {
		HomeBtn.click();
		if(TabName.equalsIgnoreCase("Attendance")) {
			AttendanceTab.click();
			return new AttendancePage(driver);
		}
		else if(TabName.equalsIgnoreCase("Leave")) {
			LeaveTab.click();
			 return new LeavePage(driver);
		}
		else if(TabName.equalsIgnoreCase("Admin Leave")) {
			adminLeaveTab.click();
			 return new AdminLeavePage(driver);
		}
		else if(TabName.equalsIgnoreCase("Admin Travel")) {
			TravelTab.click();
			 return new AdminTravelPage(driver);
		}
		else
			return null;
		
	}
}
