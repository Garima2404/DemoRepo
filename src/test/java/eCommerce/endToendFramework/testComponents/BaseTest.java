package eCommerce.endToendFramework.testComponents;

import org.testng.annotations.AfterMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.util.*;  
import java.io.*;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import eCommerce.endToendFramework.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage lp;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties p=new Properties();
		FileInputStream fis=new FileInputStream("/Users/gabby/eclipse-workspace/endToendFramework/src/main/java/eCommerce/endToendFramework/Resources/GlobalData.properties");
		p.load(fis);
		String browserName=System.getProperty("Browser")!=null ? System.getProperty("Browser") : p.getProperty("Browser");
		//p.getProperty("Browser");
		
		if(browserName.contains("Chrome")) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options= new ChromeOptions();
		driver=new ChromeDriver(options);
		if(browserName.contains("headless")) {
		options.addArguments("headless");
		}
		driver.manage().window().setSize(new Dimension(1440,900));
		
	
		
	}
		else if(browserName.equals("Edge")) {
			System.setProperty("webdriver.edge.driver", "edge.exe");
			driver=new EdgeDriver();
			
		}
        else if(browserName.equals("Firefox")) {
			//firefox code
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		return driver;

}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver=initializeDriver();
		lp=new LandingPage(driver);
		lp.hitURL();
		return lp;	
		
	}
	
	public String screenShot(String testCaseName, WebDriver driver) throws IOException{
		TakesScreenshot tk=((TakesScreenshot)driver);
		File src=tk.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("User.dir") + "//reports" + ".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("User.dir") + "//reports" + ".png";
		
	}
	
	public List<HashMap<String,String>> getJSONdata(String FilePath) throws IOException {
	
		String jsonFile=FileUtils.readFileToString(new File(FilePath),StandardCharsets.UTF_8);;
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonFile, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
}