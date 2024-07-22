package orangeHRM.pageClasses;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import orangeHRM.commonMetods.CommonMethods;

public class DashboardPage extends CommonMethods {
	
	WebDriver driver;

	public DashboardPage(WebDriver driver)
	
	{
	    super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy (xpath="//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
	WebElement dashboardTitle;
	
	@FindBy (xpath="//button[@title='Apply Leave']")
	WebElement applyLeave;
	
	@FindBy (xpath="//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title']")
	WebElement applyLeaveHeader;
	
	
	public LeavePage applyLeaveBuutonClick()
	{
		waitUntilVisibilityOf(applyLeave);
		applyLeave.click();
		waitUntilVisibilityOf(applyLeaveHeader);
		LeavePage leavePage = new LeavePage(driver);
		return leavePage;
		
	}
	

	@FindBy (xpath="//span[text()='Admin']")
	WebElement adminButton;
	
	@FindBy (xpath="//span[text()='Leave']")
	WebElement leaveButton;
	
	
	public AdminPage GoToAdmin()
	{
		waitUntilVisibilityOf(dashboardTitle);
		adminButton.click();
		AdminPage adminpage = new AdminPage(driver);
		return adminpage;
	}
	
	public LeavePage GoToLeavePage()
	{
		waitUntilVisibilityOf(dashboardTitle);
		leaveButton.click();
		LeavePage leavePage = new LeavePage(driver);
		return leavePage;
	}
}
