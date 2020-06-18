package rmg.empmgr;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import rmg.empmgr.model.Employee;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.core.Is.is;


@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmpMgrService empMgrService;

    @Test
    public void findAll() throws Exception {

        Employee employee = new Employee("101", "Axel", "Rose", "Singer");
        given(empMgrService.findAllEmployees()).willReturn(Arrays.asList(employee));

        mvc.perform(get("/employee")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0]['firstName']", is(employee.getFirstName())));

    }

    @Test
    public void findById() throws Exception {

        Employee employee = new Employee("101", "Axel", "Rose", "Singer");
        given(empMgrService.findEmployeeById(employee.getEmployeeId())).willReturn(employee);

        mvc.perform(get("/employee/" + employee.getEmployeeId())
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("firstName", is(employee.getFirstName())));

    }

    @Test
    public void add() throws Exception {

        Employee employee = new Employee("101", "Axel", "Rose", "Singer");
        given(empMgrService.addEmployee(employee)).willReturn(employee);

        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(employee);

        mvc.perform(post("/employee")
            .content(json)
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("firstName", is(employee.getFirstName())));

    }

    @Test
    public void update() throws Exception {

        Employee employee = new Employee("101", "Axel", "Rose", "Singer");
        given(empMgrService.update(employee)).willReturn(employee);

        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(employee);

        mvc.perform(put("/employee")
            .content(json)
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("firstName", is(employee.getFirstName())));

    }

}
