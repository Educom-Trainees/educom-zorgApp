package nu.zapp.repositories;

import nu.zapp.entities.AppointmentTasks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentTaskRepository extends CrudRepository<AppointmentTasks, Integer> {

    List<AppointmentTasks> findByAppointmentId(int id);
    AppointmentTasks save(AppointmentTasks task);

    //AppointmentTasks saveAll(List<AppointmentTasks> tasks);

}
