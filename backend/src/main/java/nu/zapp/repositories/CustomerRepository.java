package nu.zapp.repositories;

import nu.zapp.entities.Customer;
import nu.zapp.entities.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    public Customer findById(int id);
    public List<Customer> findAll();
}


