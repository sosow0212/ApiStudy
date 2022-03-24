package self.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import self.study.repository.BookRepository;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;


}
