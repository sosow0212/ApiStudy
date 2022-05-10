package self.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import self.study.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
