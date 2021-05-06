package pageModules;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import basePage.PageBase;

public class AdminTravelPage extends PageBase {
	
	@FindBy(xpath = "//*[@id=\"EmployeeTravelRecord\"]/div[2]/div/table/tbody/tr")
	public List <WebElement> empTravelTable;
	@FindBy(xpath = "//*[@id=\"EmployeeTravelRecord\"]/div[2]/div/table/tbody/tr/td")
	public WebElement empNoTravelTable;
	
	@FindBy(xpath = "//*[@id=\"EmployeeTravelRecord\"]/div[2]/div/table/tbody/tr/td/div/img")
	public List <WebElement> empTravelTableActionButton;
	
	@FindBy(xpath = "//select[@id='travelrequest_status']")
	public WebElement empTravelStatus;
	
	@FindBy(xpath = "//textarea[@id='travelrequest_reason']")
	public WebElement empTravelNote;
	
	@FindBy(xpath = "//button[contains(text(),'Change TravelRequest Status')]")
	public WebElement empTravelButton;
	
	@FindBy(xpath = "//button[contains(text(),'Ok')]")
	public WebElement okBtn;

	int iTravelTableCount,iIterate,iAction= 2;
	
	public AdminTravelPage( WebDriver driver) {
		super(driver);
	}
	
	public boolean empTravelAction() {
		
		try {
			
			if( !empNoTravelTable.getText().equalsIgnoreCase("No data available in table")) {
				iTravelTableCount = empTravelTable.size();
				
				for(iIterate=0;iIterate<iTravelTableCount;iIterate++) {
					
					empTravelTableActionButton.get(iAction).click();
					Select leaveStatus =new Select(empTravelStatus);
					leaveStatus.selectByIndex(0);
					empTravelNote.sendKeys("Ok");
					empTravelButton.click();
					okBtn.click();
				
					iAction=iAction+4;
				}
				return true;
			}else {
				
				System.out.println("No Table");
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		
	}

}
