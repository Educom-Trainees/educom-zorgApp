package nu.zapp.repositories;
import nu.zapp.entities.Generaltasks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Generaltasks, Integer> {
    List<Generaltasks> findAll();

    Generaltasks findByTask(String task);

    Generaltasks findById(int id);

    Generaltasks save(Generaltasks newtask);
}
