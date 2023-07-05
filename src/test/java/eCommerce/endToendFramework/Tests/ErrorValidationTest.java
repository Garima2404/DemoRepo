package eCommerce.endToendFramework.Tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.AssertJUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import eCommerce.endToendFramework.PageObjects.CartPageObjects;
import eCommerce.endToendFramework.PageObjects.ProductCatalogue;
import eCommerce.endToendFramework.testComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {
	
	@Test(groups= {"ErrorHandling"})
	public void verifyErrorMessageonWrongCredentials() {
		

		lp.performAction("garima2404@gmail.com", "Garima@240488");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
		
	}
	
	@Test
	public void verifyIfProductNameMatchesInCartNegScenario() throws InterruptedException {	
		ProductCatalogue pc=lp.performAction("garima2404@gmail.com", "Garima@2404");
		pc.getListOfProductsNeeded();
		pc.addProductToCart("ZARA COAT 3");
		CartPageObjects cpo=pc.clickOnCart();
		cpo.verifyIfProductNameMatchesInCart("ZARA");
		Assert.assertFalse(cpo.verifyIfProductNameMatchesInCart("ZARA"));		
	}
	

}
