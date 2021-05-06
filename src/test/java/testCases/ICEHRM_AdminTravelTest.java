package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import basePage.TestBase;
import utils.General_Functions;

public class ICEHRM_AdminTravelTest extends TestBase {

	General_Functions objGen;
	
	@BeforeClass
	public void init() {
		objGen = new General_Functions(driver);
	}
	
	
	@Test(priority = 1)	
	public void loginTest() {
		System.out.println(prop.getProperty("adminUser"));
		checkObjMethod =	objGen.iceHrmLogin(prop.getProperty("adminUser"),prop.getProperty("password"));
		reportingDescription("Login Validation", "User Must logged in", "User Successfullylogged in", "User unable to login");
		Assert.assertNotNull(checkObjMethod);
	}
	
	
	@Test(priority = 2,dependsOnMethods="loginTest")	
	public void adminTravelPageTest() {
		objGen.iceHrmHomepage("Admin Travel");
		checkBlnMethod = objGen.empTravelActivity();
		reportingDescription("Travel System Validation", "Admin must perform action on Employee Travel list", "Admin Sccessfully performed action on Employee Travel list", "Unable to perfrom action");
		Assert.assertEquals(true, checkBlnMethod);
		
	}
	
	
	
	

}
