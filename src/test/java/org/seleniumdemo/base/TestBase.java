package org.seleniumdemo.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumdemo.utilities.ConfigReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    private static final Logger logger = LogManager.getLogger(TestBase.class);
    private final String baseUrl = ConfigReader.getProperty("baseUrl");
    private WebDriver driver;
    private ChromeOptions chromeOptions;
    private WebDriverWait webDriverWait;

    @BeforeClass
    public void setUp() {
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        if (webDriverWait != null) {
            webDriverWait = null;
        }
    }

}
