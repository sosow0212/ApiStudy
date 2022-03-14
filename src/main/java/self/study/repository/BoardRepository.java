package self.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import self.study.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
}
