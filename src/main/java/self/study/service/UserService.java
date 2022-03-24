package self.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self.study.entity.User;
import self.study.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findUser(int id) {
        return userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("User Id를 찾을 수 없습니다.");
        });
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
