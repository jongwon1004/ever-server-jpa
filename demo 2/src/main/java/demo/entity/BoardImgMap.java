package demo.entity;

import demo.entity.middle.BoardImgMapId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 식별자 클래스
 */
@Entity
@Getter
@Table(name = "BOARD_IMG_MAP")
@NoArgsConstructor
@AllArgsConstructor
public class BoardImgMap {

    @EmbeddedId
    private BoardImgMapId id = new BoardImgMapId();

    /**
     * MapsId ?
     * 복합 키를 사용하는 엔티티에서 식별자를 매핑할때 사용
     */
    @MapsId("globalBoardNo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "globalBoardNo")
    private Board board;

    @MapsId("imageNo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imageNo")
    private Image image;
}
