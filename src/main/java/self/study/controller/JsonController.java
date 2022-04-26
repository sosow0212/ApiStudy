package self.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import self.study.entity.Board;
import self.study.entity.dto.APIMessage;
import self.study.entity.dto.BoardDto;
import self.study.entity.dto.ControllerMessage;
import self.study.entity.dto.StatusEnum;
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
        return new ResponseEntity(boardService.findById(1), HttpStatus.BAD_REQUEST);
    }


    // Message Object
    @GetMapping("/api/v3")
    public ControllerMessage apiV3() {
        return new ControllerMessage(HttpStatus.OK, "성공!", boardService.findById(1));
    }


    // ResponseEntity + Message.
    @GetMapping("/api/v4")
    public ResponseEntity<?> apiV4() {
        return new ResponseEntity<>(new ControllerMessage(HttpStatus.BAD_GATEWAY, "성공", boardService.findById(1)), HttpStatus.BAD_GATEWAY);
    }

    // ResponseEntity + Message2
    @GetMapping("/api/v5")
    public ResponseEntity<?> apiV5() {
        return new ResponseEntity<>(new ControllerMessage(HttpStatus.OK, "데이터 반환", boardService.findAll()), HttpStatus.OK);
    }


    @GetMapping("/api/v6")
    public APIMessage apiV6() {
        return new APIMessage(HttpStatus.OK, "성공", boardService.findAll());
    }

}
