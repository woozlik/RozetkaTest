package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import wait.WebElementWait;

abstract public class BasePage {
    WebDriver driver;
    private WebElementWait webElementWait;

    BasePage(WebDriver driver){
        this.driver = driver;
        webElementWait = new WebElementWait(driver);
        PageFactory.initElements(driver, this);
    }

    void waitVisibilityAndClickOn(WebElement webElement){
        waitVisibilityOf(webElement).click();
    }

    WebElement waitVisibilityOf(WebElement webElement){
        return webElementWait.waitVisibilityOf(webElement);
    }

    void waitVisibilityAndType(WebElement webElement, String text){
        waitVisibilityOf(webElement).sendKeys(text);
    }

    void waitToBeClickableAndClickOn(WebElement webElement){
        webElementWait.waitToBeClickable(webElement).click();
    }

    String waitVisibilityAndGetText(WebElement webElement){
        return webElementWait.waitToBeClickable(webElement).getText();
    }
}