package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage 

	{
	
	//constructor
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}

	//locators
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstName;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtPhoneNumber ;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword ;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfirmPassword;
	@FindBy(xpath="//input[@name='agree']") WebElement chkPolicy;
	@FindBy(xpath="//input[@value='Continue']") WebElement btnSubmit;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement confirmationMsg;

	//actionMethod
	
	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	public void setconfirmPassword(String pwd)
	{
		txtConfirmPassword.sendKeys(pwd);
	}
	public void setPhonenumber(String number)
	{
		txtPhoneNumber.sendKeys(number);
	}
	public void setPolicy()
	{
		chkPolicy.click();
	}
	public void ClickSubmit()
	{
		btnSubmit.click();
	}
	
	public String getConfirmationmsg()
	{
		try 
		{
		return(confirmationMsg.getText());
		}
		catch (Exception e) 
		{
			return(e.getMessage());  //it retrun exception message
		}
		
	}
}
