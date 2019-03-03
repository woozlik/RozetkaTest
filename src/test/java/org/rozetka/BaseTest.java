package org.rozetka;

import driver.WebDriverService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

abstract public class BaseTest {
    protected WebDriver driver;
    private WebDriverService webDriverService;

    @BeforeEach
    public void beforeTest() {
        webDriverService = new WebDriverService();
        driver = webDriverService.getDriver();
    }

    @AfterEach
    public void closeBrowser() {
        webDriverService.quitDriver();
    }
}
