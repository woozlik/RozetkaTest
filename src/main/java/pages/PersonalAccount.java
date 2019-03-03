package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalAccount extends BasePage {
    @FindBy(css = "div > div:nth-child(2) > div.addit-f-i-field > div")
    private WebElement emailLabel;

    public PersonalAccount(WebDriver driver) {
        super(driver);
        waitPageToLoad();
    }

    private void waitPageToLoad(){
        waitVisibilityOf(emailLabel);
    }

    @Step("Should display confirmed email")
    public PersonalAccount shouldDisplayEmail(String email) {
        assertEquals(email.toLowerCase(), waitVisibilityAndGetText(emailLabel), "Display incorrect email");
        return this;
    }

    @Step("Open PersonalAccount page from link")
    public static PersonalAccount openPageFromLink(WebDriver driver, String link) {
        driver.get(link);
        return new PersonalAccount(driver);
    }
}
