package self.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import self.study.entity.Body;
import self.study.repository.BodyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyService {

    private final BodyRepository bodyRepository;

    public List<Body> loadUsersBodyInfo() {
        return bodyRepository.findAll();
    }
}
