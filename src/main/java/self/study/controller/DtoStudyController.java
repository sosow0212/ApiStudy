package self.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import self.study.entity.dto.RequestDto;
import self.study.service.RequestService;

@RestController
@RequiredArgsConstructor
public class DtoStudyController {

    private final RequestService requestService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(requestService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody RequestDto requestDto) {
        return new ResponseEntity<>(requestService.save(requestDto) , HttpStatus.OK);
    }
}
