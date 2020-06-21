package payroll.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import payroll.repository.EmployeeRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RootControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void rootInfo() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/root-info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.employees.href", containsString("employees")))
                .andExpect(jsonPath("$._links.orders.href", containsString("orders")));
    }
}
