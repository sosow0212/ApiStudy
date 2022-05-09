package self.study.repository.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import self.study.entity.v1.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
