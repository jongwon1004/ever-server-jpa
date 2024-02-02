package demo.entity;

import demo.enums.StatusType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "EMAILAUTH")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailAuth {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emailAuthNo")
    private Long id;

    @Column(length = 6)
    private String certificationNumber;

    private LocalDateTime regDate;
    private LocalDateTime expireDate;

//    @Enumerated(value = EnumType.STRING)
//    private StatusType statusType;


}
