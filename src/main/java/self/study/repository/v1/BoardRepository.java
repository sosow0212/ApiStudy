package self.study.repository.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import self.study.entity.v1.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
}
