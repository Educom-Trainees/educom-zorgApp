package nu.zapp.repositories;

import nu.zapp.entities.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

    @Query("SELECT a FROM Appointment a JOIN FETCH a.customer c")
    public List<Appointment> findAll();

    @Query("SELECT a FROM Appointment a JOIN FETCH a.customer c WHERE a.date BETWEEN :startDate and :endDate")
    public List<Appointment> findWeek(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT DISTINCT a FROM Appointment a " +
            "JOIN FETCH a.customer b " +
            "WHERE a.employee.id = :id " +
            "AND a.date = :date")
    public List<Appointment> findEmployeeAppointments(@Param("id") int id, @Param("date") LocalDate date);

//    @Query("SELECT a FROM Appointment a " +
//            "JOIN FETCH a.customer b " +
//            "LEFT JOIN  a.employeeToAppointments c " +
//            "LEFT JOIN  c.employee d"+
//            "WHERE d.id = :id")
    public Appointment findById(@Param("id") int id);

}
