package demo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotificationType is a Querydsl query type for NotificationType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotificationType extends EntityPathBase<NotificationType> {

    private static final long serialVersionUID = -1583597947L;

    public static final QNotificationType notificationType = new QNotificationType("notificationType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath notificationTypeName = createString("notificationTypeName");

    public QNotificationType(String variable) {
        super(NotificationType.class, forVariable(variable));
    }

    public QNotificationType(Path<? extends NotificationType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotificationType(PathMetadata metadata) {
        super(NotificationType.class, metadata);
    }

}

