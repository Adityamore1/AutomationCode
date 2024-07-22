package orangeHRM.commonMetods;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {
	
	WebDriver driver;
	WebDriverWait wait;
	
	


	public CommonMethods(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy (id = "oxd-toaster_1")
	WebElement toaster;
	
	@FindBy (xpath = "//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']")
	WebElement toasterMessage;
	
	
	@FindBy (xpath = "//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title']")
	WebElement title;
	

	@FindBy (xpath = "//div[@class='oxd-select-dropdown --positon-bottom']")
	List<WebElement> userRoleDropdownValues;
	

	public void waitUntilVisibilityOf(By element)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	

	public void waitUntilVisibilityOf(WebElement element)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitUntilPresenceOf(By element)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}
	
	public void waitUntilVisibilityOf(List<WebElement> element)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public void waitUntilInvisibilityOf(WebElement element)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void scrollDownToWebelement(WebElement element)
	{
	   JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("arguments[0].scrollIntoView(true);", element);

	}
	
	
	public void scrollDownByPixel()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;

	    // Scroll down by 1000 pixels
	    js.executeScript("window.scrollBy(0,1000)");

	 }	

	public void javascriptClick(WebElement element) {
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	
	}
	
	public void waitForToastMsgToDisplay()
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toasterMessage));
	}
	
	public void waitForToastMsgToDisappear()
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(toasterMessage));
	}
	
	
	public void waitForEachPageTitleTpDisplay()
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(title));
	}
	
	public String getToastMessage()
	{
		String toasterMSG = toasterMessage.getText();
		System.out.println(toasterMSG);
		return toasterMSG;
	}
	
	public void selectValueFromDropdown(WebElement drpdownClick, String dropdownValue)
	{
		drpdownClick.click();

		for(WebElement userRoleDropdownValue:userRoleDropdownValues)
		{
			if(userRoleDropdownValue.getText().equalsIgnoreCase(dropdownValue));
			userRoleDropdownValue.click();
		}
	}
}
