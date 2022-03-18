package self.study.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import self.study.repository.BoardRepository;

/**
 * 단위테스트 (Service 관련된 Bean들만 메모리에 띄우면 됨)
 * BoardRepository => 가짜 객체로 만들 수 있음
 */

@ExtendWith(MockitoExtension.class) //
public class BoardServiceUnitTest {

    @InjectMocks // BoardSercice 객체가 만들어질 때, ServiceTest파일에 @Mock로 등록된 모든 애들을 주입 받는다.
    private BoardService boardService;

    @Mock // Mock을 붙여야 BoardService 안에 repository가 테스트 파일에서 주입된다.
    private BoardRepository boardRepository;

}
