package io.lenur.spring.guides.accessingdatarest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void deleteAllBeforeTests() throws Exception {
        personRepository.deleteAll();
    }

    @Test
    public void shouldReturnRepositoryIndex() throws Exception {
        RequestBuilder request = get("/");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.people").exists());
    }

    @Test
    public void shouldCreateEntity() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new Person("Frodo", "Baggins"));

        RequestBuilder request = post("/people").content(content);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("people/")));
    }

    @Test
    public void shouldRetrieveEntity() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new Person("Frodo", "Baggins"));

        RequestBuilder requestCreate = post("/people").content(content);
        MvcResult mvcResult = mockMvc
                .perform(requestCreate)
                .andExpect(status().isCreated())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("Location");
        assertThat(location).isNotNull();

        RequestBuilder requestGet = get(location);
        mockMvc.perform(requestGet)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Frodo"))
                .andExpect(jsonPath("$.lastName").value("Baggins"));
    }

    @Test
    public void shouldQueryEntity() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new Person("Frodo", "Baggins"));

        RequestBuilder requestCreate = post("/people").content(content);
        mockMvc.perform(requestCreate)
                .andExpect(status().isCreated());

        RequestBuilder requestGet = get("/people/search/findByLastName?name={name}", "Baggins");
        mockMvc.perform(requestGet)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.people[0].firstName").value("Frodo"));
    }

    @Test
    public void shouldUpdateEntity() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new Person("Frodo", "Baggins"));

        RequestBuilder requestCreate = post("/people").content(content);
        MvcResult mvcResult = mockMvc
                .perform(requestCreate)
                .andExpect(status().isCreated())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("Location");
        assertThat(location).isNotNull();

        String contentUpdate = mapper.writeValueAsString(new Person("Bilbo", "Baggins"));
        RequestBuilder requestPut = put(location).content(contentUpdate);
        mockMvc.perform(requestPut)
                .andExpect(status().isNoContent());

        RequestBuilder requestGet = get(location);
        mockMvc.perform(requestGet)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Bilbo"))
                .andExpect(jsonPath("$.lastName").value("Baggins"));
    }

    @Test
    public void shouldPartiallyUpdateEntity() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new Person("Frodo", "Baggins"));

        RequestBuilder requestCreate = post("/people").content(content);
        MvcResult mvcResult = mockMvc
                .perform(requestCreate)
                .andExpect(status().isCreated())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("Location");
        assertThat(location).isNotNull();

        RequestBuilder requestPatch = patch(location).content("{\"firstName\": \"Bilbo Jr.\"}");
        mockMvc.perform(requestPatch)
                .andExpect(status().isNoContent());

        RequestBuilder requestGet = get(location);
        mockMvc.perform(requestGet)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Bilbo Jr."))
                .andExpect(jsonPath("$.lastName").value("Baggins"));
    }

    @Test
    public void shouldDeleteEntity() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new Person("Bilbo", "Baggins"));

        RequestBuilder requestCreate = post("/people").content(content);
        MvcResult mvcResult = mockMvc
                .perform(requestCreate)
                .andExpect(status().isCreated())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("Location");
        assertThat(location).isNotNull();

        mockMvc.perform(delete(location)).andExpect(status().isNoContent());
        mockMvc.perform(get(location)).andExpect(status().isNotFound());
    }
}