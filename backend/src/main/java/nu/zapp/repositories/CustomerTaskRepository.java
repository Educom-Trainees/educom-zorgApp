package nu.zapp.repositories;

import nu.zapp.entities.CustomerSetTasks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerTaskRepository extends CrudRepository<CustomerSetTasks, Integer> {
    public List<CustomerSetTasks> findAll();

    public CustomerSetTasks findByTask(String task);

    public CustomerSetTasks findById(int id);

    public CustomerSetTasks save(CustomerSetTasks newtask);
}
