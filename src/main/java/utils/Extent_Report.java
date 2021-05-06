package utils;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Extent_Report {
	 static ExtentTest logger,child_logger,parent_logger;
	  private static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	  private static ThreadLocal<ExtentReports>  extent1 =new ThreadLocal<ExtentReports>();
	  ExtentHtmlReporter htmlReporter;
	  static ExtentReports extent;
	  static String Report_Folder_path = "C:\\Reporting\\Report"+TestUtil.Timestamp(),Reportname;
	  static int Stepnumber=1,i=1,functioncall=1,j=1;
	  
	  
	    public static void createExcelFile(String Step_details)   {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Report_Folder_path + "\\testReport.html");
	    extent = new ExtentReports();
	    extent.attachReporter(htmlReporter);
	    extent.setSystemInfo("OS", "OS");
	    extent.setSystemInfo("Browser", "browser");
	
	    htmlReporter.config().setDocumentTitle("Extent Report Demo");
	    htmlReporter.config().setReportName("Test Report");
	 
	    htmlReporter.config().setTheme(Theme.STANDARD);
	    CreateRoportname(Step_details);
	}


	  public synchronized ExtentReports getInstance() {	
		  	return extent1.get();
	  }

	 
	  public static  ExtentTest CreateRoportname(String step_details){
		  logger =extent.createTest(step_details);
		  child_logger=logger.createNode("System Name - "+System.getenv("COMPUTERNAME"));
		  functioncall=1;
		  j=1;
		  extentTest.set(child_logger);
			return extentTest.get();
			
	  }
	  
	  
	

	  public synchronized  ExtentTest getTest() {
	      return extentTest.get();
	  }
	  
	  public static  void Report(String Status1,String Description,String ActualStep,String ExpectedStep) throws Throwable{
			
		  
		  
		  String ReportStatus = "<b>Step Number "+functioncall+"<br>Description :</b> "+Description+"<br><b>Expected :</b> "+ExpectedStep+"<br><b>Actual :</b> "+ActualStep;
			//System.out.println(getTest());
			Excel_Libraries.fExcelReporter(Description, ActualStep, ExpectedStep, Status1, TestUtil.GetCurrentDate());
			try{
			if(Status1.equalsIgnoreCase("PASS")){	
				extentTest.get().log(Status.PASS, ReportStatus);
				flush();
			}
			else{
				extentTest.get().log(Status.FAIL, ReportStatus);
				flush();
				
			}
			
			}catch(Exception e){
				System.out.println(e);
			}
			functioncall=functioncall+1;
			
		}
	  

	public  String getMultiProductValue(List<WebElement> element,WebElement prtx) {
		   float value = 0;
		 
		   System.out.println(extent1.get());
			System.out.println(extentTest.get());
		   try {
			   
			   for(int i =0;i<element.size();i++) {
				   String a = element.get(i).getText();
				  value = value +Float.parseFloat(a.replace("$", ""));
			   }
			   value = value+Float.parseFloat(prtx.getText().replace("$", ""));
			   
			   return String.format("%.02f", value);
			   
		   }catch(Exception e) {
			   e.printStackTrace();
			 
		   }   
		return null;		
}
	
	@AfterTest
	public static void flush(){
		extent.flush();
	}


	
	
	
}
