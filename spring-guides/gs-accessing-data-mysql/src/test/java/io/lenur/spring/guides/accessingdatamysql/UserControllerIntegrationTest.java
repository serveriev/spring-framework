package io.lenur.spring.guides.accessingdatamysql;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    public void createUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User("user", "user@example.com");
        String content = mapper.writeValueAsString(user);

        RequestBuilder request = post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        this.mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(equalTo("Saved")));

        for (User user1: userRepository.findAll()) {
            assertThat(user1.getEmail()).isEqualTo(user.getEmail());
            assertThat(user1.getName()).isEqualTo(user.getName());
        }
    }

    @Test
    @Order(2)
    public void getAllUsers() throws Exception {
        createUser();
        this.mockMvc
                .perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("user"))
                .andExpect(jsonPath("$[0].email").value("user@example.com"));

        userRepository.deleteAll();
    }
}