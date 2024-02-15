package ever.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private String loginResult;

    @JsonProperty("isLoggedIn")
    private Boolean isLoggedIn;
}
