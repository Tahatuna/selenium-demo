package org.seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ElementsPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(ElementsPage.class);

    @FindBy(xpath = "//span[text()='Text Box']")
    private WebElement textBoxPageBtn;

    @FindBy(xpath = "//span[text()='Check Box']")
    private WebElement checkBoxPageBtn;

    public ElementsPage(WebDriver driver) {
        super(driver);
    }

    public TextBoxPage goToTextBox() {
        logger.info("Go to text box");
        clickElement(textBoxPageBtn);
        return new TextBoxPage(driver);
    }

    public CheckBoxPage goToCheckBox() {
        logger.info("Go to checkbox");
        clickElement(checkBoxPageBtn);
        return new CheckBoxPage(driver);
    }

}
