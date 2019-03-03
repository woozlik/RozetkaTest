package org.rozetka;

import emailService.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.PersonalAccount;
import pages.RegistrationPage;
import parser.HTMLParser;

import static emailService.PostShiftEmailAccount.DOMAIN;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class SignUpTest extends BaseTest {
    private EmailAccount postShiftAccount;
    private Mailbox postShiftMailbox;
    final String accountName = randomAlphabetic(8);

    @BeforeEach
    public void setupPreconditions() {
        postShiftMailbox = new PostShiftMailbox();
        postShiftAccount = postShiftMailbox.createTempAccount(accountName);
    }

    @Test
    public void activateAccountByConfirmLink() throws NoSuchEmail {
        final String tempEmail = accountName + DOMAIN;

        new RegistrationPage(driver)
                .openRegistrationPage()
                .typeNameInSignUpForm("Serhii")
                .typeEmailInSignUpForm(tempEmail)
                .typePasswordInSignUpForm("3ANmSyz4yBL7ibt")
                .clickOnSubmitBtnOnSignUpForm();

        Email email = postShiftAccount.searchForEmailFromSender("ROZETKA");
        HTMLParser parser = new HTMLParser(email.getMessage());
        String confirmEmailLink = parser.findLinkThatContains("confirm");

        PersonalAccount
                .openPageFromLink(driver, confirmEmailLink)
                .shouldDisplayEmail(tempEmail);
    }

    @AfterEach
    public void tearDown() {
        postShiftAccount.deleteAccount();
    }
}
