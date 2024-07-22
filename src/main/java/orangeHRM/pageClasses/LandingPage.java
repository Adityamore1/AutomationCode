package orangeHRM.pageClasses;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import orangeHRM.commonMetods.CommonMethods;

public class LandingPage extends CommonMethods {

	WebDriver driver;
	
	public LandingPage(WebDriver driver) 
	{	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy (xpath = "//input[@name='username']")
	WebElement userName;
	
	@FindBy (xpath = "//input[@name='password']")
	WebElement password;
	
	@FindBy (xpath = "//button[@type='submit']")
	WebElement submit;
	
	@FindBy (xpath="//span[@class='oxd-userdropdown-tab']")
	WebElement userProfile;
	
	@FindBy (xpath="//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
	WebElement dashboardTitle;
	
	@FindBy (xpath="(//a[@class='oxd-userdropdown-link'])[4]")
	WebElement logOut;
	
	By loginTitle = By.xpath("//h5[@class='oxd-text oxd-text--h5 orangehrm-login-title']");
	
	
	public void goTo(String url) 
	{
		driver.get(url);
		waitUntilVisibilityOf(loginTitle);
	}
	
	public DashboardPage login(String UN, String PW)
	{
		waitUntilVisibilityOf(loginTitle);
		userName.sendKeys(UN);
		password.sendKeys(PW);
		submit.click();
		waitUntilVisibilityOf(userProfile);
		DashboardPage dashboard = new DashboardPage(driver);
		return dashboard;
	}
	
	public void logOut()
	{
		userProfile.click();
		logOut.click();
		waitUntilVisibilityOf(loginTitle);
		
	}
	
	
	}
