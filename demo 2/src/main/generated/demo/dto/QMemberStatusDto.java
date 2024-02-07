package demo.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * demo.dto.QMemberStatusDto is a Querydsl Projection type for MemberStatusDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberStatusDto extends ConstructorExpression<MemberStatusDto> {

    private static final long serialVersionUID = 592507993L;

    public QMemberStatusDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name) {
        super(MemberStatusDto.class, new Class<?>[]{long.class, String.class}, id, name);
    }

}

