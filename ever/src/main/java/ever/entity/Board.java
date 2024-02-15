package ever.entity;

import ever.enums.StatusType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "globalBoardNo")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "languageNo")
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writerNo")
    private Member member;

    private String title;
    private String content;


    private int viewCount;
    private int commentCount;

    @Enumerated(value = EnumType.STRING)
    private StatusType status;

    @OneToMany(mappedBy = "board")
    private List<BoardImgMap> boardImgMaps = new ArrayList<>();

    public Board(Language language, Member member, String title, String content) {
        this(language, member, title, content, 0, 0, StatusType.ACTIVE);
    }

    public Board(Language language, Member member, String title, String content, int viewCount, int commentCount, StatusType status) {
        this.language = language;
        this.member = member;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", language=" + language.getLanguageName() +
                ", member.getId()=" + member.getName() +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", viewCount=" + viewCount +
                ", commentCount=" + commentCount +
                ", status=" + status +
                '}';
    }
}
