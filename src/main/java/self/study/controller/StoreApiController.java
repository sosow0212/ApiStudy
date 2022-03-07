package self.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import self.study.entity.Store;
import self.study.entity.dto.Message;
import self.study.entity.dto.StatusEnum;
import self.study.service.StoreService;

import java.util.List;

@RestController
public class StoreApiController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/")
    public ResponseEntity<?> view() {
        return new ResponseEntity<>(storeService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/api/upload")
    public ResponseEntity<?> upload(@RequestBody Store store) {
        if(store.getTitle().equals("에러유발")) {
            System.out.println("작동됨");
            return new ResponseEntity<>("에러 발생 in controller", HttpStatus.INTERNAL_SERVER_ERROR);
            // 실행 될 경우, JS에서 ajax.error()가 터진다.
        }
        return new ResponseEntity<>(storeService.save(store), HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<?> testFunc() {
        // ResponseEntity<>(Data, headers(생략가능), 상태코드)
        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(storeService.findAll());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
