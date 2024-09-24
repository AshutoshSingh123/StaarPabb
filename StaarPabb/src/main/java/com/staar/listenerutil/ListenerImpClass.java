package com.staar.listenerutil;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.staar.baseutil.BaseClass;
import com.staar.webdriverutility.UtilityClassObject;

/**
 * this class is the implementation class for listener feature
 * @author Ashutosh
 * 
 */
public class ListenerImpClass extends BaseClass implements ITestListener, ISuiteListener {
	
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;
	
	
	/**
	 * @return void
	 * @author Ashutosh
	 */
	@Override
	public void onStart(ISuite suite) {
		System.out.println("DB CONNECTED---REPORT CONFIGURED");
		ISuiteListener.super.onStart(suite);
		String timestamp = new Date().toString().replace(" ", "_").replace(":", "_");
		
		/**
		 * spark report configuration
		 */
		spark=new ExtentSparkReporter("./AdvReport/report_"+timestamp+".html");
		spark.config().setDocumentTitle("StaarCMS Test Suite Report");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		/**
		 * create a new report
		 */
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "WIN10");
		report.setSystemInfo("Browser","Chrome");
	}
	
	/**
	 * @return void
	 * @author Ashutosh
	 */
	@Override
	public void onFinish(ISuite suite) {
		System.out.println("DB DIS-CONNECTED---REPORT BACKUP COMPLETE");
		ISuiteListener.super.onFinish(suite);
		report.flush();
	}
	
	/**
	 * @author Ashutosh
	 * @return void
	 */
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("onTestStart====TestStarted");
		ITestListener.super.onTestStart(result);
		test=report.createTest(result.getMethod().getMethodName());
		
		/**
		 * set the test variable in utility class object
		 */
		UtilityClassObject.setTest(test);
		
		test.log(Status.INFO, result.getMethod().getMethodName()+"==TEST STARTED==");
	}
	
	/**
	 * @return void
	 * @author Ashutosh
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess====TestComplete");
		ITestListener.super.onTestSuccess(result);
		test.log(Status.INFO, result.getMethod().getMethodName()+"==TEST SUCCESSFULLY COMPLETE==");
	}
	
	/**
	 * method to take a screenshot everytime a test fails
	 * and save it in a folder in the project structure
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		TakesScreenshot t=(TakesScreenshot) sdriver;
		String testname = result.getMethod().getMethodName();
		String timestamp = new Date().toString().replace(" ", "_").replace(":", "_");
		try {
			FileUtils.copyFile(t.getScreenshotAs(OutputType.FILE), new File("./Screenshots/"+testname+"_"+timestamp+"_"+".png"));
		} catch (WebDriverException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromBase64String(t.getScreenshotAs(OutputType.BASE64), testname+"_"+timestamp);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"==TEST FAILED==");
		ITestListener.super.onTestFailure(result);
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		ITestListener.super.onFinish(context);
	}
	
	
}
