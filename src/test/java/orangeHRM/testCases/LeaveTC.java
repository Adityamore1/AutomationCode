package orangeHRM.testCases;



import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import orangeHRM.baseClass.BaseClass;
import orangeHRM.pageClasses.DashboardPage;
import orangeHRM.pageClasses.LandingPage;
import orangeHRM.pageClasses.LeavePage;

public class LeaveTC extends BaseClass {
	
	@Test(enabled = true)
	public void applyLeaveTC()
	{

		
		try 
		{

			//LandingPage landingPage = launchApplication(getProperties("url"));
			DashboardPage dashboard = landingPage.login("Admin", "admin123");
			LeavePage leavePage = dashboard.applyLeaveBuutonClick();
			
	        String ToasterMessage = leavePage.applyLeave("CAN - FMLA");
	        Assert.assertEquals(ToasterMessage, "Successfully Saved");
			
		
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test(dependsOnMethods = "applyLeaveTC")
	public void cancelLeaveTC()
	{
		try 
		{
		//LandingPage landingPage = launchApplication(getProperties("url"));
		
		
		DashboardPage dashboard = landingPage.login("Admin", "admin123");
		LeavePage leavePage = dashboard.GoToLeavePage();
		
		//Thread.sleep(2000);
		
		String statusMessage = leavePage.cancelLeave();
		if(statusMessage.startsWith("Cancelled"))
		{
			Assert.assertTrue(true);
		}
		
		else 
		{
			Assert.assertTrue(false);

		}
		
	    }
		catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
	

}
