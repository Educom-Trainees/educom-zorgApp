package nu.zapp.repositories;
import nu.zapp.entities.Generaltasks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Generaltasks, Long> {
    public List<Generaltasks> findAll();

    public Generaltasks save(Generaltasks newtask);
}
