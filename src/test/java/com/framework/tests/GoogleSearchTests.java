package com.framework.tests;

import com.aventstack.extentreports.ExtentTest;
import com.framework.base.DriverFactory;
import com.framework.pages.HomePage;
import com.framework.utils.Reporting;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;


public class GoogleSearchTests extends Reporting {
    @Test
    public void GoogleSearchTest1() throws IOException {
        logger = extent.createTest("GoogleSearchTest1");
        driver.navigate().to(getAppURL());
        HomePage homePage = new HomePage(driver);
        homePage.typeTextInTheSearchField("excel in testing");

        String title = driver.getTitle();
        logger.createNode("Verify Title");
        Assert.assertEquals(title, "Google");
        homePage.getResultsCount();
        logger.createNode("Verify Results count is displayed");
    }
}
