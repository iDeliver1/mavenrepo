package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import basePage.TestBase;



public class TestUtil extends TestBase {

	static String rootdir;
	public static long PAGE_LOAD_TIMEOUT = 40;
	public static long IMPLICIT_WAIT = 40;
	public static String Report_Folder_path = "C:\\Reporting";
	public static String brow;


	public static String fGetCurrentDate(int date)
	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, date);
		System.out.println(c.getTime());
	    SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd");  
	    String strDate = dateformat.format( c.getTime()); 
	    System.out.println(strDate);
	    return strDate;
	}
	
	

public static String GetCurrentDate()
{
	Date date = new Date();  
    SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");  
    String strDate = dateformat.format(date); 
    return strDate;
}

		
	//-------------------------------------------TimeStamp Function----------------------------------	
		public static String fTimestamp(int hour)
			{
				Calendar cal = Calendar.getInstance(); 
				cal.setTime(new Date());              
				cal.add(Calendar.HOUR_OF_DAY, hour);  
				System.out.println(cal.getTime());
			    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
				String time = dateFormat.format(cal.getTime());
				System.out.println(time);
				return time;
			}
			 
	//-----------------------------------------------Screenshot Function-------------------------------	
		public static String Timestamp()
		{
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss");
			String time = dateFormat.format(now);
			return time.replace("-", "");
		}
		   
			
						
			//-------------------Function for determining Chrome Browser Version---------------------
			public static String getBrowserVersion() throws IOException {
				
				try {
					
					Runtime rt = Runtime.getRuntime();
				    try {
				    	rootdir = System.getProperty("user.dir");
				     
				    	rt.exec("cmd  /K \"dir /B/AD \"C:/Program Files (x86)/Google/Chrome/Application/\"|findstr /R /C:\"^[0-9].*\\..*[0-9]$\" > "+ rootdir +"\\version.txt\"");
				    	//C:\Program Files (x86)\Google\Chrome\Application
				       brow = getversion();
				    } catch (IOException e) {
				        e.printStackTrace();
				    }
				return brow.substring(0, brow.length() - 4);
			}
			catch(Exception e)
			{
			brow = e.toString();
				return brow;
			}
			
			}
			
			//--------------------Return Stored value of  Chrome Browser Version----------------------------
			public static String getversion() {
				 String data = "";
				try {
				  File myObj = new File(rootdir+"/version.txt"); //File myObj = new File("E:/version.txt");
			      Scanner myReader = new Scanner(myObj);
			      while (myReader.hasNextLine()) {
			         data = myReader.nextLine();
			        System.out.println(data);
			        break;
			        
			      }
			      myReader.close();
			      return data;
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			  
				return null;
			}
			
		
			
			
			//------------------Function for move able object------------------- 
			public void MoveElement(WebElement element) {
				Actions action = new Actions(getDriver());
				action.moveToElement(element).build().perform();
			}
			
			
			//---------------------function for Select size --------------------
			public static void SelectLeave_type(List<WebElement> element,String LeaveFormat) {
				for(int i=0;i<element.size();i++) {
					if(element.get(i).getAttribute("Title").contains(LeaveFormat)) {
						element.get(i).click();
						break;
					}
				}	
				
			}
			
			public static void SelectItem(WebElement element,int LeaveFormat) {
				Select  Leave = new Select(element);
				Leave.selectByIndex(LeaveFormat);
			}

}
