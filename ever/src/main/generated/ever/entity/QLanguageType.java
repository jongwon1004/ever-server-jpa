package ever.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLanguageType is a Querydsl query type for LanguageType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLanguageType extends EntityPathBase<LanguageType> {

    private static final long serialVersionUID = 2081090093L;

    public static final QLanguageType languageType = new QLanguageType("languageType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Language, QLanguage> languages = this.<Language, QLanguage>createList("languages", Language.class, QLanguage.class, PathInits.DIRECT2);

    public final StringPath languageTypeName = createString("languageTypeName");

    public QLanguageType(String variable) {
        super(LanguageType.class, forVariable(variable));
    }

    public QLanguageType(Path<? extends LanguageType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLanguageType(PathMetadata metadata) {
        super(LanguageType.class, metadata);
    }

}

