package nu.zapp.repositories;

import nu.zapp.entities.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

    @Query("SELECT a FROM Appointment a JOIN FETCH a.customer c")
    public List<Appointment> findAll();

    public Appointment findById(int id);

}
