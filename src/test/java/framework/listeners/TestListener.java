package framework.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import framework.base.DriverManager;
import framework.reporting.ReportManager;
import framework.utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        // Before any test starts in this suite
        ReportManager.initReports();
    }

    @Override
    public void onFinish(ITestContext context) {
        // After all tests in this suite are finished
        ReportManager.flushReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();

        ReportManager.startTest(testName, description != null ? description : "");
        ReportManager.getTest().log(Status.INFO, "Starting test: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ReportManager.getTest().log(Status.PASS, "Test passed");
        ReportManager.unload();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        String screenshotPath = "";
        try {
            if (DriverManager.getDriver() != null) {
            	screenshotPath = ScreenshotUtils.takeScreenshot(testName);
            	
            	ReportManager.getTest().log(Status.FAIL, "Test failed due to exception: "+ result.getThrowable());
            	
            	ReportManager.getTest().fail("Screenshot At failure", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            	
            	
//                ReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
            }
        } catch (Exception e) {
            // ignoring screenshot errors
        }

        ReportManager.getTest().log(Status.FAIL, "Test failed: " + result.getThrowable());
        if (!screenshotPath.isEmpty()) {
            ReportManager.getTest().log(Status.INFO, "Screenshot: " + screenshotPath);
        }
        ReportManager.unload();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ReportManager.getTest().log(Status.SKIP, "Test skipped");
        ReportManager.unload();
    }
}
