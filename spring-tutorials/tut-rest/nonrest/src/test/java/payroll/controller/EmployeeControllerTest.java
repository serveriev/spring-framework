package payroll.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import payroll.entity.Employee;
import payroll.repository.EmployeeRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void all() throws Exception {
        Employee employee = new Employee(1L, "Frodo", "USER");
        List<Employee> employees = Collections.singletonList(employee);
        Mockito.when(employeeRepository.findAll())
                .thenReturn(employees);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Frodo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].role").value("USER"));
    }

    @Test
    public void newEmployee() throws Exception {
        Employee.EmployeeBuilder employeeBuilder = Employee.builder().name("Frodo").role("USER");
        Employee employeeCreate = employeeBuilder.build();

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(employeeCreate);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/employees")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .content(content);

        Employee employee = employeeBuilder.id(1L).build();
        Mockito.when(employeeRepository.save(employeeCreate))
                .thenReturn(employee);

        this.mockMvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Frodo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("USER"));
    }

    @Test
    public void one() throws Exception {
        Employee employee = new Employee(1L, "Frodo", "USER");
        Mockito.when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/employees/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Frodo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("USER"));
    }


    @Test
    public void oneNotExist() throws Exception {
        Mockito.when(employeeRepository.findById(1L))
                .thenReturn(Optional.empty());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/employees/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteEmployee() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/employees/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
