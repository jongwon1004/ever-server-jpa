package ever.entity.middle;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardImgMapId is a Querydsl query type for BoardImgMapId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBoardImgMapId extends BeanPath<BoardImgMapId> {

    private static final long serialVersionUID = -1274156484L;

    public static final QBoardImgMapId boardImgMapId = new QBoardImgMapId("boardImgMapId");

    public final NumberPath<Long> globalBoardNo = createNumber("globalBoardNo", Long.class);

    public final NumberPath<Long> imageNo = createNumber("imageNo", Long.class);

    public QBoardImgMapId(String variable) {
        super(BoardImgMapId.class, forVariable(variable));
    }

    public QBoardImgMapId(Path<? extends BoardImgMapId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardImgMapId(PathMetadata metadata) {
        super(BoardImgMapId.class, metadata);
    }

}

