package com.framework.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
    public static WebDriver getBrowser(String browserType) {
        WebDriver driver = null;
        if (browserType.equalsIgnoreCase("chrome")) {
            //setup the chromedriver using WebDriverManager
            WebDriverManager.chromedriver().setup();
            //Create driver object for Chrome
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("edge")) {
            //setup the EDGE Chromium driver using WebDriverManager
            WebDriverManager.edgedriver().setup();
            //Create driver object for EDGE Chromium
            driver = new EdgeDriver();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            //setup the firefoxdriver using WebDriverManager
            WebDriverManager.firefoxdriver().setup();
            //Create driver object for Firefox
            driver = new FirefoxDriver();
        } else if (browserType.equalsIgnoreCase("ie")) {
            //setup the ie driver using WebDriverManager
            WebDriverManager.iedriver().setup();
            //Create driver object for Chrome
            driver = new InternetExplorerDriver();
        } else {
            System.out.println("Failed to initialize the WEBDRIVER");
        }
        driver.manage().window().maximize();
        return driver;
    }
}
