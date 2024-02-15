package ever.entity;

import ever.entity.middle.BoardImgMapId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 識別子クラス
 */
@Entity
@Getter
@Table(name = "BOARD_IMG_MAP")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardImgMap {

    @EmbeddedId
    private BoardImgMapId id = new BoardImgMapId();

    /**
     * MapsId ?
     * 複合機を使うエンティティで、識別子をマッピングする時に使用
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
