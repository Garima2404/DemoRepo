package eCommerce.endToendFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import eCommerce.endToendFramework.AbstractComponents.AbstractComponent;


public class OrderPage extends AbstractComponent{


	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		
		super(driver); //sending driver from child landingpage to parent abstractComponent
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement Orders;
	
	
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> orders;
	
	public Boolean getOrderList() {

		Boolean b=orders.stream().filter(d->d.getText().equalsIgnoreCase("ZARA COAT 3")).findAny().isPresent();
		return b;
	}
	
	

}

