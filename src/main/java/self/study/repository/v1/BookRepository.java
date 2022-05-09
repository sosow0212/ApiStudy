package self.study.repository.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import self.study.entity.v1.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
