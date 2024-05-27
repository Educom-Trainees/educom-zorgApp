package nu.zapp.models;

import nu.zapp.entities.Appointment;
import nu.zapp.entities.AppointmentTasks;
import nu.zapp.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class AppointmentModel {

    @Autowired
    private AppointmentRepository aRepository;

    public List<Appointment> findAll() {
        return aRepository.findAll();
    }

    public List<Appointment> findByDates(LocalDate startWeek, int count){
        LocalDate endWeek = startWeek.plusDays(count);
        return aRepository.findByDates(startWeek, endWeek);
    }

    public Appointment findById(int id){
        return aRepository.findById(id);
    }

    public List<Appointment> findEmployeeAppointments(int id, LocalDate date, int count) {
        return aRepository.findEmployeeAppointments(id, date);
    }

    public Appointment createAppointment(Appointment newAppointment){
        List<AppointmentTasks> newAppointmentTasks = newAppointment.getAppointmentTasks();
        newAppointment.setAppointmentTasks(null);
        Appointment savedAppointment = aRepository.save(newAppointment);
        savedAppointment.setAppointmentTasks(setAppointmentTaskIds(savedAppointment, newAppointmentTasks));
        savedAppointment.setStartTime(calculateStartTime(savedAppointment.getStartTime(),
                savedAppointment.getAppointmentTasks()));
        savedAppointment.setEndTime(calculateEndTime(savedAppointment.getEndTime(),
                savedAppointment.getAppointmentTasks()));
        return aRepository.save(savedAppointment);
    }

    public Appointment updateAppointment(Appointment updatedAppointment){
        updatedAppointment.setAppointmentTasks(setAppointmentTaskIds(updatedAppointment, updatedAppointment.getAppointmentTasks()));
        updatedAppointment.setStartTime(calculateStartTime(updatedAppointment.getStartTime(), updatedAppointment.getAppointmentTasks()));
        updatedAppointment.setEndTime(calculateEndTime(updatedAppointment.getEndTime(), updatedAppointment.getAppointmentTasks()));
        return aRepository.save(updatedAppointment);
    }

    private LocalTime calculateStartTime(LocalTime startTime, List<AppointmentTasks> taskList) {
        for (AppointmentTasks task : taskList) {
            LocalTime startTimeTask = task.getStartTime();
            if (startTimeTask != null) {
                if (startTimeTask.isBefore(startTime)) {
                    startTime = startTimeTask;
                }
            }
        }
        return startTime;
    }

    private LocalTime calculateEndTime(LocalTime endTime, List<AppointmentTasks> taskList) {
        for (AppointmentTasks task : taskList) {
            LocalTime endTimeTask = task.getEndTime();
            if (endTimeTask != null) {
                if (endTimeTask.isAfter(endTime)) {
                    endTime = endTimeTask;
                }
            }
        }
        return endTime;
    }

    private List<AppointmentTasks> setAppointmentTaskIds(Appointment appointment, List<AppointmentTasks> taskList){
        for (AppointmentTasks task : taskList) {
            task.setAppointment(appointment);
        }
        return taskList;
    }

}
