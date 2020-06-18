package rmg.empmgr;

import org.hsqldb.HsqlException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import rmg.empmgr.model.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class EmpMgrServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(EmpMgrServiceImplTest.class);

    @Autowired
    EmpMgrService empMgrService;

    private List<Employee> employeeList;

    @BeforeEach
    void setUp() {
        empMgrService.deleteAll();

        employeeList = new ArrayList<>(Arrays.asList(
            new Employee("101", "Axel", "Rose", "Singer"),
            new Employee("102", "Eric", "Clapton", "Lead Guitar")
        ));

        for (Employee employee : employeeList) {
            log.info("Loaded into db: " + empMgrService.addEmployee(employee));
        }
    }

    @Test
    void noDupesAllowed() {

        try {
            Employee employee = employeeList.get(0);
            employee.setId(null);

            empMgrService.addEmployee(employee);
            fail("Should have received a data integrity violation.");
        } catch(DataIntegrityViolationException e) {
           // success
        }

    }

    @Test
    void getAllEmployees() {
        List<Employee> all = empMgrService.findAllEmployees();
        assertEquals(2, all.size());

        int i = 0;
        for (Employee employee : all) {
            assertEquals(employeeList.get(i++), employee);
        }

    }

    @Test
    void findEmployeeById() {
        Employee employee = empMgrService.findEmployeeById("101");
        assertNotNull(employee);
        assertEquals(employeeList.get(0), employee);
    }

    @Test
    void updateEmployeeDetails() {
        Employee employee = empMgrService.findEmployeeById("101");
        assertNotNull(employee);
        assertEquals(employeeList.get(0), employee);

        employee.setJobRole("Dancer");
        empMgrService.update(employee);

        employee = empMgrService.findEmployeeById("101");
        assertNotNull(employee);
        assertEquals("Dancer", employee.getJobRole());
    }

}
