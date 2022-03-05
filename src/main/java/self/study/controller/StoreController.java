package self.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import self.study.entity.Store;
import self.study.service.StoreService;

@RestController
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/")
    public ResponseEntity<?> view() {
        return new ResponseEntity<>(storeService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/api/upload")
    public ResponseEntity<?> upload(@RequestBody Store store) {
        return new ResponseEntity<>(storeService.save(store), HttpStatus.CREATED);
    }

    @GetMapping("/api")
    public ResponseEntity<?> testw() {
        return new ResponseEntity<>(storeService.findAll(), HttpStatus.BAD_GATEWAY);
    }
}
