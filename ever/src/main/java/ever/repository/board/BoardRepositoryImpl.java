package ever.repository.board;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ever.dto.BoardListDto;
import ever.dto.query.BoardListQueryDetails;
import ever.enums.StatusType;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

import static ever.entity.QBoard.*;
import static ever.entity.QComment.comment;
import static ever.entity.QLanguage.*;
import static ever.entity.QLanguageType.*;
import static ever.entity.QMember.*;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    public Page<BoardListDto> getBoardListAndCnt(BoardListQueryDetails queryDetails) {

        BooleanExpression condition = languageType.languageTypeName.eq(queryDetails.getLanguageTypeName());

        if (queryDetails.getLanguageName() != null) {
            condition.and(language.languageName.eq(queryDetails.getLanguageName()));
        }

        List<Tuple> results = queryFactory
                .select(board.id,
                        language.id,
                        member.id,
                        board.title,
                        board.content,
                        board.viewCount,
                        JPAExpressions
                                .select(comment.count().as("commentCnt"))
                                .from(comment)
                                .where(comment.board.id.eq(board.id)),
                        board.regDate,
                        board.updateDate,
                        board.language.languageName,
                        member.nickname)
                .from(board)
                .join(board.language, language)
                .join(language.languageType, languageType)
                .join(board.member, member)
                .where(condition)
                .fetch();

        List<BoardListDto> dtos = results
                .stream()
                .map(tuple -> {
                    return new BoardListDto(
                            tuple.get(board.id),
                            tuple.get(language.id),
                            tuple.get(member.id),
                            tuple.get(board.title),
                            tuple.get(board.content),
                            tuple.get(board.viewCount),
                            tuple.get(6, Long.class).intValue(), // commentCount 서브쿼리 결과를 int 로 변환
                            tuple.get(board.regDate),
                            tuple.get(board.updateDate),
                            tuple.get(board.language.languageName),
                            tuple.get(member.nickname)
                    );
                }).collect(Collectors.toList());

        System.out.println(dtos);
        return null;

    }
}
