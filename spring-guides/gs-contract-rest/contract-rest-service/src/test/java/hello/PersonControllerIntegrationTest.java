package hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonService personService;

    @Test
    public void findPersonById() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/person/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Richard"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Gere"));
    }
}
