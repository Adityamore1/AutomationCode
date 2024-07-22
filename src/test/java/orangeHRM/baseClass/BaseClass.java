package orangeHRM.baseClass;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import orangeHRM.pageClasses.LandingPage;

public class BaseClass  
{
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public String getProperties(String fieldName) throws IOException 
	{
		
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream("C:/Users/aditya.more/eclipse-workspace/OrangeHRM/src/main/java/orangeHRM/resources/GlobalData.properties");
		pro.load(fis);
		String Field = pro.getProperty(fieldName);
		return Field;
	
	} 

	public  ExtentReports getTestReport()
	{
		
		String path = System.getProperty("user.dir")+"//Reports//Index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("OrangeHRM Test Report");
		reporter.config().setDocumentTitle("Test Automation Report");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		
			
		
		return extent;
	}
	
	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir")+"//Reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir")+"//Reports//" + testCaseName + ".png";
	}
	
 //@BeforeMethod
   public WebDriver browserSetup() throws IOException 
   {
	String browserName = getProperties("browser");
	System.out.println(browserName);
	if(browserName.equalsIgnoreCase("chrome"))
	{
		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
	}
	
	else if(browserName.equalsIgnoreCase("firefox"))	
	{
		 WebDriverManager.firefoxdriver().setup();
		 driver = new FirefoxDriver();
	}
	
	else if (browserName.equalsIgnoreCase("edge"))
	{
		 WebDriverManager.edgedriver().setup();
		 driver = new EdgeDriver();	
	}
	 
	  driver.manage().window().maximize();
	  return driver;
   }
 
   @BeforeMethod(alwaysRun = true)
  public LandingPage launchApplication() throws IOException
  {
	  driver = browserSetup();
	  landingPage = new LandingPage(driver);
	  landingPage.goTo(getProperties("url"));
	
	  return landingPage;
  }
  
  
  @AfterMethod(alwaysRun = true)
  public void closeBrowser() 
  {
	  driver.close();
  }
}
