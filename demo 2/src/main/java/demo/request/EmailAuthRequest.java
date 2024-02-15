package demo.request;


import demo.email.EmailContent;
import lombok.*;

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

    public void setCertificationNumber(String certificationNumber) {
        this.certificationNumber = certificationNumber;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
