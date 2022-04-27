package self.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import self.study.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {
}
