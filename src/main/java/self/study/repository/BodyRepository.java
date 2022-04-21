package self.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import self.study.entity.Body;

public interface BodyRepository extends JpaRepository<Body, Integer> {
}
