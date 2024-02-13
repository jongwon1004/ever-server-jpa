package demo.exception;

import java.util.List;

public class MemberSignupException extends RuntimeException {

    private final List<String> errorMessages;

    public MemberSignupException(List<String> errorMessages) {
        super("signup errors");
        this.errorMessages = getErrorMessages();
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
