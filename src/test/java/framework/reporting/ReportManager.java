package framework.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {

	private static ExtentReports extent;

	private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	private ReportManager() {

	}

	public static void initReports() {
		if (extent != null) {
			return;
		}

		ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");

		spark.config().setReportName("Automation Test Report");
		spark.config().setDocumentTitle("Test Exectution Report");

		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Project", "SauceDemo Automation");
		extent.setSystemInfo("Tester", "Khaleel");

	}

	public static void flushReports() {
		if (extent != null) {
			extent.flush();
		}
	}

	public static void startTest(String TestName, String TestDescription) {
		ExtentTest extentTest = extent.createTest(TestName, TestDescription);
		test.set(extentTest);
	}

	public static void unload() {
		test.remove();
	}
}
