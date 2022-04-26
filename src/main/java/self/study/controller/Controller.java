package self.study.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import self.study.entity.Book;
import self.study.entity.User;
import self.study.service.BookService;
import self.study.service.UserService;

@RequiredArgsConstructor
//@RestController
public class Controller {
    private final UserService userService;
    private final BookService bookService;

    @GetMapping("/user")
    public ResponseEntity<?> userList() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> userView(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.findUser(id), HttpStatus.OK);
    }

    @PostMapping("/user/save")
    public ResponseEntity<?> userList(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}/update")
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }


    @GetMapping("/book")
    public ResponseEntity<?> allBook() {
        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<?> bookView(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(bookService.findBook(id), HttpStatus.OK);
    }

    @PostMapping("/book/save/{userId}")
    public ResponseEntity<?> saveBook(@PathVariable("userId") Integer userId, @RequestBody Book book) {
        User user = userService.findUser(userId);
        return new ResponseEntity<>(bookService.saveBook(book, user), HttpStatus.OK);
    }

    @PutMapping("/book/update/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable("bookId") Integer bookId, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.updateBook(book, bookId), HttpStatus.OK);
    }

    @DeleteMapping("/book/delete/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable("bookId") Integer bookId) {
        return new ResponseEntity<>(bookService.deleteBook(bookId), HttpStatus.OK);
    }

}
