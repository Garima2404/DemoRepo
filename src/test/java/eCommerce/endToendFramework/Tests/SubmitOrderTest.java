package eCommerce.endToendFramework.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eCommerce.endToendFramework.PageObjects.CartPageObjects;
import eCommerce.endToendFramework.PageObjects.CheckoutPageObjects;
import eCommerce.endToendFramework.PageObjects.FinalPagePageObjects;
import eCommerce.endToendFramework.PageObjects.LandingPage;
import eCommerce.endToendFramework.PageObjects.OrderPage;
import eCommerce.endToendFramework.PageObjects.ProductCatalogue;
import eCommerce.endToendFramework.testComponents.BaseTest;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException  {

	LandingPage lp= launchApplication();
	ProductCatalogue pc=lp.performAction(input.get("email"), input.get("password"));
	pc.getProductByName(input.get("product"));
	pc.addProductToCart(input.get("product"));
	CartPageObjects cpo= pc.clickOnCart();
	Boolean boo=cpo.verifyIfProductNameMatchesInCart("ZARA COAT 3");
	Assert.assertTrue(boo);// assertion should always be in test class
	CheckoutPageObjects chpo=cpo.clickOnCheckoutButton();
	
	chpo.enterCountry("india");
	chpo.selectCountry();
	FinalPagePageObjects fpo=chpo.clickFinalSubmissionOfCart();
	
	fpo.getStringMessage();
	Assert.assertTrue(fpo.getStringMessage().equalsIgnoreCase("Thankyou for the order."));

}
	//Verify ZARA COAT 3 is displyed on orders page
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() throws IOException {
		//ProductCatalogue pc=lp.performAction("garima2404@gmail.com", "Garima@2404");
		//lp.clickOnOrderHistory();
		
		OrderPage op=lp.clickOnOrderHistory();
		Assert.assertTrue(op.getOrderList());
		
		
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		//data using hashmap
//		HashMap<String, String> map =new HashMap<String,String>();
//		
//		map.put("email", "garima2404@gmail.com");
//		map.put("password", "Garima@2404");
//		map.put("product", "ZARA COAT 3");
//		
//		
//	HashMap<String, String> map1 =new HashMap<String,String>();
//		
//	map1.put("email", "shetty@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL");
		
		
		List<HashMap<String,String>> data =getJSONdata("/Users/gabby/eclipse-workspace/endToendFramework/src/test/java/eCommerce/endToendFramewor/TestData/PurchaseOrderFile.JSON");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		
		//public Object[][] getData()
		//return new Object[][] {{"garima2404@gmail.com","Garima@2404","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}} ;
		
	}
	
	
}
