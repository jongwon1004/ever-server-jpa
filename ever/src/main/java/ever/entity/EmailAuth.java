package ever.entity;

import ever.enums.StatusType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "EMAILAUTH")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailAuth {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emailAuthNo")
    private Long id;

    private String email;

    @Column(length = 6)
    private String certificationNumber;

    private LocalDateTime regDate;
    private LocalDateTime expireDate;

    @Enumerated(value = EnumType.STRING)
    private StatusType statusType;

    @Builder
    public EmailAuth(String email, String certificationNumber, LocalDateTime regDate, LocalDateTime expireDate, StatusType statusType) {
        this.email = email;
        this.certificationNumber = certificationNumber;
        this.regDate = regDate;
        this.expireDate = expireDate;
        this.statusType = statusType;
    }
}
