package wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

final public class WebElementWait {
    private WebDriverWait webDriverWait;

    public WebElementWait(WebDriver driver){
        webDriverWait = new WebDriverWait(driver, 10);
    }

    public WebElement waitVisibilityOf(WebElement webElement){
        return webDriverWait.until(visibilityOf(webElement));
    }

    public List<WebElement> waitVisibilityOf(List<WebElement> webElements){
        return webDriverWait.until(visibilityOfAllElements(webElements));
    }

    public WebElement waitVisibilityOf(By by){
        return webDriverWait.until(visibilityOfElementLocated(by));
    }

    public WebElement waitToBeClickable(WebElement webElement){
        return webDriverWait.until(elementToBeClickable(webElement));
    }
}
