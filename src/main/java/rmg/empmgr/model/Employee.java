package rmg.empmgr.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employee",
    indexes = {
        @Index(name = "employee_id_idx", columnList = "employee_id", unique = true)
    })
public class Employee implements Comparable<Employee> {

    // surrogate primary key not the employeeId
    String id;

    String employeeId;
    String firstName;
    String lastName;
    String jobRole;

    public Employee() {
    }

    public Employee(String employeeId, String firstName, String lastName, String jobRole) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobRole = jobRole;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "employee_id", nullable = false)
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "job_role", nullable = false)
    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId.equals(employee.employeeId) &&
            firstName.equals(employee.firstName) &&
            lastName.equals(employee.lastName) &&
            jobRole.equals(employee.jobRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firstName, lastName, jobRole);
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id='" + id + '\'' +
            ", employeeId='" + employeeId + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", jobRole='" + jobRole + '\'' +
            '}';
    }

    @Override
    public int compareTo(Employee rhs) {
        return this.employeeId.compareTo(rhs.employeeId);
    }
}
