package nu.zapp.repositories;

import nu.zapp.entities.CustomerTasks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerTaskRepository extends CrudRepository<CustomerTasks, Integer> {
    public List<CustomerTasks> findAll();

    public CustomerTasks findByTask(String task);

    public CustomerTasks findById(int id);

    public CustomerTasks save(CustomerTasks newtask);
}
