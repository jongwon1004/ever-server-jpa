package ever.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardListDto {
    private Long globalBoardNo;
    private Long languageNo;
    private Long writerNo;
    private String title;
    private String content;
    private int viewCnt;
    private int commentCnt;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String languageName;
    private String writerName;
}

/*
    SELECT cb.globalBoardNo, cb.languageNo, cb.writerNO, cb.title,
        cb.content, cb.viewCnt, (SELECT COUNT(*) FROM comment WHERE globalBoardNo = cb.globalBoardNo) AS commentCnt,
        cb.regDate, cb.updateDate,
        l.languageName, u.nickname AS writerName
        FROM communityBoard cb
        JOIN language l ON cb.languageNo = l.languageNo
        JOIN languageType lt ON l.languageTypeNo = lt.languageTypeNo
        JOIN users u on cb.writerNO = u.userNo
        WHERE cb.status = 'ACTIVE' AND lt.languageTypeName = #{languageTypeName}
        <if test="languageName != null">
            AND l.languageName = #{languageName}
        </if>
        <if test="search != null">
            AND (cb.title LIKE CONCAT('%', #{search}, '%')
            OR u.nickname LIKE CONCAT('%', #{search}, '%')
            OR cb.content LIKE CONCAT('%', #{search}, '%'))
        </if>
        ORDER BY cb.regDate DESC
        LIMIT 9
        OFFSET #{offset}
 */