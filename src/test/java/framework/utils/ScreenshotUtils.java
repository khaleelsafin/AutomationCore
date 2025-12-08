package framework.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.google.common.io.Files;

import framework.base.DriverManager;

public class ScreenshotUtils {
	
	public static String takeScreenshot(String testname) {
		File srcFile = ((TakesScreenshot) DriverManager.getdriver()).getScreenshotAs(OutputType.FILE);
		
		
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());	
		String screenshotPath = "target/screenshots/"+ testname + "_" + timestamp + ".png";
		
		File destFile = new File(screenshotPath);
		
		destFile.getParentFile().mkdir();
		
		try {
			Files.copy(srcFile, destFile);
		} catch(Exception e) {
			throw new RuntimeException("Failed to save screenshot", e);
		}
		
		return screenshotPath;
		
	}

}
