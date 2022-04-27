package self.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self.study.entity.Request;
import self.study.entity.dto.RequestDto;
import self.study.repository.RequestRepository;

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
