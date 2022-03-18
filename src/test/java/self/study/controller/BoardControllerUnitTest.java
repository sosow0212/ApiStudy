package self.study.controller;

// 단위 테스트 (Controller 관련 로직만 띄움)
// ex) Controller, Filter, ControllerAdvice

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@WebMvcTest
@ExtendWith(SpringExtension.class) // 이걸 꼭 붙여야함
public class BoardControllerUnitTest {

}
