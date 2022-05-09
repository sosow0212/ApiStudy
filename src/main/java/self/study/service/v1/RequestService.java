package self.study.service.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self.study.entity.v1.Request;
import self.study.entity.v1.dto.RequestDto;
import self.study.repository.v1.RequestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    @Transactional(readOnly = true)
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Transactional
    public Request save(RequestDto requestDto) {
        return requestRepository.save(requestDto.toEntity());
    }

}
