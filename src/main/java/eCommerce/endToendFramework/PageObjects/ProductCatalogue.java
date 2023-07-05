package eCommerce.endToendFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import eCommerce.endToendFramework.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver){
		super(driver); //sending driver from child landingpage to parent abstractComponent{every child need to serve parent :P}
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//List<WebElement> products=driver.findElements(By.xpath("//div[@class='card-body']"));
	
	@FindBy(xpath="//div[@class='card-body']")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement loadercircle;
	

	
	By loc=By.xpath("//div[@class='card-body']"); //by locator for elements to appear on page 
	By addtocart=By.xpath("//button[@class='btn w-10 rounded']");
	By toastMessage=By.xpath("//ngx-spinner[@class='ng-tns-c31-1 ng-star-inserted']"); //by locator for element to display toast message after click add to cart
	
	
	public List<WebElement> getListOfProductsNeeded() {  
		//By.xpath("//div[@class='card-body']"))
		waitForElementToAppear(loc);
		return products;
		
	}
	
	public WebElement getProductByName(String name) {
		WebElement neededProduct=getListOfProductsNeeded().stream().filter(s->s.findElement(By.tagName("b")).getText().contains(name)).findFirst().orElse(null);
		return neededProduct;
		
	}
	
	public void addProductToCart(String name) {
		
		getProductByName(name).findElement(addtocart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(loadercircle);
		
	}
	
	


}
