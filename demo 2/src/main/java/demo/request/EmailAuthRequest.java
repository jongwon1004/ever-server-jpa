package demo.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
@Getter
public class EmailAuthRequest {

    private String email;
    private String certificationNumber;
    private LocalDateTime regDate;
    private LocalDateTime expireDate;
    private Boolean status;

}
