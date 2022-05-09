package self.study.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import self.study.repository.v1.BoardRepository;


/**
 * 단위 테스트 (DB 관련된 Bean들이 IoC에 등록되면 됨)
 */

@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // 가짜 DB로 테스트, Replace.NONE은 실제DB로 테스트
@DataJpaTest // Repository들을 다 IoC에 등록해줌
public class BoardRepositoryUnitTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void save_test() throws Exception {
        //given

        //when

        //then

    }
}
