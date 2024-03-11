package ever.repository.board;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ever.dto.BoardListDto;
import ever.dto.query.BoardListQueryDetails;
import ever.entity.*;
import ever.enums.StatusType;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ever.entity.QBoard.*;
import static ever.entity.QComment.comment;
import static ever.entity.QLanguage.*;
import static ever.entity.QLanguageType.*;
import static ever.entity.QMember.*;

@Repository
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    public List<BoardListDto> getBoardList(BoardListQueryDetails queryDetails) {


        // notice select
        List<Tuple> noticeList = queryFactory
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
                .join(board.language.languageType, languageType)
                .join(board.member, member)
                .where(language.id.eq(0L))
                .orderBy(board.regDate.desc())
                .limit(3)
                .fetch();

        noticeList.forEach(System.out::println);

        BooleanExpression condition = languageType.languageTypeName.eq(queryDetails.getLanguageTypeName());

        if (queryDetails.getLanguageName() != null) {
            condition.and(language.languageName.eq(queryDetails.getLanguageName()));
        }

        if (queryDetails.getSearch() != null) {
            condition = condition.and(
                    board.title.contains(queryDetails.getSearch())
                            .or(member.nickname.contains(queryDetails.getSearch()))
                            .or(board.content.contains(queryDetails.getSearch()))
            );
        }


        List<Tuple> boardList = queryFactory
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
                .join(board.language.languageType, languageType)
                .join(board.member, member)
                .where(condition)
                .orderBy(board.regDate.desc())
                .limit(9)
                .offset(queryDetails.getOffset())
                .fetch();

        List<Tuple> combinedResults = new ArrayList<>();
        combinedResults.addAll(noticeList); // notice
        combinedResults.addAll(boardList);

        return combinedResults
                .stream()
                .map(tuple -> {
                    Long commentCount = tuple.get(6, Long.class);
                    int commentCnt = commentCount == null ? 0 : commentCount.intValue();

                    return new BoardListDto(
                            tuple.get(board.id),
                            tuple.get(language.id),
                            tuple.get(member.id),
                            tuple.get(board.title),
                            tuple.get(board.content),
                            tuple.get(board.viewCount),
                            commentCnt,
                            tuple.get(board.regDate),
                            tuple.get(board.updateDate),
                            tuple.get(board.language.languageName),
                            tuple.get(member.nickname)
                    );
                }).collect(Collectors.toList());
    }

    @Override
    public Long getBoardCnt(BoardListQueryDetails queryDetails) {

        BooleanExpression condition = languageType.languageTypeName.eq(queryDetails.getLanguageTypeName());

        if (queryDetails.getLanguageName() != null) {
            condition.and(language.languageName.eq(queryDetails.getLanguageName()));
        }

        if (queryDetails.getSearch() != null) {
            condition = condition.and(
                    board.title.contains(queryDetails.getSearch())
                            .or(member.nickname.contains(queryDetails.getSearch()))
                            .or(board.content.contains(queryDetails.getSearch()))
            );
        }

        return queryFactory
                .select(Expressions.numberTemplate(Long.class, "ceil({0}/9)", board.count()))
                .from(board)
                .join(board.language, language)
                .join(board.language.languageType, languageType)
                .join(board.member, member)
                .where(condition)
                .fetchOne();
    }
}
