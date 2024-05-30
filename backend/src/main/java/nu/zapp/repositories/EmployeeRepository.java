package nu.zapp.repositories;

import nu.zapp.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    Employee findByUsername(String username);

    Employee findById(int id);

    List<Employee> findAll();
    Employee save(Employee employee);

}
