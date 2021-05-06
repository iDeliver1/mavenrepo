package basePage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Excel_Libraries;
import utils.Extent_Report;
import utils.TestUtil;
import utils.WebEventListener;

public class TestBase {

	protected WebDriver driver;
	public static String browserName = "chrome",url,desc,actual,passExp,failExp,errorReason;
	public String reportName; //need to change attribute name
	protected String userName,password,cause;
	public  Properties prop;
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static EventFiringWebDriver eDriver;
	public static WebEventListener eventListener;
	public Object checkObjMethod;
	public boolean checkBlnMethod;
	
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/"
					+ "config/config.properties");
		
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		url=prop.getProperty("url");
		this.userName = prop.getProperty("username");
		this.password=prop.getProperty("password");
		
	}
	
	
	
	@BeforeTest
    public void beforeSuite(ITestContext context) throws Throwable {
		
		String BrowserVersion = TestUtil.getBrowserVersion();	
		System.out.println("Browser Version- "+BrowserVersion);
		WebDriverManager.chromedriver().version(BrowserVersion).setup();
		driver = new ChromeDriver(); 
		eDriver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		eDriver.register(eventListener);
		driver = eDriver;
	
		try {
			Excel_Libraries.createExcel(getClass().getSimpleName());
			Extent_Report.createExcelFile(getClass().getSimpleName());
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	
		initateURL();
	}

	
	public void initateURL() throws Throwable{
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		}
	
	
	public void reportingDescription(String ReportDesc,String ReportingActual,String ReportingPassExp,String ReportingFailexp) {
		TestBase.desc=ReportDesc;
		TestBase.actual=ReportingActual;
		TestBase.passExp=ReportingPassExp;
		TestBase.failExp = ReportingFailexp;

	}

	
	public static void log(String e) {
		Logger.getLogger(TestBase.class.getName());
		log.info(e);
		Reporter.log(e);
	}
	
	@AfterMethod
	public void checkMethodStatus(ITestResult result) throws Throwable {

		if(result.getStatus()==ITestResult.SUCCESS) {
			
			Extent_Report.Report("Pass", desc, actual, passExp);
			String ResultRe = " Description "+desc+" Actual "+actual+" Expected "+passExp;
			log(ResultRe);
			
			
		}
		else if(result.getStatus()==ITestResult.FAILURE) {
			Extent_Report.Report("Fail", desc, actual, failExp);
			String ResultRe = " Description "+desc+" Actual "+actual+" Expected "+failExp +" due to "+cause;
			
			log.warning(ResultRe);
			
		}
	}
	
    @AfterTest
    public void afterSuite() {
        if(null != driver) {
            driver.close();
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }


}
