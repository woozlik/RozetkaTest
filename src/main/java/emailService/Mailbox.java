package emailService;

public interface Mailbox {
    EmailAccount createTempAccount(String accountName);
}
