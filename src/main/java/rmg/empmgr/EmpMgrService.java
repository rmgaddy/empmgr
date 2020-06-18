package rmg.empmgr;

import rmg.empmgr.model.Employee;

import java.util.List;

public interface EmpMgrService {

    Employee addEmployee(Employee employee);
    List<Employee> findAllEmployees();
    Employee findEmployeeById(String employeeId);
    Employee update(Employee employee);
    void deleteAll();

}


