package self.study.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import self.study.entity.Board;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * 통합 테스트 (모든 Bean들을 똑같이 IoC에 올리고 테스트)
 * WebEnvironment.MOCK == 실제 톰캣을 올리는게 아니라 다른 톰캣으로 테스트
 * WebEnvironment.RANDOM_PRO == 실제 톰캣으로 테스트
 * @AutoConfigureMockMvc // 이걸 넣어야 MockMvc를 IoC에 등록
 * @Transactional 은 각각의 테스트함수가 종료될 때마다 트랜잭션을 rollback 해준다.
 */

@Slf4j
@Transactional // 이걸 붙여야 rollback 되기 때문에 DB에 실제로 저장되지 않고 다시 반환됨.
@AutoConfigureMockMvc // 이걸 넣어야 MockMvc를 인젝션할 수 있다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // 다른 톰캣으로 테스트
public class BoardControllerIntegreTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void save_test() throws Exception {
        // given (테스트를 하기 위한 준비)
        Board board = new Board(1, "스프링테스트", "랄라");
        // Json 오브젝트를 주기 위해서 (컨트롤러에서는 @RestController를 사용해서 Json 데이터를 줘야함 = ObjectMapper 사용)
        String content = new ObjectMapper().writeValueAsString(board);


        // when (테스트 실행)
        ResultActions resultActions =
                mockMvc.perform(post("/board/write")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(content)
                        .accept(MediaType.APPLICATION_JSON_UTF8));



        // then (검증)
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("스프링테스트"))
                .andDo(MockMvcResultHandlers.print());

    }
}
