package demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@Getter
public class Member extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;
    @Column(length = 7)
    private String studentNumber;
    @Column(length = 18)
    private String loginId;
    @Column(length = 18)
    private String nickname;
    @Column(length = 30)
    private String email;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classNo", referencedColumnName = "classNo") // 단방향, ToString OK
    private UserClass userClass;

    @Column(length = 30)
    private String interest;
    private String introduction;
    private int exp;
    private String profileImage;

    public Member(String name, String studentNumber, String loginId, String nickname, String email, String password, UserClass userClass) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.loginId = loginId;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.userClass = userClass;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", loginId='" + loginId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userClass=" + userClass.getClassName() +
                ", interest='" + interest + '\'' +
                ", introduction='" + introduction + '\'' +
                ", exp=" + exp +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}
