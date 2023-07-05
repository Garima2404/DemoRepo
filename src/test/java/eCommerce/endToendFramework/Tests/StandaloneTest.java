package eCommerce.endToendFramework.Tests;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import eCommerce.endToendFramework.PageObjects.LandingPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//Traditional way
//		System.setProperty("webdriver.chrome.driver","/Users/gabby/eclipse-workspace/endToendFramework/Drivers/chromedriver");
//		WebDriver driver=new ChromeDriver();
		
		//if you add webdrivermanager to pom.xml, just write
		String proName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		//Implicit wait on global level
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		LandingPage lp=new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("garima2404@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Garima@2404");
		driver.findElement(By.id("login")).click();

//		
//		WebElement finalCart= driver.findElement(By.className("cartSection"));
//		String f=finalCart.findElement(By.tagName("h3")).getText();
//		System.out.println(finalCart.findElement(By.tagName("h3")).getText());
//		if(f.contains("ZARA")) {
//			System.out.println("cart is final");
//		}
		
		//Using iteration method
//		for(int i=0;i<products.size();i++) {
//			String[] names=products.get(i).getText().split(" ");
//			String neededProduct=names[0];
//			System.out.println(neededProduct);
//			if(neededProduct.contains("ZARA")) {
//				
//				driver.findElements(By.xpath("//button[@class='btn w-10 rounded']")).get(i).click();
//				System.out.println(driver.switchTo().alert().getText());
//				
//			}
//		}
		
		//using Streams
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']")));	
		List<WebElement> products=driver.findElements(By.xpath("//div[@class='card-body']"));
		//also correct
		//String[] a=products.stream().filter(s->s.findElement(By.tagName("b")).getText().contains("ZARA COAT 3")).findFirst().get().getText().split("\r?\n|\r");
		//System.out.println(a[0]);
		WebElement neededProduct=products.stream().filter(s->s.findElement(By.tagName("b")).getText().contains("ZARA COAT 3")).findFirst().orElse(null);
		neededProduct.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
		
		//wait until toast message(product added to cart) is seen 
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ngx-spinner[@class='ng-tns-c31-1 ng-star-inserted']")));
        //Loactor of loading circle when adding to card is //ng-animating . Ask the developer ; .classname is css
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//
		
		
		WebElement cart=driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']"));	
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click()",cart);
		
		
		
		List<WebElement> cartItems= driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		//for any products d in the stream if d.gettext matches a ie the string we are looking for, it returns true
		//also correct
		//Boolean boo=cartItems.stream().anyMatch(d->d.getText().equalsIgnoreCase(a[0]));
		Boolean boo=cartItems.stream().anyMatch(d->d.getText().equalsIgnoreCase(proName));
		Assert.assertTrue(boo);
		
		driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']")).click();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));
		
		
		
		WebElement checkout=driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Select Country']")));
		checkout.click();
//		checkout.sendKeys("INDIA");
		Actions action=new Actions(driver);
		action.sendKeys(checkout, "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String text=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(text, "THANKYOU FOR THE ORDER.");
	
		
		

	}

}
