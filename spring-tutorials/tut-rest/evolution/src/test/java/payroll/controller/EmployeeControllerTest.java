package payroll.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import payroll.entity.Employee;
import payroll.repository.EmployeeRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void all() throws Exception {
        List<Employee> employees = employeeRepository.findAll();
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.employeeList").isArray())
                .andExpect(jsonPath("$._embedded.employeeList", hasSize(employees.size())))
                .andExpect(jsonPath("$._links.self.href", containsString("employees")));
    }

    @Test
    public void newEmployee() throws Exception {
        Employee employee = new Employee("Frodo", "USER");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(employee);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/employees")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .content(content);

        this.mockMvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Frodo"))
                .andExpect(jsonPath("$.role").value("USER"));
    }

    @Test
    public void one() throws Exception {
        Employee employee = employeeRepository.findById(1L).orElse(null);
        assertThat(employee).isNotNull();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andExpect(jsonPath("$.role").value(employee.getRole()));
    }

    @Test
    public void oneNotExist() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/employees/1000"))
                .andExpect(status().isNotFound());
    }
}
