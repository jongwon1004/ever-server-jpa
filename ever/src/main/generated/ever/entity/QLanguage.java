package ever.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLanguage is a Querydsl query type for Language
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLanguage extends EntityPathBase<Language> {

    private static final long serialVersionUID = 590694483L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLanguage language = new QLanguage("language");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath languageName = createString("languageName");

    public final QLanguageType languageType;

    public QLanguage(String variable) {
        this(Language.class, forVariable(variable), INITS);
    }

    public QLanguage(Path<? extends Language> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLanguage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLanguage(PathMetadata metadata, PathInits inits) {
        this(Language.class, metadata, inits);
    }

    public QLanguage(Class<? extends Language> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.languageType = inits.isInitialized("languageType") ? new QLanguageType(forProperty("languageType")) : null;
    }

}

