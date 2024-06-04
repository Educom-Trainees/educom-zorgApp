package nu.zapp.repositories;

import nu.zapp.entities.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findById(int id);
    List<Customer> findAll();

}


