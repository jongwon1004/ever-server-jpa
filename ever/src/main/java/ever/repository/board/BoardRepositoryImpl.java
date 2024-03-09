package ever.repository.board;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ever.dto.BoardListDto;
import ever.dto.query.BoardListQueryDetails;
import ever.entity.QBoard;
import ever.entity.QLanguage;
import ever.entity.QLanguageType;
import ever.entity.QMember;
import ever.enums.StatusType;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    public Page<BoardListDto> getBoardListAndCnt(BoardListQueryDetails queryDetails, Pageable pageable) {

        JPQLQuery<Long> commentCountSubQuery = JPAExpressions
                .select(comment.count())
                .from(comment)
                .where(comment.board.id.eq(board.id));

        queryFactory.select(
                        Projections.fields(BoardListDto.class,
                                board.id.as("globalBoardNo"),
                                language.id.as("languageNo"),
                                member.id.as("writerNo"),
                                board.title,
                                board.content,
                                board.viewCount.as("viewCnt"),
                                board.commentCount.as("commentCnt"),
                                board.regDate,
                                board.updateDate,
                                board.language.languageName,
                                member.nickname.as("writerName")

                        )
                ).from(board)
                .join(board.language, language)
                .join(language.languageType, languageType)
                .join(board.member, member)
                .where(board.status.eq(StatusType.ACTIVE),
                        languageType.languageTypeName.eq(queryDetails.getLanguageTypeName()));



    }
}
