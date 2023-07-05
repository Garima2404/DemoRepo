package eCommerce.endToendFramework.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import eCommerce.endToendFramework.PageObjects.CartPageObjects;
import eCommerce.endToendFramework.PageObjects.CheckoutPageObjects;
import eCommerce.endToendFramework.PageObjects.FinalPagePageObjects;
import eCommerce.endToendFramework.PageObjects.LandingPage;
import eCommerce.endToendFramework.PageObjects.ProductCatalogue;
import eCommerce.endToendFramework.testComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class stepDefinitionImplementation extends BaseTest {
	
	public static LandingPage lp;
	public static ProductCatalogue pc;
	public static CartPageObjects cpo;
	public static CheckoutPageObjects chpo;
	public static FinalPagePageObjects fpo;
	
	
	
	@Given("I land on eCommerce page")
	public void I_land_on_eCommerce_page() throws IOException {
		
		
		lp= launchApplication();
		System.out.println("first method");
		//code
	}
	
	@Given("^Logged in with email (.+) and password (.+) $")
	public void Logged_in_with_email_and_password(String email,String password) {
		 pc=lp.performAction(email, password);
		 System.out.println("Second method");
		
	}
	@When("^I add productName (.+) to cart$")
	public void add_productName_to_cart(String productName) {
		pc.addProductToCart(productName);
	}
	
	@When("^Checkout productName (.+) and submit the order$")
	public void Checkout_submit_the_Order(String productName) throws InterruptedException {
		 cpo= pc.clickOnCart();
		 Boolean boo=cpo.verifyIfProductNameMatchesInCart("ZARA COAT 3");
		 Assert.assertTrue(boo);// assertion should always be in test class
		 chpo=cpo.clickOnCheckoutButton();
	}
	
	@Then("{string} is displayed on ConfirmationPage")
		public void message_displayed(String string) {
		
		chpo.enterCountry("india");
		chpo.selectCountry();
		fpo=chpo.clickFinalSubmissionOfCart();
		fpo.getStringMessage();
		Assert.assertTrue(fpo.getStringMessage().equalsIgnoreCase(string));
		
	
	}
	
	@Then("{string} is displayed on loginPage")
	public void Error_Message_displayed(String email, String password){
		lp.performAction(email, password);
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
		driver.close();
		
	}
}
