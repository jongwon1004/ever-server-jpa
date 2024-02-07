package demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@ToString
public class LoginRequest {

    private String userId;
    private String userPass;

//    @ConstructorProperties({"userId", "userPass"})
    public LoginRequest(String userId, String userPass) {
        this.userId = userId;
        this.userPass = userPass;
    }

}
