package orangeHRM.pageClasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import orangeHRM.commonMetods.CommonMethods;

public class LeavePage extends CommonMethods {
	
	WebDriver driver;
		
	public LeavePage(WebDriver driver)
	{
	    super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title']")
	WebElement applyLeaveHeader;
	
	@FindBy (xpath="//div[@class='oxd-select-text-input']")
	WebElement leaveType;
	
	@FindBy (xpath="//button[text()=' Apply ']")
	WebElement applyButton;
	
	@FindBy (xpath="//div[@loading='false']")
	WebElement leaveTypeValues;
	
	@FindBy (xpath="(//i[@class='oxd-icon bi-calendar oxd-date-input-icon'])[1]")
	WebElement fromDateIcon;
	
	@FindBy (xpath="(//i[@class='oxd-icon bi-calendar oxd-date-input-icon'])[2]")
	WebElement toDateIcon;
	
	
	@FindBy (css=".--today")
	WebElement fromSelectedDate;
	

	@FindBy (css=".--selected")
	WebElement toSelectedDate;
	
	@FindBy (xpath="//a[text()='My Leave']")
	WebElement myLeaveTab;
	
	@FindBy (xpath="//button[text()=' Search ']")
	WebElement leaveSearchButton;
	
	
	public String applyLeave(String leaveTypevalue) 
	{
		waitUntilVisibilityOf(applyLeaveHeader);
		leaveType.click();
		//Thread.sleep(1000);
		waitUntilVisibilityOf(leaveTypeValues);
		leaveTypeValues.click();
		fromDateIcon.click();
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-mm");
        LocalDate currentDate = LocalDate.now();
        LocalDate fromDate = currentDate.plusDays(3);
        System.out.println(fromDate);
		waitUntilVisibilityOf(fromSelectedDate);
	    WebElement leaveStartdate = driver.findElement(By.xpath("(//div[@class='oxd-calendar-date-wrapper'])//*[text()= '" + fromDate.getDayOfMonth() + "']"));
		System.out.println(leaveStartdate.getText());
	    leaveStartdate.click();
		
		toDateIcon.click();
		 LocalDate toDate = fromDate.plusDays(3);
	     System.out.println(toDate);
	     waitUntilVisibilityOf(fromSelectedDate);
	    WebElement leaveEnddate = driver.findElement(By.xpath("(//div[@class='oxd-calendar-dates-grid'])//div[text()= '" + toDate.getDayOfMonth() + "']"));
	    System.out.println(leaveEnddate.getText());
	
		leaveEnddate.click();
		applyButton.click();
	    waitForToastMsgToDisplay();
	
		String ToasterMessage = getToastMessage();
		
		return ToasterMessage;
			
		
	}
	
	public String cancelLeave() 
	{
		waitUntilVisibilityOf(myLeaveTab);
		myLeaveTab.click();
		waitUntilVisibilityOf(leaveSearchButton);
			
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        LocalDate currentDateFormat = LocalDate.now();
        //String currentDate = currentDateFormat.format(formatter);
        LocalDate fromDateFormat = currentDateFormat.plusDays(3);
        String fromDate = fromDateFormat.format(formatter);
        System.out.println(fromDate);

        LocalDate toDateFormat = fromDateFormat.plusDays(3);
        String toDate =toDateFormat.format(formatter);
        System.out.println(toDate);
		 String date = fromDate +" " +"to"+ " " +toDate;
		 System.out.println(date);
		 waitUntilPresenceOf(By.xpath("//div[contains(@class,'oxd-table-row')]//div[text()='"+ date +"']//ancestor::div[@role='row']"));
	
		 WebElement rowToDelete = driver.findElement(By.xpath("//div[contains(@class,'oxd-table-row')]//div[text()='"+ date +"']//ancestor::div[@role='row']"));
		
		// scrollDownByPixel();
		 scrollDownToWebelement(rowToDelete);
		
		
		WebElement cancelButton = rowToDelete.findElement(By.xpath(".//button[text()=' Cancel ']"));
		cancelButton.click();
		waitForToastMsgToDisplay();
			
		waitForToastMsgToDisappear();
		
		 //Thread.sleep(1000);
		 waitUntilPresenceOf(By.xpath("//div[contains(@class,'oxd-table-row')]//div[text()='"+ date +"']//ancestor::div[@role='row']//div[contains(text(), 'Cancelled')]"));	
		 String statusMessage= driver.findElement(By.xpath("//div[contains(@class,'oxd-table-row')]//div[text()='"+ date +"']//ancestor::div[@role='row']//div[contains(text(), 'Cancelled')]")).getText();
		 
		 
		System.out.println(statusMessage);
		return statusMessage;
	}
}
