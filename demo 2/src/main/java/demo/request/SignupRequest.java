package demo.request;


import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;


/*
    {
        "name" : "崔 鐘元",
        "studentNumber" : "2220019",
        "loginId" : "whddnjs3340",
        "nickname" : "johnny",
        "email" : "whddnjs3340@naver.com",
        "password" : "dnflwlq1408",
        "confirmPassword" : "dnflwlq1408",
        "className" : "SK2A"
    }
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignupRequest {

    private Integer userNo;

    @NotBlank(message = "名前を入力してください")
    @Size(min = 3, max=20, message="名前は3〜20文字以内にしてください")
    @Pattern(regexp = "^[^0-9\\s,]+$", message = "数字、または空白を入れることはできません")
    private String name;

    @NotNull(message = "７桁の学生番号を入力してください")
    @Size(min = 7, max = 7, message = "７桁の学生番号を入力してください")
    @Pattern(regexp = "\\d{7}", message = "学生番号は数字のみを含む７桁である必要があります")
    private String studentNumber;

    @NotBlank(message = "ログインIDを入力してください")
    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z\\d]+$", message = "ログインIDには少なくとも一つの英字が含まれている必要があり、特殊文字を含むことはできません")
    @Size(min=7, max=18, message="ログインIDは７〜１８桁以内にしてください")
    private String loginId;

    @NotBlank(message = "ニックネームを入力してください")
    @Size(min = 2, max = 18, message = "ニックネームは２〜１８文字桁以内にしてください")
    @Pattern(regexp = "^(?!\\d+$).+$", message = "ニックネームには数字のみ、または空白を入れることはできません")
    private String nickname;

    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "メール形式ではありません")
    private String email;

    @NotBlank(message = "パスワードを入力してください")
    @Size(min = 7, max = 18, message = "パスワードは７〜１８文字以内にしてください")
    @Pattern(regexp = "^(?=.*[a-zA-Z]).{7,18}$", message = "パスワードには少なくとも一つの英字を含む必要があります")
    private String password;

    //    @NotBlank(message = "確認用パスワードを入力してください")
    private String confirmPassword;

    @NotBlank(message = "クラスを入力してください") // 이거 요청 json 키값 className으로 받아야됨
    private String className;   // 입력받을때는 이름으로 받고 이름으로 DB조회
    // 여기 위에 원래는 major 로 요청 json 키값을 받았는데 className으로 고쳐야됨

    private String interest;

    private LocalDateTime regDate;
}
