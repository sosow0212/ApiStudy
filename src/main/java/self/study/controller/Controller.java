package self.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import self.study.entity.User;
import self.study.service.UserService;

@RequiredArgsConstructor
@RestController
public class Controller {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<?> userList() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> userView(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.findUser(id), HttpStatus.OK);
    }

    @PostMapping("/user/create")
    public ResponseEntity<?> userList(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

}
