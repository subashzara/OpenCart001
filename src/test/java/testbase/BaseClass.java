package testbase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;


public class BaseClass 
{
	public static WebDriver driver; //to make them One Driver for all methods
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master" ,"DataDriven"})
	@Parameters ({"os","browser"})
	public void setup(String os, String br) throws IOException 
	{
		logger= LogManager.getLogger(this.getClass()); //to createLog
		
		FileReader file = new FileReader("./src/test/resources/Config.Properties");
		p= new Properties();
		p.load(file);
		
		//GRID_ENV
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
		DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
		/*
			if(os.equalsIgnoreCase("windows")) 
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("macos")) 
			{
				logger.info("**** MacoS Started");
				capabilities.setPlatform(Platform.MAC);
			}
			else 
			{
				System.out.println("No matching OS");
			}
			*/
			switch(os.toLowerCase()) 
			{
			case "windows": capabilities.setPlatform(Platform.WIN11); break;
			case "macos": capabilities.setPlatform(Platform.MAC); break;
			case "linux": capabilities.setPlatform(Platform.LINUX); break;
			default: System.out.println("No matching OS Found"); return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "chromium": capabilities.setBrowserName("chromium"); break;
			case "safari": capabilities.setBrowserName("safari"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("No matching browser"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
			
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			switch(br.toLowerCase()) 
			{
			case "chrome" : driver = new ChromeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			case "safari" : driver = new SafariDriver(); break;
			default : System.out.println("Invalid Browser"); return;
		
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master", "DataDriven"})
	public void tearDown()
	{
		driver.close();
	}
	
	public String randomString() 
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	public String randomNumber() 
	{
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String AlpaNumeric() 
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		String generatedNumber=RandomStringUtils.randomNumeric(3);
		
		return (generatedString + "@" + generatedNumber);
		
	}
	/*
	 * Incase of new method for randomAlphaphetic and RandomNumbers refer below
	 * ----------------------------------
	 * 
	 * import org.apache.commons.lang3.RandomStringUtils;

		public class RandomStringGenerator 
		{
    	public static void main(String[] args)
    	 	{
        String secureNumericString = RandomStringUtils.secure().nextNumeric(10);
        System.out.println("Secure numeric string: " + secureNumericString);
    		}
		}
		
	 */
	public String captureScreen(String tname) throws IOException 
	
	{

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"/screenshots/" + tname + "_" + timeStamp + ".png";
		//String targetFilePath= "/Users/subashzara/Desktop/OpenCart/OpenCart001/screenshots";
		
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	
}
