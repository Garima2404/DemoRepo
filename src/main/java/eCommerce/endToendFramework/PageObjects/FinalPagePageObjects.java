package eCommerce.endToendFramework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import eCommerce.endToendFramework.AbstractComponents.AbstractComponent;

public class FinalPagePageObjects extends AbstractComponent {
	WebDriver driver;
	
	public FinalPagePageObjects(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
 @FindBy(className="hero-primary")
 WebElement text;
 
 By textmessage = By.className("hero-primary");
 
	public String getStringMessage() {
		waitForElementToAppear(textmessage);
		return text.getText();
		
	}

}
