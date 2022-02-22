package com.onmobile.api.helper;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@Listeners({RestListener.class})
public class RestListener implements ITestListener{

	public static ExtentTestFactory extentTestFactory;
	public File WORKING_DIRECTORY;
	private static ExtentReports extent;

	// public static String filePath = System.getProperty("user.dir") +
	// "/Reports/ExtentReport_" + LocalDateTime.now()
	// + ".html";

	public static void deleteDirectory(File directory) {
		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					System.out.println(files[i].getName());
					if (files[i].isDirectory()) {
						deleteDirectory(files[i]);
					} else {
						files[i].delete();
					}
				}
			}
		}
	}

	private static String resultpath = getResultPath() + ".html";

	private static String getResultPath() {

		resultpath = new SimpleDateFormat("yyyy-MM-dd hh-mm.ss").format(new Date());
		if (!new File(resultpath).isDirectory()) {
			new File(resultpath);
		}
		return resultpath;
	}

	public static String ReportLocation = "test-output/Report/Extent_Report_" + resultpath + "/";

	public RestListener() {
		extentTestFactory = new ExtentTestFactory();
	}

	/**
	 * Load Extent Config xml file
	 */
	public static void loadExtentFile() {
		new File(ReportLocation);
		extent = new ExtentReports();
		extent.attachReporter(getHtmlReporter());
	}

	/**
	 *
	 * @return ExtentHtmlReporter Instance
	 */
	private static ExtentHtmlReporter getHtmlReporter() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(ReportLocation);
		htmlReporter.config().setDocumentTitle("Onmobile -Automation");
		htmlReporter.config().setReportName("API Automation");
		htmlReporter.config().setTheme(Theme.DARK);
		return htmlReporter;
	}

	public static void configExtenTest(String className) {

		ExtentTest parent = extent.createTest(className);
		ExtentTestFactory.setExtentTest(parent);
	}

	public void childMethods(Method name, String className, String testDataName) {

		// Extent Report Configuration

		ExtentTest child = ExtentTestFactoryParent.getExtentTest().createNode(testDataName);
		ExtentTestFactory.setExtentTest(child);

	}

	/**
	 * Flush the report
	 */
	public void flushReport() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		extent.flush();
	}

	/**
	 * Result Status Capture
	 * 
	 * @param result
	 */
	public void testResultCapture(ITestResult result) {

		/**
		 * Success Block
		 */
		if (result.getStatus() == ITestResult.SUCCESS) {
			ExtentTestFactory.getExtentTest().log(Status.PASS, result.getMethod().getMethodName() + " Passed");
		}

		/**
		 * Failure Block
		 */
		if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTestFactory.getExtentTest().fail(result.getThrowable().getMessage());

		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
