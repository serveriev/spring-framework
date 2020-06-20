package io.lenur.spring.guides.accessingdatamysql;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Iterator;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void createUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User("user", "user@example.com");
        String content = mapper.writeValueAsString(user);

        RequestBuilder request = post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        when(userRepository.save(user)).thenReturn(user);

        this.mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(equalTo("Saved")));
    }

    @Test
    public void getAllUsers() throws Exception {
        when(userRepository.findAll()).thenReturn(getUserIterator());

        this.mockMvc
                .perform(get("/users"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(equalTo("[]")));
    }

    private Iterable<User> getUserIterator() {
        return (() -> new Iterator<>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public User next() {
                return new User("user", "user@example.com");
            }
        });
    }
}