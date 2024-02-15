package ever.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QImage is a Querydsl query type for Image
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImage extends EntityPathBase<Image> {

    private static final long serialVersionUID = -1704620480L;

    public static final QImage image = new QImage("image");

    public final QTimeBaseEntity _super = new QTimeBaseEntity(this);

    public final ListPath<BoardImgMap, QBoardImgMap> boardImgMaps = this.<BoardImgMap, QBoardImgMap>createList("boardImgMaps", BoardImgMap.class, QBoardImgMap.class, PathInits.DIRECT2);

    public final StringPath clientFilename = createString("clientFilename");

    public final NumberPath<Integer> entityId = createNumber("entityId", Integer.class);

    public final EnumPath<ever.enums.EntityType> entityType = createEnum("entityType", ever.enums.EntityType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath serverFilename = createString("serverFilename");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QImage(String variable) {
        super(Image.class, forVariable(variable));
    }

    public QImage(Path<? extends Image> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImage(PathMetadata metadata) {
        super(Image.class, metadata);
    }

}

