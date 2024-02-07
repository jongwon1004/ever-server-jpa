package demo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentImgMap is a Querydsl query type for CommentImgMap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentImgMap extends EntityPathBase<CommentImgMap> {

    private static final long serialVersionUID = 878457368L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentImgMap commentImgMap = new QCommentImgMap("commentImgMap");

    public final QComment comment;

    public final demo.entity.middle.QCommentImgMapId id;

    public final QImage image;

    public QCommentImgMap(String variable) {
        this(CommentImgMap.class, forVariable(variable), INITS);
    }

    public QCommentImgMap(Path<? extends CommentImgMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentImgMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentImgMap(PathMetadata metadata, PathInits inits) {
        this(CommentImgMap.class, metadata, inits);
    }

    public QCommentImgMap(Class<? extends CommentImgMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new QComment(forProperty("comment"), inits.get("comment")) : null;
        this.id = inits.isInitialized("id") ? new demo.entity.middle.QCommentImgMapId(forProperty("id")) : null;
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image")) : null;
    }

}

