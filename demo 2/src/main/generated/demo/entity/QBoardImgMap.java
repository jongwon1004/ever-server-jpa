package demo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardImgMap is a Querydsl query type for BoardImgMap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardImgMap extends EntityPathBase<BoardImgMap> {

    private static final long serialVersionUID = 2025554271L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardImgMap boardImgMap = new QBoardImgMap("boardImgMap");

    public final QBoard board;

    public final demo.entity.middle.QBoardImgMapId id;

    public final QImage image;

    public QBoardImgMap(String variable) {
        this(BoardImgMap.class, forVariable(variable), INITS);
    }

    public QBoardImgMap(Path<? extends BoardImgMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardImgMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardImgMap(PathMetadata metadata, PathInits inits) {
        this(BoardImgMap.class, metadata, inits);
    }

    public QBoardImgMap(Class<? extends BoardImgMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.id = inits.isInitialized("id") ? new demo.entity.middle.QBoardImgMapId(forProperty("id")) : null;
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image")) : null;
    }

}

