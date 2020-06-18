package rmg.empmgr;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rmg.empmgr.model.Employee;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmpMgrService empMgrService;

    @GetMapping("/employee")
    public List<Employee> findAll() {
        return empMgrService.findAllEmployees();
    }

    @GetMapping("/employee/{employeeId}")
    Employee findById(@PathVariable String employeeId) {
        return empMgrService.findEmployeeById(employeeId);
    }

    @PostMapping("/employee")
    Employee add(@RequestBody Employee newEmployee) {
        return empMgrService.addEmployee(newEmployee);
    }


    @PutMapping("/employee")
    Employee update(@RequestBody Employee newEmployee) {
        return empMgrService.update(newEmployee);
    }

}
