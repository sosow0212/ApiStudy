package self.study.bodyTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import self.study.entity.Body;
import self.study.service.BodyService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @DisplayName("1_save_test")
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
}
