package self.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self.study.entity.Store;
import self.study.repository.StoreRepository;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Transactional(readOnly = true)
    public List<Store> findAll() {
        List<Store> stores = storeRepository.findAll();
        return stores;
    }

    @Transactional
    public Store save(Store store) {

        storeRepository.save(store);
        return store;
    }

    @Transactional(readOnly = true)
    public Store findById(int id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패, 아이디를 찾을 수 없습니다.");
        });
        return store;
    }

}
