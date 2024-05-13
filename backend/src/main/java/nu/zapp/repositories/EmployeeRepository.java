package nu.zapp.repositories;

import nu.zapp.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    public Employee findByUserName(String username);

    public Employee findById(int id);

    public List<Employee> findAll();

}
