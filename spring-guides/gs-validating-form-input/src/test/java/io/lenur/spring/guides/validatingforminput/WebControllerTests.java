package io.lenur.spring.guides.validatingforminput;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class WebControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void withoutPassingNameGetError() throws Exception {
        MockHttpServletRequestBuilder createPerson = post("/")
                .param("age", "20");

        mockMvc.perform(createPerson)
                .andExpect(model().hasErrors());
    }

    @Test
    public void passInvalidNameParam() throws Exception {
        MockHttpServletRequestBuilder createPerson = post("/")
                .param("name", "R")
                .param("age", "20");

        mockMvc.perform(createPerson)
                .andExpect(model().hasErrors());
    }

    @Test
    public void missAgeParam() throws Exception {
        MockHttpServletRequestBuilder createPerson = post("/")
                .param("name", "Rob");

        mockMvc.perform(createPerson)
                .andExpect(model().hasErrors());
    }

    @Test
    public void passCorrectParams() throws Exception {
        MockHttpServletRequestBuilder createPerson = post("/")
                .param("name", "Rob")
                .param("age", "20");

        mockMvc.perform(createPerson)
                .andExpect(model().hasNoErrors());
    }
}
