package demo.exception;

public class MailSendException extends RuntimeException{

    private final String errorMessage;

    public MailSendException(String errorMessage) {
        super("mailSendException");
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
