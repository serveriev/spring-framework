package io.lenur.spring.guides.handlingformsubmission;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void rendersForm() throws Exception {
        mockMvc.perform(get("/greeting"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Form")));
    }

    @Test
    public void submitsForm() throws Exception {
        RequestBuilder requestBuilder = post("/greeting")
                .param("id", "12345")
                .param("content", "Hello");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(content().string(containsString("Result")))
                .andExpect(content().string(containsString("id: 12345")));
    }
}