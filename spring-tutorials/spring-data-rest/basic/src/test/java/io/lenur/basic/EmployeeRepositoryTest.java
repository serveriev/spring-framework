package io.lenur.basic;

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
public class EmployeeRepositoryTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void deleteAllBeforeTests() throws Exception {
        employeeRepository.deleteAll();
    }

    @Test
    public void shouldReturnRepositoryIndex() throws Exception {
        RequestBuilder request = get("/api");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.employees").exists());
    }

    @Test
    public void shouldCreateEntity() throws Exception {
        String content = mapper.writeValueAsString(new Employee("Frodo", "Baggins", "ring bearer"));

        RequestBuilder request = post("/api/employees").content(content);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("employees/")));
    }

    @Test
    public void shouldRetrieveEntity() throws Exception {
        String content = mapper
                .writeValueAsString(new Employee("Frodo", "Baggins", "ring bearer"));

        RequestBuilder requestCreate = post("/api/employees").content(content);
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
                .andExpect(jsonPath("$.lastName").value("Baggins"))
                .andExpect(jsonPath("$.description").value("ring bearer"));
    }

    @Test
    public void shouldUpdateEntity() throws Exception {
        String content = mapper
                .writeValueAsString(new Employee("Frodo", "Baggins", "ring bearer"));

        RequestBuilder requestCreate = post("/api/employees").content(content);
        MvcResult mvcResult = mockMvc
                .perform(requestCreate)
                .andExpect(status().isCreated())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("Location");
        assertThat(location).isNotNull();

        String contentUpdate = mapper
                .writeValueAsString(new Employee("Frodo upd", "Baggins upd", "ring bearer upd"));
        RequestBuilder requestPut = put(location).content(contentUpdate);
        mockMvc.perform(requestPut)
                .andExpect(status().isNoContent());

        RequestBuilder requestGet = get(location);
        mockMvc.perform(requestGet)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Frodo upd"))
                .andExpect(jsonPath("$.lastName").value("Baggins upd"))
                .andExpect(jsonPath("$.description").value("ring bearer upd"));
    }

    @Test
    public void shouldPartiallyUpdateEntity() throws Exception {
        String content = mapper
                .writeValueAsString(new Employee("Frodo", "Baggins", "ring bearer"));

        RequestBuilder requestCreate = post("/api/employees").content(content);
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
                .andExpect(jsonPath("$.lastName").value("Baggins"))
                .andExpect(jsonPath("$.description").value("ring bearer"));
    }

    @Test
    public void shouldDeleteEntity() throws Exception {
        String content = mapper
                .writeValueAsString(new Employee("Frodo", "Baggins", "ring bearer"));

        RequestBuilder requestCreate = post("/api/employees").content(content);
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