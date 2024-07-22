package orangeHRM.pageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import orangeHRM.commonMetods.CommonMethods;

public class AdminPage extends CommonMethods {
	
	WebDriver driver;
	
	public AdminPage(WebDriver driver)
	{
	    super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//button[text()=' Add ']")
	WebElement addButton;
	
	@FindBy (xpath = "(//div[@class='oxd-select-text-input'])[1]")
	WebElement userRoleDropdown;
	
	/*@FindBy (xpath = "//div[@class='oxd-select-dropdown --positon-bottom']")
	List<WebElement> userRoleDropdownValues;*/
	
	@FindBy (xpath = "(//div[@class='oxd-select-text-input'])[2]")
	WebElement statusDropdown;
	
	@FindBy (xpath = "//input[@placeholder='Type for hints...']")
	WebElement employeeNameTextbox;
	
	@FindBy (xpath = "//div[text()='Searching....']")
	WebElement employeeNameSearchMessage;
	
	
	@FindBy (xpath = "//div[@class='oxd-autocomplete-dropdown --positon-bottom']")
	List<WebElement> employeeNameDropdownvalues;
	
	@FindBy (xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
	WebElement userNameTextbox;
	
	@FindBy (xpath = "(//input[@type='password'])[1]")
	WebElement passwordTextbox;
	
	@FindBy (xpath = "(//input[@type='password'])[2]")
	WebElement confirmPasswordTextbox;
	
	@FindBy (xpath = "//button[@type='submit']")
	WebElement submitButton;

	@FindBy (xpath = "//div[@class='oxd-table']")
	WebElement adminUserTable;
		
	@FindBy (xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")
	WebElement popupDeleteButton;
	
	public String addUser(String userName, String Password) throws InterruptedException
	{
		waitUntilVisibilityOf(addButton);
		addButton.click();
		waitForEachPageTitleTpDisplay();
		
		selectValueFromDropdown(userRoleDropdown, "Admin");
		selectValueFromDropdown(statusDropdown, "Enabled");
		
	/*	userRoleDropdown.click();

		for(WebElement userRoleDropdownValue:userRoleDropdownValues)
		{
			if(userRoleDropdownValue.getText().equalsIgnoreCase("Admin"));
			userRoleDropdownValue.click();
		}
		
		statusDropdown.click();
		
		for(WebElement statusDropdownValue:userRoleDropdownValues)
		{
			if(statusDropdownValue.getText().equalsIgnoreCase("Enabled"));
			statusDropdownValue.click();
		}*/
		
		employeeNameTextbox.sendKeys(userName);
		waitUntilInvisibilityOf(employeeNameSearchMessage);
		waitUntilVisibilityOf(employeeNameDropdownvalues);
		
		
		for(WebElement employeeNameDropdownvalue:employeeNameDropdownvalues)
		{
			if(employeeNameDropdownvalue.getText().equalsIgnoreCase("manda akhil user"));
			employeeNameDropdownvalue.click();
		}
		
		userNameTextbox.sendKeys("UserName2");
		passwordTextbox.sendKeys(Password);
		confirmPasswordTextbox.sendKeys(Password);
		submitButton.click();
		waitForToastMsgToDisplay();
		String ToasterMessage = getToastMessage();
		//Thread.sleep(1000);
		return ToasterMessage;
	
	}
	
	
	public String deleteUser() throws InterruptedException  
	{
		
		waitUntilVisibilityOf(adminUserTable);
		//Thread.sleep(500);
		WebElement rowToDelete = driver.findElement(By.xpath("//div[contains(@class, 'oxd-table-body')]//div[contains(text(), 'UserName2')]//ancestor::div[@role='row']"));
		scrollDownToWebelement(rowToDelete);
		WebElement deleteButton = rowToDelete.findElement(By.xpath(".//button[contains(@class, 'oxd-icon-button')][i[contains(@class, 'bi-trash')]]"));
		javascriptClick(deleteButton);
		waitUntilVisibilityOf(popupDeleteButton);
		popupDeleteButton.click();
		waitForToastMsgToDisplay();
		String ToasterMessage = getToastMessage();
		
		return ToasterMessage;
	
		
	}
	}
	


