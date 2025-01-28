package org.seleniumdemo.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.seleniumdemo.base.TestBase;
import org.seleniumdemo.pages.CheckBoxPage;
import org.seleniumdemo.pages.ElementsPage;
import org.seleniumdemo.pages.TextBoxPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ElementsTest extends TestBase {
    private static final Logger logger = LogManager.getLogger(ElementsTest.class);
    private ElementsPage elementsPage;
    private TextBoxPage textBoxPage;
    private CheckBoxPage checkBoxPage;

    @BeforeMethod
    public void setUpPages() {
        elementsPage = new ElementsPage(driver);
        textBoxPage = new TextBoxPage(driver);
        checkBoxPage = new CheckBoxPage(driver);
    }

    @Test(priority = 1)
    public void testSubmitFormInTextBox(){
        elementsPage.goToTextBox();
        textBoxPage.fillFormInTextBox();
    }

    @Test(priority = 2)
    public void testInCheckBox(){
        elementsPage.goToCheckBox();
        checkBoxPage.clickCheckbox();
    }

}
