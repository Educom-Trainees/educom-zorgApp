package nu.zapp.repositories;

import nu.zapp.entities.AppointmentTasks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentTaskRepository extends CrudRepository<AppointmentTasks, Integer> {

    public AppointmentTasks save(AppointmentTasks task);

    //public AppointmentTasks saveAll(List<AppointmentTasks> tasks);

}
