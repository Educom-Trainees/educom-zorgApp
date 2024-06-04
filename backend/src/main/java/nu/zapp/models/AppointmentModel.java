package nu.zapp.models;

import nu.zapp.entities.Appointment;
import nu.zapp.entities.AppointmentTasks;
import nu.zapp.repositories.AppointmentRepository;
import nu.zapp.repositories.AppointmentTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class AppointmentModel {

    @Autowired
    private AppointmentRepository aRepository;

    @Autowired
    private AppointmentTaskRepository aTaskRepository;

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

    // Start section
    /* All functions between start and end are dedicated to creating and updating appointments
    Both need the setAppointmentTasksIds as in both cases the tasks are created from scratch
    For update this is to accommodate the removal of tasks from an appointment
    Comment last updated: 03/06/2024
     */
    public Appointment createAppointment(Appointment newAppointment){
        newAppointment.setId(0);
        List<AppointmentTasks> newAppointmentTasks = newAppointment.getAppointmentTasks();
        newAppointment.setAppointmentTasks(null);
        Appointment savedAppointment = aRepository.save(newAppointment);
        savedAppointment.setAppointmentTasks(setAppointmentTaskIds(savedAppointment, newAppointmentTasks));
//        savedAppointment.setStartTime(calculateStartTime(savedAppointment.getStartTime(),
//                savedAppointment.getAppointmentTasks()));
//        savedAppointment.setEndTime(calculateEndTime(savedAppointment.getEndTime(),
//                savedAppointment.getAppointmentTasks()));
        return aRepository.save(savedAppointment);
    }

    public Appointment updateAppointment(Appointment updatedAppointment){
        deleteOldAppointmentTasks(updatedAppointment.getId());
        updatedAppointment.setAppointmentTasks(setAppointmentTaskIds(updatedAppointment, updatedAppointment.getAppointmentTasks()));
//        updatedAppointment.setStartTime(calculateStartTime(updatedAppointment.getStartTime(), updatedAppointment.getAppointmentTasks()));
//        updatedAppointment.setEndTime(calculateEndTime(updatedAppointment.getEndTime(), updatedAppointment.getAppointmentTasks()));
        return aRepository.save(updatedAppointment);
    }

    /*
    Originally these two functions existed with the assumption a start and end time was set beforehand
    that is currently not always the case + the calculations are being handled in the front end
     */
//    private LocalTime calculateStartTime(LocalTime startTime, List<AppointmentTasks> taskList) {
//        for (AppointmentTasks task : taskList) {
//            LocalTime startTimeTask = task.getStartTime();
//
//            if (startTimeTask != null) {
//                if (startTime == null){
//                    startTime = startTimeTask;
//                } else if (startTimeTask.isBefore(startTime)) {
//                    startTime = startTimeTask;
//                }
//            }
//        }
//        return startTime;
//    }
//
//    private LocalTime calculateEndTime(LocalTime endTime, List<AppointmentTasks> taskList) {
//        for (AppointmentTasks task : taskList) {
//            LocalTime endTimeTask = task.getEndTime();
//            if (endTimeTask != null) {
//                if (endTimeTask.isAfter(endTime)) {
//                    endTime = endTimeTask;
//                }
//            }
//        }
//        return endTime;
//    }

    private List<AppointmentTasks> setAppointmentTaskIds(Appointment appointment, List<AppointmentTasks> taskList){
        for (AppointmentTasks task : taskList) {
            task.setAppointment(appointment);
            task.setId(0);
        }
        return taskList;
    }

    // end section

    private void deleteOldAppointmentTasks(int appointmentId) {
        List<AppointmentTasks> oldTasks = aTaskRepository.findByAppointmentId(appointmentId);
        aTaskRepository.deleteAll(oldTasks);
    }

    public void deleteAppointment(int id) {
        aRepository.delete(aRepository.findById(id));
    }
}
