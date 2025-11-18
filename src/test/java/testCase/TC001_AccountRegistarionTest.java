package testCase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testbase.BaseClass;

public class TC001_AccountRegistarionTest extends BaseClass
{
	

	@Test(groups= {"Regression","Master"})
	public void verify_account_Registration() 
	{
		try {
		logger.info("******  Testing Started *****");
		HomePage hp = new HomePage(driver);
		hp.clkMyAccount();
		hp.clkRegister();
		
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString() + "@gmail.com");
		regpage.setPhonenumber(randomNumber());
		
		String Password = AlpaNumeric();
		
		regpage.setPassword(Password);
		regpage.setconfirmPassword(Password);
		regpage.setPolicy();
		regpage.ClickSubmit();

		
		String cnfmsg= regpage.getConfirmationmsg();
		
		if(cnfmsg.equals("Your Account Has Been Created!"))
		{
			AssertJUnit.assertTrue(true);
			logger.info("***** TestPassed *****");
		}
		else {
			logger.error("***** Test Failed *****");
			logger.debug("***** debug Logs *****");
			AssertJUnit.assertTrue(false);
		}
		}
		catch (Exception e) 
		{
			AssertJUnit.fail();
		}
		
	}
	
	
}
