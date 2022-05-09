package self.study.repository.v2;

import org.springframework.data.jpa.repository.JpaRepository;
import self.study.entity.v2.SignUpDto;
import self.study.entity.v2.User2;


public interface User2Repository extends JpaRepository<User2, Integer> {
}
