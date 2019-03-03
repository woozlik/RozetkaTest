package emailService;

public class NoSuchEmail extends Exception {
    public NoSuchEmail(){
        super();
    }

    public NoSuchEmail(String message){
        super(message);
    }
}
