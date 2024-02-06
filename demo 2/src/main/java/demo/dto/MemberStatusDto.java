package demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MemberStatusDto {

    private Long id;
    private String name;
    private String studentNumber;
    private String loginId;
    private String nickname;
    private String email;
    private String password;
    private Integer classNo;
    private String interest;
    private String introduction;
    private int exp;
    private String profileImage;

    @QueryProjection
    public MemberStatusDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
