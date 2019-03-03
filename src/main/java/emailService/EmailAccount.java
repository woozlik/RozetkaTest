package emailService;

public interface EmailAccount {
    Email[] getEmails();
    Email searchForEmailFromSender(String sender) throws NoSuchEmail;
    void deleteAccount();
}
