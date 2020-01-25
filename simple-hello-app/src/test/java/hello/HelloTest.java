package hello;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DisplayName("Hello Testing")
@AutoConfigureMockMvc
public class HelloTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test with input Person")
    public void testWithInputPerson() throws Exception {
        var person = "John Doe";
        mockMvc.perform(get(String.format("/?person=%s",person)).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isFound()).andExpect(jsonPath("$.salutation").value(String.format("Hello, %s",person)));
    }

    @Test
    @DisplayName("Test with empty input Person")
    public void testWithEmptyInputPerson() throws Exception {
        var person = "";
        mockMvc.perform(get(String.format("/?person=%s",person)).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isFound()).andExpect(jsonPath("$.salutation").value("Hello World"));
    }

    @Test
    @DisplayName("Test without input Person")
    public void testWithoutInputPerson() throws Exception {
        mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isFound()).andExpect(jsonPath("$.salutation").value("Hello World"));

    }
}
