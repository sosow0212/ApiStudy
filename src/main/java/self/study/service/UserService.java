package self.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import self.study.dto.RegisterDto;
import self.study.entity.User;
import self.study.repository.UserRepository;
import self.study.response.Response;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(RegisterDto registerDto) {
        User user = new User();
        user.setName(registerDto.getName());
        user.setPassword(registerDto.getPassword());
        user.setUsername(registerDto.getUsername());
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
