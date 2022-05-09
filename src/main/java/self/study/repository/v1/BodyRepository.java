package self.study.repository.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import self.study.entity.v1.Body;

public interface BodyRepository extends JpaRepository<Body, Integer> {
}
