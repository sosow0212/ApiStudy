package self.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self.study.entity.Book;
import self.study.entity.User;
import self.study.repository.BookRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findBook(int id) {
        return bookRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("Book Id를 찾을 수 없습니다.");
        });
    }

    @Transactional
    public Book saveBook(Book book, User user) {
        book.setUser(user);
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Book requestBook, int bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()->{
            return new IllegalArgumentException("book을 찾을 수 없습니다.");
        });

        book.setContent(requestBook.getContent());
        book.setTitle(requestBook.getTitle());

        return book;
    }

    @Transactional
    public String deleteBook(int bookId) {
        bookRepository.deleteById(bookId);
        return "ok";
    }

}
