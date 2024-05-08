package nu.zapp.models;

import nu.zapp.entities.Appointment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AppointmentModel {
    //Model sends on to the CRUD/Querier
    public List<Appointment> getCalender() {
        //Gets planned appointments for the calender for the planner
        return null;
    }

    public List<Appointment> getAppointments(int employeeId, LocalDate date) {
        //meant to get the employee's appointments for the day
        return null;
    }

    public Appointment getAppointmentDetails(int id) {
        //Meant to fetch info for the appointment screen
        return null;
    }
}
