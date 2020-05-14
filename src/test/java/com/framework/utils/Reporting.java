package com.framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.framework.base.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting extends DriverFactory {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;
    public String timeStamp;

    ReadConfig readConfig = new ReadConfig();

    @BeforeClass
    public void onStart(ITestContext testContext) {
        timeStamp = null;
        //time stamp
        timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        String repName = "Automation Report_" + timeStamp + ".html";
        //specify location of the report
        htmlReporter = new ExtentHtmlReporter(
                System.getProperty("user.dir") + "/Results/" + timeStamp + "/" + repName);

        htmlReporter.config().setDocumentTitle("Excel In Testing Project");
        htmlReporter.config().setReportName("Test Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("Environment", readConfig.getEnvironment());
    }

    @AfterMethod
    public void result(ITestResult tr) throws IOException {
        if (tr.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, "Step Passed " + tr.getName());
        } else if (tr.getStatus() == ITestResult.SKIP) {
            logger.log(Status.SKIP, "Step Skipped " + tr.getName());
        } else if (tr.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, "Step Failed " + tr.getName());
            logger.log(Status.FAIL, "Step Failed " + tr.getThrowable());
        }
        String screenshotPath = captureScreenshot(driver, tr.getName());
        logger.addScreenCaptureFromPath(screenshotPath);
    }

    public String captureScreenshot(WebDriver driver, String ScreenshotName) throws IOException {
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        TakesScreenshot tks = (TakesScreenshot) driver;
        File src = tks.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty(
                "user.dir") + "/Results/" + timeStamp + "/Screenshots/" + ScreenshotName + date + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(src, finalDestination);
        return destination;
    }

    @AfterClass
    public void onFinish(ITestContext testContext) {
        extent.flush();
    }
}
