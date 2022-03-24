package self.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import self.study.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
