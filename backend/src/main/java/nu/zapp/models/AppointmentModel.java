package nu.zapp.models;

import nu.zapp.entities.Appointment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppointmentModel {
    //Model sends on to the CRUD/Querier
    public List<Appointment> getCalender() {
        return null;
    }

    public List<Appointment> getAppointments(String employee) {
        return null;
    }

    public Appointment getAppointmentDetails(String appointmentId) {
        return null;
    }
}
