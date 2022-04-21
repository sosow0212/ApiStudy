package self.study.bodyTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import self.study.entity.Body;
import self.study.service.BodyService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest
public class BodyControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    private final Body body = new Body(1, "이재윤", 24, 186, 80, 0);


    @MockBean
    private BodyService bodyService;

    @DisplayName("유저 정보 저장")
    @Test
    public void saveBody() throws Exception {
        // given
        String content = new ObjectMapper().writeValueAsString(body);
        when(bodyService.saveBodyInfo(body)).thenReturn(new Body(1, "이재윤", 24, 186, 80, 0));


        // when
        ResultActions resultActions = mockMvc.perform(post("/api/body/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON));


        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("이재윤"))
                .andDo(MockMvcResultHandlers.print());
    }


    @DisplayName("모든 유저 조회")
    @Test
    public void findAll_test() throws Exception {
        // given
        List<Body> bodies = new ArrayList<>();
        bodies.add(new Body(1, "이재윤", 24, 186, 80, 0));
        bodies.add(new Body(2, "이재윤2", 242, 1862, 802, 2));
        when(bodyService.loadAllBodyInfo()).thenReturn(bodies);

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/body")
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.[1].name").value("이재윤2"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("개별 유저 찾기")
    @Test
    public void findById_test() throws Exception {
        // given
        int id = 1;
        Body body = new Body(1, "이재윤", 24, 186, 80, 0);
        when(bodyService.loadUserBodyInfo(id)).thenReturn(body);

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/body/{id}", id)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("이재윤"))
                .andDo(MockMvcResultHandlers.print());
    }


    @DisplayName("유저 정보 변경")
    @Test
    public void update_test() throws Exception {
        // given
        int id = 1;
        Body body = new Body(1, "이재윤", 24, 186, 80, 0);
        String content = new ObjectMapper().writeValueAsString(body);
        when(bodyService.updateBody(id, body)).thenReturn(new Body(1, "이재윤", 24, 186, 80, 0));

        // when
        ResultActions resultActions = mockMvc.perform(put("/api/body/{id}/edit", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("이재윤"))
                .andDo(MockMvcResultHandlers.print());
    }


    @DisplayName("삭제")
    @Test
    public void delete_test() throws Exception {
        // given
        int id = 1;
        when(bodyService.deleteBodyInfo(id)).thenReturn("삭제성공");

        // when
        ResultActions resultActions = mockMvc.perform(delete("/api/body/{id}/delete",id));

        // then
        resultActions
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals("삭제성공", result);
    }

}
