package self.study.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import self.study.entity.Board;
import self.study.repository.BoardRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager entityManager;
    // Jpa의 구현체


    @BeforeEach // 모든 테스트가 실행되기 전에 실행됨
    public void init() {
        entityManager.createNativeQuery("ALTER TABLE board AUTO_INCREMENT = 1").executeUpdate();
    }



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



    @Test
    public void findAll_test() throws Exception {
        // given
        List<Board> boards = new ArrayList<>();
        boards.add(new Board(1, "제목1", "내용1"));
        boards.add(new Board(2, "제목2", "내용2"));
        boardRepository.saveAll(boards);
        // > 실제 DB에 데이터를 넣었다가 rollback 함 (transaction 때문에)


        // when
        ResultActions resultActions = mockMvc.perform(get("/board")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        );


        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.[0].title").value("제목1"))
                .andDo(MockMvcResultHandlers.print());
    }



    @Test
    public void findById_test() throws Exception {
        // given
        int id = 1;

        List<Board> boards = new ArrayList<>();
        boards.add(new Board(1, "제목1", "내용1"));
        boards.add(new Board(2, "제목2", "내용2"));
        boardRepository.saveAll(boards);

        // when
        ResultActions resultActions = mockMvc.perform(get("/board/{id}", id)
                .accept(MediaType.APPLICATION_JSON_UTF8));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("제목1"))
                .andDo(MockMvcResultHandlers.print());
    }




    @Test
    public void update_test() throws Exception {
        // given
        int id = 2;

        List<Board> boards = new ArrayList<>();
        boards.add(new Board(1, "제목1", "내용1"));
        boards.add(new Board(2, "제목2", "내용2"));
        boardRepository.saveAll(boards);

        Board board = new Board(2, "업데이트 성공", "고고");
        String content = new ObjectMapper().writeValueAsString(board);

        // when
        ResultActions resultActions = mockMvc.perform(put("/board/{id}/update", id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        );

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("업데이트 성공"))
                .andDo(MockMvcResultHandlers.print());
    }



    @Test
    public void delete_test() throws Exception {
        // given
        int id = 1;

        boardRepository.save(new Board(1, "제목1", "내용1"));

        // when
        ResultActions resultActions = mockMvc.perform(delete("/board/{id}/delete", id));

        // then
        resultActions
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        MvcResult requestResult = resultActions.andReturn();
        String result = requestResult.getResponse().getContentAsString();

        Assertions.assertEquals("ok", result);

    }
}
