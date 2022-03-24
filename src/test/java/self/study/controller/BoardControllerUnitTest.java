package self.study.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import self.study.entity.Board;
import self.study.service.BoardService;

import java.util.ArrayList;
import java.util.List;


// 단위 테스트 (Controller 관련 로직만 띄움)
// ex) Controller, Filter, ControllerAdvice
// @ExtendWith(SpringExtension.class) // 이걸 꼭 붙여야함 하지만 JUnit5 되면서 붙여 나옴


@Slf4j
@WebMvcTest
public class BoardControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // boardService가 IoC환경에 bean 등록이 됨 가짜 서비스임!!
    private BoardService boardService;

    // BDDMockito 패턴, Given When Then
    @Test
    public void save_test() throws Exception {
        // given (테스트를 하기 위한 준비)
        Board board = new Board(1, "스프링테스트", "랄라");
        // Json 오브젝트를 주기 위해서 (컨트롤러에서는 @RestController를 사용해서 Json 데이터를 줘야함 = ObjectMapper 사용)
        String content = new ObjectMapper().writeValueAsString(board);

        // 이건 통합 서비스에선 안 써도됨
        when(boardService.writeBoard(board)).thenReturn(new Board(1, "스프링테스트", "랄라"));


        // when (테스트 실행)
        ResultActions resultActions = mockMvc.perform(post("/board/write")
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
        when(boardService.findAll()).thenReturn(boards);

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
        Board board = new Board(1, "자바 스터디", "호호");
        when(boardService.findById(id)).thenReturn(board);

        // when
        ResultActions resultActions = mockMvc.perform(get("/board/{id}", id)
                .accept(MediaType.APPLICATION_JSON_UTF8));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("자바 스터디"))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void update_test() throws Exception {
        // given
        int id = 2;
        Board board = new Board(2, "업데이트 성공", "고고");
        String content = new ObjectMapper().writeValueAsString(board);
        when(boardService.editBoard(id, board)).thenReturn(new Board(2, "업데이트 성공", "고고"));

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
        when(boardService.deleteBoard(id)).thenReturn("ok");

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
