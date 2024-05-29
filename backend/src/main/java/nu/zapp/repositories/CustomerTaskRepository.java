package nu.zapp.repositories;

import nu.zapp.entities.CustomerTasks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerTaskRepository extends CrudRepository<CustomerTasks, Integer> {
    List<CustomerTasks> findAll();

    CustomerTasks findByTask(String task);

    CustomerTasks findById(int id);

    List<CustomerTasks> findByCustomerId(int id);

    CustomerTasks save(CustomerTasks newtask);
}
