package orangeHRM.testCases;

import java.io.IOException;
import org.testng.annotations.Test;

import orangeHRM.baseClass.BaseClass;
import orangeHRM.pageClasses.LandingPage;


public class LoginTC extends BaseClass {
	
	
	@Test(enabled = true, priority=0)
	public void loginApplication() 
	{
		 
	  try 
	     {
		  
		  landingPage.login("Admin", "admin123");
		  //Thread.sleep(500);
		 }
	  catch (Exception e) 
	     {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	  }

	@Test (priority=1)
	public void logOutApplication() 
	{

	  try 
		 {
		
		  landingPage.login("Admin", "admin123");
		  
		  landingPage.logOut();	
		//Thread.sleep(500);
		  } 
	  catch (Exception e) 
		 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}
}
