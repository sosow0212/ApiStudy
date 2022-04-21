package self.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import self.study.service.BodyService;

@RequiredArgsConstructor
@RestController
public class BodyController {

    private final BodyService bodyService;

    @GetMapping("/api/body")
    public ResponseEntity<?> loadUsersBodyInfo() {
        return new ResponseEntity<>(bodyService.loadUsersBodyInfo(), HttpStatus.OK);
    }
}
