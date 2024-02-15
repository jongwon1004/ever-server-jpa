package demo.exception;

public class UserClassNotFoundException extends RuntimeException {

    private final String errorMessage;

    public UserClassNotFoundException(String errorMessage) {
        super("UserClass cannot be found");
        this.errorMessage = errorMessage;
    }

    public String getErrorMessages() {
        return errorMessage;
    }
}
