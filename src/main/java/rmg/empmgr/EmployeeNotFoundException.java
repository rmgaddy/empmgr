package rmg.empmgr;

public class EmployeeNotFoundException extends RuntimeException {

    EmployeeNotFoundException(String employeeId) {
        super("Employee not found with: employee id " + employeeId);
    }

}
