package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testbase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups= {"Sanity","Master"})
	public void verify_Login()
	{
		logger.info("****** Test TC002_LoginTest Stated ******");
		try {
		HomePage hp = new HomePage(driver);
		hp.clkMyAccount();
		logger.info("****** MyAccClicked ******");
		hp.clkLogin();
		logger.info("****** LoginClicked ******");
		
		logger.info("****** Clicked Loggon Button ******");
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clkLogin();
		
		MyAccountPage macc = new MyAccountPage(driver);
		
		boolean result= macc.isMyAccountPageExist();
		Assert.assertTrue(result);	
		}
		catch(Exception e) 
		{
			Assert.fail();
			logger.info("****** Test TC002_LoginTest Failed ******");
		}
		logger.info("****** Test TC002_LoginTest Finished ******");
	}
	
}
