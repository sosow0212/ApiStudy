package self.study.service.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self.study.entity.v1.Body;
import self.study.repository.v1.BodyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyService {

    private final BodyRepository bodyRepository;

    @Transactional(readOnly = true)
    public List<Body> loadAllBodyInfo() {
        return bodyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Body loadUserBodyInfo(Integer id) {
        return bodyRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("ID를 찾을 수 없습니다.");
        });
    }

    @Transactional
    public Body saveBodyInfo(Body body) {
        return bodyRepository.save(body);
    }

    @Transactional
    public Body updateBody(Integer userId, Body newBody) {
        Body body = bodyRepository.findById(userId).orElseThrow(() -> {
           return new IllegalArgumentException("ID를 찾을 수 없습니다.");
        });
        body.setName(newBody.getName());
        body.setAge(newBody.getAge());
        body.setHeight(newBody.getHeight());
        body.setWeight(newBody.getWeight());
        body.setBmi(newBody.getBmi());

        return body;
    }


    @Transactional
    public String deleteBodyInfo(Integer id) {
        Body body = bodyRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("ID를 찾을 수 없습니다.");
        });

        bodyRepository.delete(body);
        return "삭제성공";
    }
}
