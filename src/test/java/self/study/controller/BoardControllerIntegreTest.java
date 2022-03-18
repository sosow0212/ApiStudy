package self.study.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


/**
 * 통합 테스트 (모든 Bean들을 똑같이 IoC에 올리고 테스트)
 * WebEnvironment.MOCK == 실제 톰캣을 올리는게 아니라 다른 톰캣으로 테스트
 * WebEnvironment.RANDOM_PRO == 실제 톰캣으로 테스트
 * @AutoConfigureMockMvc // 이걸 넣어야 MockMvc를 IoC에 등록
 * @Transactional 은 각각의 테스트함수가 종료될 때마다 트랜잭션을 rollback 해준다.
 */

@Transactional // 이걸 붙여야 rollback 되기 때문에 편함
@AutoConfigureMockMvc // 이걸 넣어야 MockMvc를 인젝션할 수 있다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // 다른 톰캣으로 테스트
public class BoardControllerIntegreTest {

    @Autowired
    private MockMvc mockMvc;

    public void test1() {

    }
}
