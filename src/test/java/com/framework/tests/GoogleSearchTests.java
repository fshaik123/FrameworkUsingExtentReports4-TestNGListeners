package com.framework.tests;

import com.framework.base.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class GoogleSearchTests {
    public void GoogleSearchTest1() throws IOException {
        WebDriver driver = DriverFactory.getBrowser("chrome");

        //Takes Screenshot
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("E:\\WebDriver\\home.jpg"));
    }
}
