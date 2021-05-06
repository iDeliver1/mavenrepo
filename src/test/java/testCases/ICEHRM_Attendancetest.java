package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import basePage.TestBase;
import utils.General_Functions;

public class ICEHRM_Attendancetest extends TestBase {

	General_Functions objGen;
	
	
	@BeforeClass
	public void init() {
		objGen = new General_Functions(driver);
	}

	@Test(priority = 1)	
	public void loginTest() {
		checkObjMethod =	objGen.iceHrmLogin(prop.getProperty("username"),prop.getProperty("password"));
		reportingDescription("Login Validation", "User Must logged in", "User Successfullylogged in", "User unable to login");
		Assert.assertNotNull(checkObjMethod);
	}
	
	@Test(priority = 2,dependsOnMethods="loginTest")	
	public void punchInTest() {
		objGen.iceHrmHomepage("Attendance");
		checkBlnMethod = objGen.iceHrmAttendancePage("IN",0);
		reportingDescription("Punch System Validation", "User Must punch  in", "User Successfully punched  in", "User unable to punch");
		Assert.assertEquals(true, checkBlnMethod);
		
	}
	
	@Test(priority = 3,dependsOnMethods="punchInTest")	
	public void leaveTest() throws InterruptedException {
		objGen.iceHrmHomepage("Leave");
		objGen.iceHrmLeavePage("Causal Leave");
		reportingDescription("Leave Validation", "User Must able to apply for leave", "User Successfully apply for leave", "User unable to aplly for leave");
		
	}
	@Test(priority = 4,dependsOnMethods="leaveTest")	
	public void punchOutTest() {
		objGen.iceHrmHomepage("Attendance");
		checkBlnMethod = objGen.iceHrmAttendancePage("OUT",5);
		reportingDescription("Punch System Validation", "User Must punch out", "User Successfully punched  out", "User unable to punch");
		Assert.assertEquals(true, checkBlnMethod);
		
	}
	
	
	
}
