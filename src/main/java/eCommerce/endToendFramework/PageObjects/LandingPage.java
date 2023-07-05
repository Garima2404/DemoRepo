package eCommerce.endToendFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import eCommerce.endToendFramework.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{


	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver); //sending driver from child landingpage to parent abstractComponent
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="userEmail")
	WebElement useremail;
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	@FindBy(id="login")
	WebElement LoginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	


	
	
	By em= By.cssSelector("[class*='flyInOut']");
	
	public void hitURL()
	{
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
	}
	
	public String getErrorMessage() {
		//toast-bottom-right toast-container
		waitForElementToAppear(em);
		return errorMessage.getText();
		
	}
	
	public ProductCatalogue performAction(String emailId,String password) {
		
		useremail.sendKeys(emailId);
		Password.sendKeys(password);
		LoginButton.click();
		ProductCatalogue pc=new ProductCatalogue(driver);
		return pc;
		
		
	}
	
	
	
	

}

