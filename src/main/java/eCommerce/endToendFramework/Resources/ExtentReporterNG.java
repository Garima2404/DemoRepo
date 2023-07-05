package eCommerce.endToendFramework.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	
	public static ExtentReports getReportObject() {
		
		String path=System.getProperty("user.dir") + "//Reports//index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports exp=new ExtentReports();
		exp.attachReporter(reporter);
		exp.setSystemInfo("tester", "Garima");
		exp.createTest(path);
		return exp;
	}
}
