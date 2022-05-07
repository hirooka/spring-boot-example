package example.api.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.domain.dto.ExampleOne;
import example.domain.service.IExampleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExampleRestController.class)
public class ExampleRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IExampleService exampleService;

    @Test
    void Create_Example_Http200() throws Exception {
        ExampleOne exampleOne = new ExampleOne();
        given(exampleService.create(exampleOne)).willReturn(exampleOne);
        mockMvc.perform(
                post("/v1/examples")
                        .content(new ObjectMapper().writeValueAsBytes(exampleOne))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
