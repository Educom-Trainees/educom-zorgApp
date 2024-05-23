package nu.zapp.repositories;

import nu.zapp.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    //Both findByUsername and findById are for detail pages

    Employee findByUsername(String username);

    //@Query("SELECT a FROM Employee a LEFT JOIN FETCH a.employeeToAppointments b LEFT JOIN FETCH b.appointment c")
    Employee findById(int id);

    List<Employee> findAll();
    Employee save(Employee employee);

}
