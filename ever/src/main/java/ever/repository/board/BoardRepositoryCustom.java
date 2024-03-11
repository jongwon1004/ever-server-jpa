package ever.repository.board;

import ever.dto.BoardListDto;
import ever.dto.query.BoardListQueryDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Page<BoardListDto> getBoardListAndCnt(BoardListQueryDetails queryDetails);
}
