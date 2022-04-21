package self.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import self.study.entity.Body;
import self.study.service.BodyService;

@RequiredArgsConstructor
@RestController
public class BodyController {

    private final BodyService bodyService;

    @GetMapping("/api/body")
    public ResponseEntity<?> loadAllBodyInfo() {
        return new ResponseEntity<>(bodyService.loadAllBodyInfo(), HttpStatus.OK);
    }

    @GetMapping("/api/body/{id}")
    public ResponseEntity<?> loadUserBodyInfo(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(bodyService.loadUserBodyInfo(id), HttpStatus.OK);
    }

    @PostMapping("/api/body/save")
    public ResponseEntity<?> saveBodyInfo(@RequestBody Body body) {
        return new ResponseEntity<>(bodyService.saveBodyInfo(body), HttpStatus.OK);
    }

    // 확인 필요
    @PutMapping("/api/body/{id}/edit")
    public ResponseEntity<?> editBodyInfo(@RequestBody Body newBody, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(bodyService.updateBody(id, newBody), HttpStatus.OK);
    }

    @DeleteMapping("/api/body/{id}/delete")
    public ResponseEntity<?> deleteBodyInfo(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(bodyService.deleteBodyInfo(id), HttpStatus.OK);
    }
}
