package eCommerce.endToendFramework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import eCommerce.endToendFramework.AbstractComponents.AbstractComponent;

public class CheckoutPageObjects extends AbstractComponent {
	WebDriver driver;
	
	public CheckoutPageObjects(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	private WebElement countryEnter;
	
	@FindBy(xpath="(//button[@type='button'])[2]")
	private WebElement countrySelection;
	
	@FindBy(css=".action__submit")
	private WebElement submitButton;
	 
	By countryList=By.cssSelector(".ta-results");
	

	public void enterCountry(String countryName) {
		//waitForelementToBeClickable(countrySelection);
		Actions action=new Actions(driver);
		action.sendKeys(countryEnter, countryName).build().perform();
		
			
	}

	public void selectCountry() {
		waitForElementToAppear(countryList);
		countrySelection.click();
		
	}
	
	public FinalPagePageObjects clickFinalSubmissionOfCart() {
		waitForelementToBeClickable(submitButton);
		submitButton.click();
		FinalPagePageObjects fpo=new FinalPagePageObjects(driver);
		return fpo;
		
		
		
	}
}
