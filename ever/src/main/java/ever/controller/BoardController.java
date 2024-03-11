package ever.controller;

import ever.dto.query.BoardListQueryDetails;
import ever.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(value = "/api/category")
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = {"/{languageTypeName}", "/{languageTypeName}/{language}"})
    public ResponseEntity<Map<String, Object>> BoardList(@PathVariable("languageTypeName") String languageTypeName,
                                                         @PathVariable(value = "language", required = false) String language,
                                                         @RequestParam Integer page,
                                                         @RequestParam(value = "search", required = false) String search) {

        Integer offset = (page - 1) * 9;

        Map<String, Object> result = boardService.getBoardList(languageTypeName, language, page, search);


        return ResponseEntity.ok(result);
    }

}
