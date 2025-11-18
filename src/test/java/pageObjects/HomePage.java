package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	//constructor
	public HomePage(WebDriver driver) 
	{
		super(driver); //it invoke Basepage Constructor
	}
	
	//Locators
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkMyAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkRegister ;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement lnkLogin;
	//ActionMethod
	
	public void clkMyAccount()
	{
		lnkMyAccount.click();
	}
	
	public void clkRegister()
	{
		lnkRegister.click();
	}
	
	public void clkLogin()
	{
		lnkLogin.click();
	}
}
