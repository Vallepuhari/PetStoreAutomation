package api.utilites;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext testContext) {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			repName = "Test-Report-"+timeStamp+".html";
			
			sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); 			//specify location of the file
			sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");	//title of project
			sparkReporter.config().setReportName("Pet Store Users API");				//name of report
			sparkReporter.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "Pet Store Users API");
			extent.setSystemInfo("Operating System", System.getProperty("os.name"));
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("user", "Hariprasad");	
			
			
	}
	
	public void onTestSuccess(ITest result) {
		
		test = extent.createTest(result.getTestName());
		//test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getTestName());
		test.log(Status.PASS, "Test Passed");
		
		
	}
	
public void onTestFailure(ITest result) {
	
	test = extent.createTest(result.getTestName());
	test.createNode(result.getTestName());
	//test.assignCategory(result.getMethod().getGroups());
	test.log(Status.FAIL, "Test Failed");
	//test.log(Status.FAIL, result.getThrowable().getMessage());
		
	}
	
public void onTestSkipped(ITest result) {
	
	test = extent.createTest(result.getTestName());
	test.createNode(result.getTestName());
	//test.assignCategory(result.getMethod().getGroups());
	test.log(Status.SKIP, "Test Skipped");
	//test.log(Status.SKIP, result.getThrowable().getMessage());
}

public void onFinish(ITestContext textContext) {
		extent.flush();
}

}
