package nu.zapp.repositories;
import nu.zapp.entities.Generaltasks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Generaltasks, Integer> {
    public List<Generaltasks> findAll();

    public Generaltasks findByTask(String task);

    public Generaltasks findById(int id);

    public Generaltasks save(Generaltasks newtask);
}
