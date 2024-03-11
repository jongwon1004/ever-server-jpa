package ever.service;

import ever.dto.BoardListDto;
import ever.dto.query.BoardListQueryDetails;
import ever.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Map<String, Object> getBoardList(String languageTypeName, String language, Integer page, String search) {

        Integer offset = (page - 1) * 9;
        BoardListQueryDetails queryDetails = new BoardListQueryDetails(languageTypeName, language, page, search, offset);

        Map<String, Object> response = new HashMap<>();
        response.put("boardResult", boardRepository.getBoardList(queryDetails));
        response.put("totalBoardCnt", boardRepository.getBoardCnt(queryDetails));

        return response;
    }
}
