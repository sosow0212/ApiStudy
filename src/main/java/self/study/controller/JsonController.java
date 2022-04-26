package self.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import self.study.entity.Board;
import self.study.entity.dto.ControllerMessage;
import self.study.service.BoardService;

@RequiredArgsConstructor
@RestController
public class JsonController {

    public final BoardService boardService;

    // Board형으로 전달
    @GetMapping("/api/v1")
    public Board apiV1() {
        Board board = new Board();
        board.setId(1);
        board.setTitle("제목");
        board.setContent("내용");
        return board;
    }

    // ResponseEntity
    @GetMapping("/api/v2")
    public ResponseEntity apiV2() {
        return new ResponseEntity(boardService.findById(1), HttpStatus.OK);
    }


    // Message Object
    @GetMapping("/api/v3")
    public ControllerMessage apiV3() {
        return new ControllerMessage(boardService.findById(1), "성공!", HttpStatus.OK);
    }

}
