package ever.exception;

import java.util.List;
import java.util.Map;

public class MemberSignupException extends RuntimeException {

    private final Map<String, String> errorMessages;

    public MemberSignupException(Map<String, String> errorMessages) {
        super("signup errors");
        this.errorMessages = errorMessages;
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }
}
