package nu.zapp.repositories;

import nu.zapp.entities.Appointment;
import nu.zapp.entities.EmployeeToAppointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeToAppointmentRepository extends CrudRepository<EmployeeToAppointment, Integer> {
    public List<EmployeeToAppointment> findAll();

    public EmployeeToAppointment findById(int id);

}
