package self.study.service.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import self.study.entity.v2.SignUpDto;
import self.study.entity.v2.User2;
import self.study.repository.v2.User2Repository;

@Service
@RequiredArgsConstructor
public class Auth2Service {

    private final User2Repository userRepository;

    public void singup(User2 user) {
        userRepository.save(user);
    }
}
