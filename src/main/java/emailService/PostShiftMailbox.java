package emailService;

import request.Request;

import static java.text.MessageFormat.format;

public class PostShiftMailbox implements Mailbox {
    public static final String BASE_URL = "https://post-shift.ru/api.php";
    private static Request request;

    public EmailAccount createTempAccount(String name){
        request = new Request();
        return createAccountInMailbox(name);
    }

    private static EmailAccount createAccountInMailbox(String name) {
        String api = format("{0}?action=new&name={1}&type=json",  BASE_URL, name);
        CreatedAccount createdAccount = request.makeGetRequest(api, CreatedAccount.class);
        return new PostShiftEmailAccount(createdAccount);
    }
}