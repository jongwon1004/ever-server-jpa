package ever.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member extends TimeBaseEntity implements UserDetails {

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

    @Builder
    public Member(String name, String studentNumber, String loginId, String nickname, String email, String password, UserClass userClass, String interest, String profileImage) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.loginId = loginId;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.userClass = userClass;
        this.interest = interest;
        this.profileImage = profileImage;
    }


    @Override // 권한 반환 ( 사용자가 가지고 있는 권한 리스트 )
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override // 사용자를 식별할 수 있는 사용자 이름 반환. 사용되는 값은 반드시 고유해야함.
    public String getUsername() {
        return email;
    }

    @Override // 계정이 만료되었는지 확인. 만료되지 않았을 시 true 반환
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override // 계정이 잠금되었는지 확인. 잠금되지 않았을 시 true 반환
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // 비밀번호가 만료되었는지 확인. 만료되지 않았을 시 true 반환
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // 계정이 사용가능한지 확인. 사용 가능할 시 true 반환
    public boolean isEnabled() {
        return true;
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
