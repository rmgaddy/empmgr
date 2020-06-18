package rmg.empmgr.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rmg.empmgr.EmployeeRepository;
import rmg.empmgr.model.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class EmpMgrContextConfig {

    private static final Logger log = LoggerFactory.getLogger(EmpMgrContextConfig.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {

        List<Employee> employeeList = new ArrayList<>(Arrays.asList(
            new Employee("101", "Axel", "Rose", "Singer"),
            new Employee("102", "Eric", "Clapton", "Lead Guitar")
        ));

        return args -> {
            for (Employee employee : employeeList) {
                log.info("Loaded into db: " + repository.save(employee));
            }
        };

    }

}
