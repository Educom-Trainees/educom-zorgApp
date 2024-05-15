package nu.zapp.repositories;

import nu.zapp.entities.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

    public List<Appointment> findAll();

}
