package payroll.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import payroll.entity.Order;
import payroll.repository.OrderRepository;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void all() throws Exception {
        List<Order> employees = orderRepository.findAll();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.orderList").isArray())
                .andExpect(jsonPath("$._embedded.orderList", hasSize(employees.size())))
                .andExpect(jsonPath("$._links.self.href", containsString("orders")));
    }

    @Test
    public void oneNotExist() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/employees/1000"))
                .andExpect(status().isNotFound());
    }
}
