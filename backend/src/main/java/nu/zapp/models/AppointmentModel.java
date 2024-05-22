package nu.zapp.models;

import nu.zapp.entities.Appointment;
import nu.zapp.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AppointmentModel {

    @Autowired
    private AppointmentRepository aRepository;

    public List<Appointment> findAll() {
        return aRepository.findAll();
    }

    public List<Appointment> findWeek(LocalDate startWeek){
        LocalDate endWeek = startWeek.plusDays(6);
        return aRepository.findWeek(startWeek, endWeek);
    }

    public Appointment findById(int id){
        return aRepository.findById(id);
    }

    public Appointment createAppointment(Appointment newAppointment){
        return aRepository.save(newAppointment);
    }

    public List<Appointment> findEmployeeAppointments(int id, LocalDate date) {
        return aRepository.findEmployeeAppointments(id, date);
    }
}
