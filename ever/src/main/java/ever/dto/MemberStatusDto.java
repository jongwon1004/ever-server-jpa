package ever.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberStatusDto {

    private Long id;
    private String name;
    private String studentNumber;
    private String loginId;
    private String nickname;
    private String email;
    private String className;
    private String interest;
    private String introduction;
    private int exp;
    private String profileImage;

}
