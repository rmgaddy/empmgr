package rmg.empmgr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rmg.empmgr.model.Employee;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findByEmployeeIdOrderByEmployeeIdAsc(String employeeId);

}
