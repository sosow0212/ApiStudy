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

}
