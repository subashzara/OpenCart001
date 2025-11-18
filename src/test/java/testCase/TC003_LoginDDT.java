package testCase;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testbase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass 
{

/*
Data is valid  - login success - test pass  - logout
login failed - test fail

Data is invalid - login success - test fail  - logout
login failed - test pass
*/
	
	@Test (groups=("DataDriven"),dataProvider="LoginData", dataProviderClass=DataProviders.class) // getting data provider from different class
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		
		logger.info("***** Test Started *****");
		try {
		HomePage hp = new HomePage(driver);
		hp.clkMyAccount();
		hp.clkLogin();
		
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clkLogin();
		
		MyAccountPage macc = new MyAccountPage(driver);
		
		boolean result= macc.isMyAccountPageExist();
		
		if(exp.equalsIgnoreCase("Valid")) 
		{
			if(result==true)
			{
				macc.clkLogout();
				Assert.assertTrue(true);
			}
			else{
				
				Assert.assertTrue(false);
			}
			
		}
		
		if(exp.equalsIgnoreCase("Invalid")) 
		{
			if(result==true)
			{
				macc.clkLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
	} catch(Exception e) 
		{
		Assert.fail();
		}
		
		logger.info("***** Test Finished *****");
	}
}
