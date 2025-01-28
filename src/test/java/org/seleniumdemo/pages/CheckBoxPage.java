package org.seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CheckBoxPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(CheckBoxPage.class);

    @FindBy(xpath = "//span[@class='rct-checkbox']")
    private WebElement checkBox;

    @FindBy(xpath = "//div[@id='result']/span")
    private WebElement result;

    public CheckBoxPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckbox() {
        clickElement(checkBox);
        Assert.assertEquals(getText(result), "You have selected :");
    }

}
