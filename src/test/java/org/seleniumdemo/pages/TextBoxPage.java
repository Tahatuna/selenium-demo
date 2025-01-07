package org.seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.seleniumdemo.utilities.ConfigReader;
import org.testng.Assert;

public class TextBoxPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(TextBoxPage.class);

    @FindBy(id = "userName")
    private WebElement userName;
    @FindBy(id = "userEmail")
    private WebElement email;
    @FindBy(id = "currentAddress")
    private WebElement currentAddress;
    @FindBy(id = "permanentAddress")
    private WebElement permanentAddress;
    @FindBy(id = "submit")
    private WebElement submit;
    @FindBy(id= "name")
    private WebElement name;

    public TextBoxPage(WebDriver driver) {
        super(driver);
        ConfigReader.loadProperties("src/test/resources/user.properties");
        logger.info("Loaded user.properties file for TextBoxPage");
    }

    public void fillFormInTextBox() {
        String currentUser = ConfigReader.getProperty("userName");
        sendKeys(userName, currentUser);
        sendKeys(email, ConfigReader.getProperty("email"));
        sendKeys(currentAddress, ConfigReader.getProperty("currentAddress"));
        sendKeys(permanentAddress, ConfigReader.getProperty("permanentAddress"));
        scrollAndClick(submit);
        Assert.assertEquals(getText(name), "Name:" + currentUser );
    }

}
