package demo.email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailContent {
    private String from;
    private String subject;
    private String text;

    // 메일 본문 구성을 위한 메서드
    public static EmailContent createEmailContent(String certificationNumber) {
        EmailContent emailContent = new EmailContent();
        emailContent.setFrom("teamevergp@gmail.com");
        emailContent.setSubject("【　Ever　】 新規会員登録 ");
        emailContent.setText("※本メールに心当たりの無い方は、本メールの破棄をお願いいたします。\n\n"
                + "いつもEver.eccをご利用いただき、誠にありがとうございます。\n\n"
                + "認証コードをお知らせします。\n"
                + "───────────────────────────────────\n"
                + "■認証コード通知■\n"
                + "───────────────────────────────────\n"
                + "認証コード ：  「   " + certificationNumber + "   」 \n"
                + "───────────────────────────────────\n\n"
                + "上記のコードを認証画面にご入力ください。");
        return emailContent;
    }
}

