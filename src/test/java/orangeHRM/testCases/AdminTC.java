package orangeHRM.testCases;




import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import orangeHRM.baseClass.BaseClass;
import orangeHRM.baseClass.Retry;
import orangeHRM.pageClasses.AdminPage;
import orangeHRM.pageClasses.DashboardPage;
import orangeHRM.pageClasses.LandingPage;

public class AdminTC extends BaseClass{
	
	
	
	@Test(enabled = true, retryAnalyzer = Retry.class)
	public void addAdminUser() throws InterruptedException
	{
		
		try 
		{
			
		//LandingPage landingPage = launchApplication(getProperties("url"));
		DashboardPage dashboard = landingPage.login("Admin", "admin123");
		AdminPage adminpage = dashboard.GoToAdmin();
		String successMessage = adminpage.addUser("user", getProperties("password"));
		if(successMessage.startsWith("Successfully"))
		{
		  Assert.assertTrue(true);
		}
		
		else 
		{
			Assert.assertTrue(false);

		//Assert.assertEquals(successMessage, "Successfully Saved");
		
		} 
    	}
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test(dependsOnMethods = "addAdminUser")
		public void deleteAdminUser() throws InterruptedException
		{
			 
			try
			{
			//LandingPage landingPage = launchApplication(getProperties("url"));
			DashboardPage dashboard = landingPage.login("Admin", "admin123");
			AdminPage adminpage = dashboard.GoToAdmin();
			String deleteMessage = adminpage.deleteUser();
		
			Assert.assertEquals(deleteMessage, "Successfully Deleted");
			
			} 
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

