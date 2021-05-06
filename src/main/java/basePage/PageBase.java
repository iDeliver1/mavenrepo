package basePage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {

	private static  final int TIMEOUT = 10; //seconds
    private  static final int POLLING = 100; //milliseconds

    protected WebDriver driver;
    private WebDriverWait wait;


    public PageBase(WebDriver driver) {
    	 this.driver = driver;
         wait = new WebDriverWait(driver, TIMEOUT, POLLING);
         PageFactory.initElements(driver, this);
	}

	protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    protected void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitForTextToDisappear(By locator, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
    }
    
    protected void moveElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
    
    protected  boolean isClickable(WebElement webe){
    	try
    	{ 
    	WebDriverWait wait = new WebDriverWait(driver, 5);
    	wait.until(ExpectedConditions.elementToBeClickable(webe));
    	   return true;
    	 }
    	catch (Exception e)
    	{
    	return false;
    	 }
    } 
    
    public void waitDriver() {
    	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    }
}
