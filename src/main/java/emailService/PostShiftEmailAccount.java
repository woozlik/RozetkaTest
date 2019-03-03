package emailService;

import request.Request;
import java.util.stream.Stream;

import static emailService.PostShiftMailbox.BASE_URL;
import static java.text.MessageFormat.format;

public class PostShiftEmailAccount implements EmailAccount {
    public static final String DOMAIN = "@post-shift.ru";
    private final Request request;
    private final CreatedAccount createdAccount;
    private Email[] emails;

    PostShiftEmailAccount(CreatedAccount createdAccount){
        request = new Request();
        System.out.println(createdAccount.getKey() + " keyyyyyy"); //todo remove
        this.createdAccount = createdAccount;
    }

    @Override
    public Email[] getEmails() {
        String api = format("{0}?action=getlist&key={1}&type=json",  BASE_URL, createdAccount.getKey());
        emails = request.makeGetRequest(api, Email[].class);
        return emails;
    }

    private Email waitToReseiveEmail(String sender, int seconds) throws NoSuchEmail {
        int delayInMilis = 5000;
        int secondsCounter = 0;
        Email email = null;

        while (seconds * 1000 > secondsCounter) {
            try {
                secondsCounter += delayInMilis;
                Thread.sleep(delayInMilis);
                email = filterEmailsBySender(sender);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NoSuchEmail noSuchEmail) {
                noSuchEmail.printStackTrace();
            }
        }
        if (email == null)
            throw new NoSuchEmail("Wait " + seconds + " seconds, but could not find email from " + sender);
        return email;
    }

    private Email filterEmailsBySender(String sender) throws NoSuchEmail {
        return Stream.of(getEmails())
                .filter(email -> email.getFrom().contains(sender))
                .findAny()
                .orElseThrow(NoSuchEmail::new);
    }

    private String getTextOfEmail(Email email){
        String api =
                format("{0}?action=getmail&key={1}&id={2}",  BASE_URL, createdAccount.getKey(), email.getId());
        return request.makeGetRequest(api, String.class);
    }

    public Email searchForEmailFromSender(String sender) throws NoSuchEmail {
        Email email = waitToReseiveEmail(sender, 30);
        email.setMessage(getTextOfEmail(email));
        return email;
    }

    public void deleteAccount() {
        String api = format("{0}?action=delete&key={1}",  BASE_URL, createdAccount.getKey());
        request.makeGetRequest(api, String.class);
    }
}
