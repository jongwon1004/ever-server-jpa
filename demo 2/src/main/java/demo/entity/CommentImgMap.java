package demo.entity;

import demo.entity.middle.CommentImgMapId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "COMMENT_IMG_MAP")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentImgMap {

    @EmbeddedId
    private CommentImgMapId id = new CommentImgMapId();

    @MapsId("commentNo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentNo")
    private Comment comment;


    @MapsId("imageNo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imageNo")
    private Image image;
}
