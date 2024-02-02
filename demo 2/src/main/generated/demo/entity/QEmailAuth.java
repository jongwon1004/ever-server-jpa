package demo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmailAuth is a Querydsl query type for EmailAuth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmailAuth extends EntityPathBase<EmailAuth> {

    private static final long serialVersionUID = 412323748L;

    public static final QEmailAuth emailAuth = new QEmailAuth("emailAuth");

    public final StringPath certificationNumber = createString("certificationNumber");

    public final DateTimePath<java.time.LocalDateTime> expireDate = createDateTime("expireDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public QEmailAuth(String variable) {
        super(EmailAuth.class, forVariable(variable));
    }

    public QEmailAuth(Path<? extends EmailAuth> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmailAuth(PathMetadata metadata) {
        super(EmailAuth.class, metadata);
    }

}

