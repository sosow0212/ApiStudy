package self.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import self.study.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
