package demo.entity.middle;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommentImgMapId is a Querydsl query type for CommentImgMapId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCommentImgMapId extends BeanPath<CommentImgMapId> {

    private static final long serialVersionUID = 507012666L;

    public static final QCommentImgMapId commentImgMapId = new QCommentImgMapId("commentImgMapId");

    public final NumberPath<Long> commentNo = createNumber("commentNo", Long.class);

    public final NumberPath<Long> imageNo = createNumber("imageNo", Long.class);

    public QCommentImgMapId(String variable) {
        super(CommentImgMapId.class, forVariable(variable));
    }

    public QCommentImgMapId(Path<? extends CommentImgMapId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommentImgMapId(PathMetadata metadata) {
        super(CommentImgMapId.class, metadata);
    }

}

