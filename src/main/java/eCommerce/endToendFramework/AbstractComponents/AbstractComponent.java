package eCommerce.endToendFramework.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import eCommerce.endToendFramework.PageObjects.CartPageObjects;
import eCommerce.endToendFramework.PageObjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement Orders;
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> orders;
	
	By btn=By.xpath("//button[@routerlink='/dashboard/myorders']");


	public  void waitForElementToAppear(By locator) 
	{
	WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(100));
	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
	
	public void waitForElementToDisappear(WebElement l) 
	{
	WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(100));
	wait.until(ExpectedConditions.invisibilityOf(l));
    }
	
	public void waitForelementToBeClickable(WebElement u) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(100));
		wait.until(ExpectedConditions.elementToBeClickable(u));
	}
	
	
	public CartPageObjects clickOnCart() throws InterruptedException {
		Thread.sleep(1000);
//		JavascriptExecutor js=((JavascriptExecutor)driver);
//		js.executeScript("arguments[0].click()",cart);
		cart.click();
		CartPageObjects cpo=new CartPageObjects(driver);
		return cpo;
	}
	
	public OrderPage clickOnOrderHistory() {
		waitForElementToAppear(btn);
		Orders.click();
		System.out.println("Just for my reference");
		OrderPage op=new OrderPage(driver);
		return op;
		

		
	}
	
}