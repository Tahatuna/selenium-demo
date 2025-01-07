package org.seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {
    private static final Logger logger = LogManager.getLogger(BasePage.class);
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void waitUntilClickable(WebElement element) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver).
                    withTimeout(Duration.ofSeconds(60)).
                    pollingEvery(Duration.ofMillis(500)).
                    ignoring(NoSuchElementException.class).
                    ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            logger.error("Failed to wait for element to be clickable: Element: {}", element, e);
            throw new RuntimeException("Failed to wait for element to be clickable: " + e.getMessage(), e);
        }
    }

    protected void clickElement(WebElement element) {
        try {
            waitUntilClickable(element);
            element.click();
            logger.info("Element click operation has been completed. Element: {}", element);
        } catch (ElementClickInterceptedException e) {
            logger.error("Element is not clickable: Another element is blocking the click operation. Element: {}", element, e);
            throw new RuntimeException("Element click intercepted by another element: " + e.getMessage(), e);
        } catch (StaleElementReferenceException e) {
            logger.error("Element is no longer attached to the DOM. Retrying... Element: {}", element, e);
            throw new RuntimeException("Stale element reference: Element is no longer attached to the DOM. " + e.getMessage(), e);
        } catch (NoSuchElementException e) {
            logger.error("Element not found: {}", e.getMessage(), e);
            throw new NoSuchElementException("Element not found: " + e.getMessage(), e);
        } catch (TimeoutException e) {
            logger.error("Timeout while waiting for the element to be clickable: {}", e.getMessage(), e);
            throw new RuntimeException("Timeout: Element was not clickable within the given time. " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error during element click: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error during element click: " + e.getMessage(), e);
        }
    }

    private void scrollToElementWithWebElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            logger.error("Error while scrolling to element: {}", element, e);
        }
    }

    protected void scrollAndClick(WebElement element) {
        scrollToElementWithWebElement(element);
        element.click();
    }

    protected void waitUntilVisible(WebElement element) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver).
                    withTimeout(Duration.ofSeconds(60)).
                    pollingEvery(Duration.ofMillis(500)).
                    ignoring(NoSuchElementException.class).
                    ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            Assert.fail("Web Element not visible: " + e.getMessage());
        }
    }

    protected void sendKeys(WebElement element, String text) {
        waitUntilVisible(element);
        element.clear();
        element.sendKeys(text);
        logger.info("Element send keys operation has been completed. Element: {}", element);
    }

    protected String getText(WebElement element) {
        waitUntilVisible(element);
        String text = element.getText();
        logger.info("Element text is: {}", text);
        return text;
    }

}

