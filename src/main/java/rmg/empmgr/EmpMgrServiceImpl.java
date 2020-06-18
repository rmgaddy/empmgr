package rmg.empmgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rmg.empmgr.model.Employee;

import java.util.Collections;
import java.util.List;


@Component
public class EmpMgrServiceImpl implements EmpMgrService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        Collections.sort(employeeList);
        return employeeList;
    }

    @Override
    public Employee findEmployeeById(String employeeId) {
        List<Employee> employeeList =  employeeRepository.findByEmployeeIdOrderByEmployeeIdAsc(employeeId);
        if (employeeList == null || employeeList.size() == 0) {
            throw new EmployeeNotFoundException(employeeId);
        }
        return employeeList.get(0);
    }

    @Override
    public Employee update(Employee employee) {

        Employee oldEmployee = findEmployeeById(employee.getEmployeeId());
        oldEmployee.setEmployeeId(employee.getEmployeeId());
        oldEmployee.setFirstName(employee.getFirstName());
        oldEmployee.setLastName(employee.getLastName());
        oldEmployee.setJobRole(employee.getJobRole());

        return employeeRepository.save(oldEmployee);
    }

    @Override
    public void deleteAll() {
        employeeRepository.deleteAll();
    }

}
