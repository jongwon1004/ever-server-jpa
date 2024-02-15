package ever.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExpHistory is a Querydsl query type for ExpHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExpHistory extends EntityPathBase<ExpHistory> {

    private static final long serialVersionUID = 596847090L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExpHistory expHistory = new QExpHistory("expHistory");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final StringPath reason = createString("reason");

    public QExpHistory(String variable) {
        this(ExpHistory.class, forVariable(variable), INITS);
    }

    public QExpHistory(Path<? extends ExpHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExpHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExpHistory(PathMetadata metadata, PathInits inits) {
        this(ExpHistory.class, metadata, inits);
    }

    public QExpHistory(Class<? extends ExpHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

