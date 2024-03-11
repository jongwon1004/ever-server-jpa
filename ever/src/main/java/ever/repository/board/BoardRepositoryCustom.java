package ever.repository.board;

import ever.dto.BoardListDto;
import ever.dto.query.BoardListQueryDetails;

import java.util.List;

public interface BoardRepositoryCustom {
    List<BoardListDto> getBoardList(BoardListQueryDetails queryDetails);

    Long getBoardCnt(BoardListQueryDetails queryDetails);
}
