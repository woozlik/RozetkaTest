package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
    private static final String signUpURL = "https://my.rozetka.com.ua/signup/";
    @FindBy(css = "#signup_form input[name = 'title']")
    private WebElement nameInput;
    @FindBy(css = "#signup_form input[name = 'login']")
    private WebElement emailInput;
    @FindBy(css = "#signup_form input[name = 'password']")
    private WebElement passwordInput;
    @FindBy(css = ".signup-submit [type = 'submit']")
    private WebElement submitBt;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Registration page")
    public RegistrationPage openRegistrationPage() {
        driver.get(signUpURL);
        return this;
    }

    @Step("Type name in SignUp form")
    public RegistrationPage typeNameInSignUpForm(String name) {
        waitVisibilityAndType(nameInput, name);
        return this;
    }

    @Step("Type email in SignUp form")
    public RegistrationPage typeEmailInSignUpForm(String email) {
        waitVisibilityAndType(emailInput, email);
        return this;
    }

    @Step("Type password in SignUp form")
    public RegistrationPage typePasswordInSignUpForm(String password) {
        waitVisibilityAndType(passwordInput, password);
        return this;
    }

    @Step("Click on submit button on SignUp Form")
    public PersonalAccount clickOnSubmitBtnOnSignUpForm() {
        waitVisibilityAndClickOn(submitBt);
        return new PersonalAccount(driver);
    }

}
