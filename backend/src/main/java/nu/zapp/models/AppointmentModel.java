package nu.zapp.models;

import nu.zapp.entities.Appointment;
import nu.zapp.entities.AppointmentTasks;
import nu.zapp.repositories.AppointmentRepository;
import nu.zapp.repositories.AppointmentTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AppointmentModel {

    @Autowired
    private AppointmentRepository aRepository;
    @Autowired
    private AppointmentTaskRepository atRepository;

    @Autowired
    private CustomerModel mModel;
    @Autowired
    private EmployeeModel eModel;

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
        List<AppointmentTasks> newAppointmentTasks = newAppointment.getAppointmentTasks();
        // Remove AppointmentTasks from newAppointment
        newAppointment.setAppointmentTasks(null);
        Appointment savedAppointment = aRepository.save(newAppointment);

        for (AppointmentTasks task : newAppointmentTasks) {
            task.setAppointment(savedAppointment);
        }

        List<AppointmentTasks> savedAppointmentTasks = (List<AppointmentTasks>) atRepository.saveAll(newAppointmentTasks);

        savedAppointment.setAppointmentTasks(savedAppointmentTasks);

        return savedAppointment;
    }

    public List<Appointment> findEmployeeAppointments(int id, LocalDate date) {
        return aRepository.findEmployeeAppointments(id, date);
    }
}
