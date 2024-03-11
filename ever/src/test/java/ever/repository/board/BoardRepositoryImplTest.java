package ever.repository.board;

import ever.dto.query.BoardListQueryDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@Transactional
class BoardRepositoryImplTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void test() {
        BoardListQueryDetails boardListQueryDetails = new BoardListQueryDetails("frontend", "react",1,null,1);
        boardRepository.getBoardListAndCnt(boardListQueryDetails);
    }


}