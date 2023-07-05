package eCommerce.endToendFramework.PageObjects;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;

import eCommerce.endToendFramework.AbstractComponents.AbstractComponent;

public class CartPageObjects extends AbstractComponent {
	WebDriver driver;

	public CartPageObjects(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartItems;
	
	@FindBy(css="li[class='totalRow'] button[type='button']")
	WebElement checkout;

	By loca=By.xpath("//div[@class='cartSection']/h3"); 
	


	public Boolean verifyIfProductNameMatchesInCart(String productName) {
		waitForElementToAppear(loca);
		Boolean boo=cartItems.stream().anyMatch(d->d.getText().equalsIgnoreCase(productName));
		return boo;
	}
	
	public CheckoutPageObjects clickOnCheckoutButton() {
		checkout.click();
		CheckoutPageObjects chpo=new CheckoutPageObjects(driver);
		return chpo;
	}
	
	

}
