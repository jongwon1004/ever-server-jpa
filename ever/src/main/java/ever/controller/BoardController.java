package ever.controller;

import ever.dto.query.BoardListQueryDetails;
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

    @GetMapping(value = {"/{languageTypeName}", "/{languageTypeName}/{language}"})
    public ResponseEntity<Map<String, Object>> BoardList(@PathVariable("languageTypeName") String languageTypeName,
                                                         @PathVariable("language") String language,
                                                         @RequestParam Integer page,
                                                         @RequestParam(value = "search") String search) {

        Integer offset = (page - 1) * 9;

        BoardListQueryDetails queryDetails = new BoardListQueryDetails(languageTypeName, language, page, search, offset);



        return ResponseEntity.ok(Collections.singletonMap("hello", "hello"));
    }

}
