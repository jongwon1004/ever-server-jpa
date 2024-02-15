package ever.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserClass is a Querydsl query type for UserClass
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserClass extends EntityPathBase<UserClass> {

    private static final long serialVersionUID = -73244974L;

    public static final QUserClass userClass = new QUserClass("userClass");

    public final StringPath className = createString("className");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QUserClass(String variable) {
        super(UserClass.class, forVariable(variable));
    }

    public QUserClass(Path<? extends UserClass> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserClass(PathMetadata metadata) {
        super(UserClass.class, metadata);
    }

}

