package org.seleniumdemo.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.seleniumdemo.utilities.ConfigReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    private static final Logger logger = LogManager.getLogger(TestBase.class);
    private final String baseUrl = ConfigReader.getProperty("baseUrl");
    protected WebDriver driver;
    protected ChromeOptions chromeOptions;

    @BeforeClass
    public void setUp() {
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.navigate().to(baseUrl);
        logger.info("Opened website url: " + baseUrl);
    }

    @AfterClass
    public void tearDown() {
        logger.info("Tests completed");
        if (driver != null) {
            driver.quit();
        }
    }

}
