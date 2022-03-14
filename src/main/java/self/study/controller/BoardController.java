package self.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import self.study.entity.Board;
import self.study.service.BoardService;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board")
    public ResponseEntity<?> viewAllBoard() {
        return new ResponseEntity<>(boardService.findAll(), HttpStatus.OK);
    }


    @PostMapping("/board/write")
    public ResponseEntity<?> writeBoard(@RequestBody Board board) {
        return new ResponseEntity<>(boardService.writeBoard(board), HttpStatus.OK);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<?> findBoardById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(boardService)
    }


}
