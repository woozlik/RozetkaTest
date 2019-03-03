package emailService;

final public class Email {
    private String date;
    private String subject;
    private String from;
    private String id;
    private String message;

    private Email(){
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public String getId() {
        return id;
    }
}